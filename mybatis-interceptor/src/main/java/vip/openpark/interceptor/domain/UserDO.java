package vip.openpark.interceptor.domain;

import lombok.Data;
import vip.openpark.interceptor.interceptor.OpenDesensitizationAnnotation;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 表名：user
 * 表注释：用户信息表
*/
@Data
@Table(name = "user")
public class UserDO implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    /**
     * 业务编码
     */
    @Column(name = "code")
    private String code;

    /**
     * 用户登录名
     */
    @OpenDesensitizationAnnotation
    @Column(name = "username")
    private String username;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String real_name;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nick_name;

    /**
     * 密码
     */
    @OpenDesensitizationAnnotation
    @Column(name = "password")
    private String password;

    /**
     * 性别，0表示女，1表示男
     */
    @Column(name = "gender")
    private Byte gender;

    /**
     * 出生日期
     */
    @Column(name = "birthday")
    private LocalDateTime birthday;

    /**
     * 民族
     */
    @Column(name = "nation")
    private String nation;

    /**
     * 国家
     */
    @Column(name = "country_name")
    private String country_name;

    /**
     * 身份证信息
     */
    @Column(name = "id_card")
    private String id_card;

    /**
     * 地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 手机号码
     */
    @OpenDesensitizationAnnotation
    @Column(name = "phone")
    private String phone;

    /**
     * 邮件
     */
    @Column(name = "email")
    private String email;

    /**
     * 是否启用，0表示否，1表示是
     */
    @Column(name = "bl_enable")
    private Byte bl_enable;

    /**
     * 是否删除，0表示否，1表示是
     */
    @Column(name = "bl_delete")
    private Byte bl_delete;

    /**
     * 版本号
     */
    @Column(name = "version")
    private Long version;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private LocalDateTime gmt_create;

    /**
     * 创建人真实姓名
     */
    @Column(name = "creator")
    private String creator;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private Long creator_id;

    /**
     * 创建人code
     */
    @Column(name = "creator_code")
    private String creator_code;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modify")
    private LocalDateTime gmt_modify;

    /**
     * 修改人真实姓名
     */
    @Column(name = "modifier")
    private String modifier;

    /**
     * 修改人ID
     */
    @Column(name = "modifier_id")
    private Long modifier_id;

    /**
     * 修改人code
     */
    @Column(name = "modifier_code")
    private String modifier_code;

    private static final long serialVersionUID = 1L;
}