package vip.openpark.advanced.search.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author anthony
 * @version 2018/9/5 16:48
 */
@Data
public class OneToManyRoleDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 7454240971789116908L;

    private Long id;
    private String code;
    private String name;

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