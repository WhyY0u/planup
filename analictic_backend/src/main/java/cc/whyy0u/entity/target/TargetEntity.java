package cc.whyy0u.entity.target;

import java.time.LocalDate;

import cc.whyy0u.controller.user.request.AddTargetRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "target")
public class TargetEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    Long id;

    @Column(name = "name", unique = false, nullable = false, length = 50)
    String name;

    @Column(name = "description", unique = false, nullable = false, length = 500)
    String description;

    @Column(name = "photo", unique = false, nullable = true, length = 255)
    String photo;

    @Column(name = "targetPrice", unique = false, nullable = false, length = 255)
    double targetPrice;

    @Column(name = "curntPrice", unique = false, nullable = false, length = 255)
    double curntPrice;

    @Column(name = "endDate", unique = false, nullable = false)
    LocalDate endDate;

    @Column(name = "creator_id", unique = true, nullable = false)
    Long creator_id;


    public TargetEntity(AddTargetRequest addTargetRequest, Long create_id) {
        this.name = addTargetRequest.getName();
        this.description = addTargetRequest.getDescription();
        this.photo = addTargetRequest.getPhoto();
        this.endDate = addTargetRequest.getEndDate();
        this.targetPrice = addTargetRequest.getTargetPrice();
        this.curntPrice = 0.0f;
        this.creator_id = create_id;
    }
}
