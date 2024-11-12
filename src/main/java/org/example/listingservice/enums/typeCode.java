package org.example.listingservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum typeCode {
    NOI_THAT("nội thất"),
    NGUYEN_CAN("nguyên căn"),
    TANG_TRET("tầng trêt"),
    CHO_THUE("cho thuê"),
    BAN("bán");

    private String type;

    typeCode(String type) {this.type = type;}
    private String getType(){return type;}
    public void setType(String type){
        setType(type);
    }
    public static Map<String, String> type() {
        Map<String, String> listType = new HashMap<>();
        for (typeCode item : typeCode.values()) {
            listType.put(item.toString(), item.getType());
        }
        return listType;
    }
}
