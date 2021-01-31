package top.simba1949.mapper;

import org.apache.ibatis.annotations.Param;
import top.simba1949.model.SysUser;

import java.util.List;

/**
 * @author anthony
 * @date 2021/1/31 14:01
 */
public interface SysUserMapper {
    /**
     * 查询所有
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 通过id获取数据
     * @param id
     * @return
     */
    SysUser get(@Param("id") long id);

    /**
     * 插入
     * @param sysUser
     * @return
     */
    void insert(@Param("sysUser") SysUser sysUser);

    /**
     * 更新
     * @param sysUser
     */
    void update(@Param("sysUser") SysUser sysUser);

    /**
     * 删除
     * @param id
     */
    void delete(@Param("id") long id);
}
