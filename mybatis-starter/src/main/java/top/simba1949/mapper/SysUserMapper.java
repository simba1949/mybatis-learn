package top.simba1949.mapper;

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
}
