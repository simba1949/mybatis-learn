package vip.openpark.cache.redis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author anthony
 * @version 2023/11/6 17:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5786786308237386825L;

    private Long id;
    private String code;
    private String username;
    private String realName;
    private String nickName;
    private String password;
    private Byte gender;
    private LocalDateTime birthday;
    private String nation;
    private String countryName;
    private String idCard;
    private String address;
    private String phone;
    private String email;

    private Byte blEnable;
    private Byte blDelete;
    private Long version;
    private LocalDateTime gmtCreate;
    private String creator;
    private Long creatorId;
    private String creatorCode;
    private LocalDateTime gmtModify;
    private String modifier;
    private Long modifierId;
    private String modifierCode;
}