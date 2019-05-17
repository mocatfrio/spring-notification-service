package id.ac.its.notification_api.entity;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(readOnly = true)
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

    public enum Type {
        ANDROID, IOS, WEB
    }
}
