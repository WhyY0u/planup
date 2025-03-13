package cc.whyy0u.utils;

import java.time.LocalDate;

import cc.whyy0u.entity.target.ValuteType;
import lombok.Data;


@Data
public class TargetResponseUtils {
    String name;
    String description;
    String photo;
    String targetPrice;
    String curntPrice;
    LocalDate endDate;
    ValuteType valuteType;
}
