package id.ac.its.notification_api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "account")
@Data
public class Account {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
}
