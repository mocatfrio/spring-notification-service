package id.ac.its.notification_api.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "role")
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private Name name;
    @ManyToOne(optional = false)
    private Account account;

    @Override
    public String getAuthority() {
        return name.name();
    }

    public enum Name {
        CUSTOMER, ADMIN
    }
}
