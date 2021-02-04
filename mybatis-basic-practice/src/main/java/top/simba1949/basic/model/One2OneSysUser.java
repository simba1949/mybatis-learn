package top.simba1949.basic.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author anthony
 * @date 2021/2/2
 */
@Data
public class One2OneSysUser implements Serializable {

    private static final long serialVersionUID = 5211841094596047578L;

    private Long id;
    private String username;
    private String userPassword;
    private String userEmail;
    private String userInfo;
    private String headImg;
    private LocalDateTime createTime;

    private One2OneSysRole role;
}
