package org.example.listingservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum districtCode {
    THANH_PHO_THU_DUC("Thành phố Thủ Đức"),
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_3("Quận 3"),
    QUAN_4("Quận 4"),
    QUAN_5("Quận 5"),
    QUAN_6("Quận 6"),
    QUAN_7("Quận 7"),
    QUAN_8("Quận 8"),
    QUAN_9("Quận 9"),
    QUAN_10("Quận 10"),
    QUAN_11("Quận 11"),
    QUAN_12("Quận 12"),
    QUAN_BINH_TAN("Quận Bình Tân"),
    QUAN_BINH_THANH("Quận Bình Thạnh"),
    QUAN_GO_VAP("Quận Gò Vấp"),
    QUAN_PHU_NHUAN("Quận Phú Nhuận"),
    QUAN_TAN_BINH("Quận Tân Bình"),
    QUAN_TAN_PHU("Quận Tân Phú"),
    HUYEN_BINH_CHANH("Huyện Bình Chánh"),
    HUYEN_CAN_GIO("Huyện Cần Giờ"),
    HUYEN_CU_CHI("Huyện Củ Chi"),
    HUYEN_HOC_MON("Huyện Hóc Môn"),
    HUYEN_NHA_BE("Huyện Nhà Bè");

    private String districtName;

    districtCode(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public static Map<String, String> district() {
        Map<String, String> listDistrict = new HashMap<>();
        for (districtCode item : districtCode.values()) {
            listDistrict.put(item.toString(), item.getDistrictName());
        }
        return listDistrict;
    }
}