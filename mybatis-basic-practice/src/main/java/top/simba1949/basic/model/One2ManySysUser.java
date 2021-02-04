package top.simba1949.basic.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author anthony
 * @date 2021/2/4
 */
@Data
public class One2ManySysUser implements Serializable {
    private static final long serialVersionUID = 4478073671257376439L;

    private Long id;
    private String username;
    private String userPassword;
    private String userEmail;
    private String userInfo;
    private String headImg;
    private LocalDateTime createTime;

    private List<One2ManySysRole> roleList;
}
