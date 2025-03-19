package org.example.listingservice.services.payments;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.listingservice.constant.MessageKeys;
import org.example.listingservice.dtos.PaymentDTO;
import org.example.listingservice.messages.PaymentMessage;
import org.example.listingservice.repositories.PaymentRepository;
import org.example.listingservice.repositories.UserRepository;
import org.example.listingservice.responses.payment.PaymentDetailResponse;
import org.example.listingservice.responses.payment.PaymentListResponse;
import org.example.listingservice.services.MailService;
import org.example.listingservice.services.rabbitMQ.RabbitMQProducer;
import org.example.listingservice.services.user.UserService;
import lombok.RequiredArgsConstructor;

import org.example.listingservice.models.Payment;
import org.example.listingservice.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService{
    private final UserService userService;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final HttpServletRequest request;
    private final MailService mailService;
    private final RabbitMQProducer rabbitMQProducer;



    @Value("${vnpay.return_client_url}")
    private  String returnClientUrl;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Value("${vnpay.tmn_code}")
    private String vnp_TmnCode;

    @Value("${vnpay.hash_secret}")
    private String vnp_HashSecret;

    @Value("${vnpay.url}")
    private String vnp_Url;

    @Value("${vnpay.return_url}")
    private String vnp_ReturnUrl;

    @Value("${file.upload-dir}")
    private String uploadDir;


    public Boolean isOwner(Long id, String username){
        return userRepository.findById(id)
                .map(user -> user.getUsername().equals(username))
                .orElse(false);
    }
    public String createPaymentUrl(PaymentDTO paymentDTO) throws Exception{
        User existingUser = userRepository.findById(paymentDTO.getUserId()).orElse(null);
        if(existingUser==null){
            return MessageKeys.USER_NOT_FOUND + "with id = " + paymentDTO.getUserId();
        }
        Payment newPayment = Payment.builder()
                .status(0)
                .user(existingUser)
                .posts(paymentDTO.getQuantity())
                .email(paymentDTO.getEmail())
                .money(1.0*paymentDTO.getQuantity()*10000)
                .build();

        Payment savedPayment = paymentRepository.save(newPayment);
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "billpayment";
        String vnp_OrderInfo = URLEncoder.encode("Thanh toan đon hang #" + savedPayment.getId(), StandardCharsets.US_ASCII.toString());

        String orderId = savedPayment.getId().toString();
        String vnp_Amount = String.valueOf(paymentDTO.getQuantity() * 10000 * 100);
        String bankCode = "NCB";

        String vnp_Locale = "vn";

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version",vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", vnp_Amount);
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", orderId);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", vnp_Locale);
        vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
        String vnp_IpAddr = request.getRemoteAddr();
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_BankCode", bankCode);
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while(itr.hasNext()){
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if(fieldValue!= null &&fieldValue.length()>0){
                hashData.append(fieldName)
                        .append("=")
                        .append(URLEncoder.encode(fieldValue,StandardCharsets.US_ASCII.toString()));
                query.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    hashData.append('&');
                    query.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = hmacSHA512(vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        return vnp_Url + "?" + queryUrl;
    }
    private String hmacSHA512(String key, String data) throws Exception {
        Mac hmac512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmac512.init(secretKey);
        byte[] hash = hmac512.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder result = new StringBuilder();
        for (byte b : hash) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }


    @Override
    public String getVNPayURL(PaymentDTO dto) {
        return null;
    }

    @Override
    public RedirectView handleVNPayReturnURL(Map<String, String> params) throws Exception {
        StringBuilder urlReturn = new StringBuilder(returnClientUrl);
        String responseCode = params.get("vnp_ResponseCode");
        if(responseCode.equals("24")){
          urlReturn.append("failed");
        }
        else if(responseCode.equals("00")){
            Long orderId = Long.parseLong(params.get("vnp_TxnRef"));
            Payment existingPayment = paymentRepository.findById(orderId).get();
            User user = existingPayment.getUser();
            user.setRemainPost(user.getRemainPost() + existingPayment.getPosts());
            user.setTotalPaid(user.getTotalPaid() + existingPayment.getMoney());
            urlReturn.append("success&current-post=")
                    .append(user.getRemainPost());
            userRepository.save(user);
            existingPayment.setStatus(1);
            paymentRepository.saveAndFlush(existingPayment);

            rabbitMQProducer.sendMailToQueue(PaymentMessage.builder()
                            .email(existingPayment.getEmail())
                            .id(existingPayment.getId())
                    .build());

        }
     return new RedirectView(urlReturn.toString());
    }

    @Override
    public PaymentListResponse adminGetPayments(int year, int month, int page, int limit) {
        PageRequest request = PageRequest.of(page,limit);
        Page<Payment> payments = paymentRepository.findAllByMonthAndYear(month, year, request);

        List<PaymentDetailResponse> responses = payments.getContent().stream().map(PaymentDetailResponse::fromPayment).toList();
        return PaymentListResponse.builder()
                .totalPages(payments.getTotalPages())
                .totalRevenue((double) PaymentListResponse.calculateTotalPaid(payments.getContent()).intValue())
                .paymentResponses(responses)
                .build();
    }


    @Override
    public PaymentListResponse getAllByUserId(Long userId) {
        List<Payment> payments = paymentRepository.findAllByUser_IdAndStatus(userId,1, Sort.by(Sort.Direction.DESC, "createdAt"));
        List<PaymentDetailResponse> response = payments.stream().map(PaymentDetailResponse::fromPayment).toList();
        return PaymentListResponse.builder()
                .totalPaid(PaymentListResponse.calculateTotalPaid(payments))
                .paymentResponses(response)
                .build();
    }

    @Override
    public Double getTotalRevenue(int year,int month) {
        return paymentRepository.getTotalRevenueByStatus(1,year, month);
    }

    @Override
    public String downloadExcelUrl(int year, int month)throws IOException {
        List<Payment> payments = paymentRepository.findAllByMonthAndYear(month, year);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("payments_" +month+"_"+year);

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("User ID");
        headerRow.createCell(2).setCellValue("Money");
        headerRow.createCell(3).setCellValue("Email");
        headerRow.createCell(4).setCellValue("Created At");

        int rowIndex = 1;
        for (Payment payment : payments) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(payment.getId());
            row.createCell(1).setCellValue(payment.getUser() != null ? payment.getUser().getId() : null);
            row.createCell(2).setCellValue(payment.getMoney());
            row.createCell(3).setCellValue(payment.getEmail());
            row.createCell(4).setCellValue(payment.getCreatedAt().toString());
        }

        File file = new File(uploadDir + "payments_"+month+"_"+year);
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
        }
        workbook.close();

        return file.getAbsolutePath();
    }
}
