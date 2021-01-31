package top.simba1949.common;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author anthony
 * @date 2021/1/31 9:43
 */
@Data
public class SysUser implements Serializable {
    private Long id;
    private String username;
    private String userPassword;
    private String userEmail;
    private String userInfo;
    private String headImg;
    private LocalDateTime createTime;
}
