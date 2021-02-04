package top.simba1949.basic.mapper;

import top.simba1949.basic.model.SysUser;

import java.util.List;

/**
 * @author anthony
 * @date 2021/2/2
 */
public interface SysUserMapper4SelectMapper {
    /**
     * 通过 别名 映射查询
     * @return
     */
    List<SysUser> selectAllByAlias();

    /**
     * 通过 ResultMap 映射查询
     * @return
     */
    List<SysUser> selectAllByResultMap();
}
