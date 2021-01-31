package top.simba1949.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author anthony
 * @date 2021/1/31 13:33
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 6404663857840500983L;

    private Long id;
    private String username;
    private String userPassword;
    private String userEmail;
    private String userInfo;
    private String headImg;
    private LocalDateTime createTime;
}
