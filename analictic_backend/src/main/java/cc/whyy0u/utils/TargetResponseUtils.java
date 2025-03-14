package cc.whyy0u.utils;

import java.time.LocalDate;

import cc.whyy0u.entity.target.TargetEntity;
import cc.whyy0u.entity.target.ValuteType;
import lombok.Data;


@Data
public class TargetResponseUtils {
    String name;
    String description;
    String photo;
    double targetPrice;
    double curntPrice;
    LocalDate endDate;

    public TargetResponseUtils(TargetEntity entity) {
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.photo = entity.getPhoto();
        this.targetPrice = entity.getTargetPrice();
        this.curntPrice = entity.getCurntPrice();
        this.endDate = entity.getEndDate();
    }
}
