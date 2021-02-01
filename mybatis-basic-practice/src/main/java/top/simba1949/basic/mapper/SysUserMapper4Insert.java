package top.simba1949.basic.mapper;

import org.apache.ibatis.annotations.Param;
import top.simba1949.basic.model.SysUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author anthony
 * @date 2021/2/1
 */
public interface SysUserMapper4Insert {
    /**
     * 简单插入
     * @param sysUser
     */
    void insert(@Param("sysUser") SysUser sysUser);

    /**
     * 插入后将主键信息设置到对象中
     * @param sysUser
     */
    void insertByJdbc(@Param("sysUser") SysUser sysUser);

    /**
     * 插入后，使用selectKey将主键信息设置到对象中
     * @param sysUser
     */
    void insertBySelectKey(@Param("sysUser") SysUser sysUser);

    /**
     * 批量插入list
     * @param sysUserList
     */
    void insertBatch4List(@Param("list") List<SysUser> sysUserList);

    /**
     * 批量插入set
     * @param sysUserSet
     */
    void insertBatch4Set(@Param("set") Set<SysUser> sysUserSet);

    /**
     * 批量插入map（只插入用户名和密码）
     * @param usernameAndPwd
     */
    void insertBatch4Map(@Param("map") Map<String, String> usernameAndPwd);

    /**
     * 静态值，静态方法插入
     */
    void insertStatic();
}
