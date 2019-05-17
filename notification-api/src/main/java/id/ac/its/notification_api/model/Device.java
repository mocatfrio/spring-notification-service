package id.ac.its.notification_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "device")
@Data
public class Device {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "token", nullable = false)
    @NotBlank
    private String token;
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;
    @Column(name = "user_id", nullable = false)
    @NotNull
    private Integer userId;

    @JsonIgnore
    public boolean isFcm() {
        return Type.ANDROID == this.type || Type.WEB == this.type;
    }

    @JsonIgnore
    public boolean isApn() {
        return Type.IOS == this.type;
    }

    public enum Type {
        ANDROID, IOS, WEB
    }
}
