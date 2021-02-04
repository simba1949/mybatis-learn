package top.simba1949.basic.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author anthony
 * @date 2021/2/4
 */
@Data
public class One2ManySysRole implements Serializable {
    private static final long serialVersionUID = -3075369047823258385L;

    private Long id;
    private String roleName;
    private Integer enabled;
    private String createBy;
    private LocalDateTime createTime;
}
