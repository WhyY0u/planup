package cc.whyy0u.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "name", unique = false, nullable = false, length = 100)
  String name;

  @Column(name = "password", unique = false, nullable = false, length = 255)
  String password;

  @Column(name = "login", unique = true, nullable = false, length = 255)
  String login;
} 
