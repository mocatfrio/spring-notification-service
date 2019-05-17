package id.ac.its.notification_api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "device")
@Data
public class Device {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "token", nullable = false)
    private String token;
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public boolean isFcm() {
        return Type.ANDROID == this.type || Type.WEB == this.type;
    }

    public boolean isApn() {
        return Type.IOS == this.type;
    }

    public enum Type {
        ANDROID, IOS, WEB
    }
}
