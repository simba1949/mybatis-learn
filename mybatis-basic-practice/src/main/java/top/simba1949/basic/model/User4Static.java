package top.simba1949.basic.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author anthony
 * @date 2021/2/1
 */
@Data
public class User4Static implements Serializable {
    private static final long serialVersionUID = -2236335298827740174L;

    static String username = "username";
    static String getPassword(){
        return "password";
    }
}
