package vip.openpark.jdbc.quick.start.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author anthony
 * @version 2023/11/6 17:16
 */
@Data
public class User implements Serializable {
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
    private LocalDateTime gmtModifier;
    private String modifier;
    private Long modifierId;
    private String modifierCode;
}