package cc.whyy0u.entity.incone;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "incone")
public class InconeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    Long id;

    @Column(name = "creator_id", unique = false, nullable = false)
    Long creator_id;

    @Column(name = "name", unique = false, nullable = false, length = 50)
    String name;      

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    double amount;    

    @Column(name = "next_date", nullable = false)
    LocalDate nextDate;  
}
