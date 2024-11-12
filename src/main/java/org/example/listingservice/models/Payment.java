package org.example.listingservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
@Builder
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="user_id")
    @ToString.Exclude
    private User user;

    private Integer status;
    private Double money;
    private Long posts;
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist()
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }


    @PreUpdate()
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

    public String toHtmlBody() {

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Payment Details</title>\n" +
                "    <style>\n" +
                "        body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }\n" +
                "        .container { max-width: 800px; margin: auto; background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); }\n" +
                "        h1 { text-align: center; }\n" +
                "        table { width: 100%; border-collapse: collapse; }\n" +
                "        table, th, td { border: 1px solid #ddd; }\n" +
                "        th, td { padding: 10px; text-align: left; }\n" +
                "        th { background-color: #f2f2f2; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <h1>Payment Details</h1>\n" +
                "        <table>\n" +
                "            <tr><th>Payment ID</th><td>" + id + "</td></tr>\n" +
                "            <tr><th>User</th><td>" + (user != null ? user.getId() + " - " +user.getFullName() : "N/A") + "</td></tr>\n" +
                "            <tr><th>Amount</th><td>" + money + "VND </td></tr>\n" +
                "            <tr><th>Post ID</th><td>" + posts + "</td></tr>\n" +
                "            <tr><th>Email</th><td>" + email + "</td></tr>\n" +
                "            <tr><th>Created At</th><td>" + createdAt + "</td></tr>\n" +
                "            <tr><th>Updated At</th><td>" + updatedAt + "</td></tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                " <a href=\"http://localhost:5173/new-post\" >"+
                " return back to our website </a> "+
                "</body>\n" +
                "</html>";
    }

}
