package cc.whyy0u.controller.user.request;

import cc.whyy0u.entity.target.ValuteType;
import lombok.Data;

@Data
public class UpadateTargetRequest {
    String name;
    String photo;
    double currntPrice = -1;
    ValuteType valuteType;


    public UpadateTargetRequest(String name, String photo, double currntPrice, ValuteType valuteType) {
        this.name = name;
        this.photo = photo;
        this.currntPrice = currntPrice;
        this.valuteType = valuteType;
    }
} 
