package cc.whyy0u.controller.user.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import cc.whyy0u.entity.target.ValuteType;
import lombok.Data;

@Data
public class AddTargetRequest {
    String name;
    String description;
    String photo;
    double targetPrice;

    @JsonFormat(pattern = "MM/dd/yyyy")
    LocalDate endDate;

    ValuteType valuteType;
}
