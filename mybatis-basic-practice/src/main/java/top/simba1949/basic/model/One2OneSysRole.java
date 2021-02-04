package top.simba1949.basic.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author anthony
 * @date 2021/2/2
 */
@Data
public class One2OneSysRole implements Serializable {
    private static final long serialVersionUID = -5975477131415920816L;

    private Long id;
    private String roleName;
    private Integer enabled;
    private String createBy;
    private LocalDateTime createTime;
}
