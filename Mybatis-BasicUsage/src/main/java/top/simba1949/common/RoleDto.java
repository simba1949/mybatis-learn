package top.simba1949.common;

import java.util.Date;

/**
 * @author v_jiayytian@tencent.com
 * @date 2018/9/6 15:03
 */
public class RoleDto {

    private Long id;
    private String roleName;
    private Integer enabled;
    private Integer createBy;
    private Date createTime;

    private UserDto userDto;


    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", enabled=" + enabled +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", userDto=" + userDto +
                '}';
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
