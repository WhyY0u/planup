package cc.whyy0u.controller.incone.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AddInconeRequest {
    String name;         
    LocalDate nextDate;
    double amount;
}
