package vip.openpark.advanced.search.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author anthony
 * @version 2023/11/9 19:31
 */
@Data
public class OneToManyPermissionDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3087422575494783232L;

    private Long id;
    private String code;
    private String name;
    private String url;

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