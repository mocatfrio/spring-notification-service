package id.ac.its.notification_api.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "authority")
@Data
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne(optional = false)
    private Account account;

    @Override
    public String getAuthority() {
        return role.name();
    }

    public enum Role {
        CUSTOMER, ADMIN
    }
}
