package top.simba1949.mapper;

import top.simba1949.common.User;

import java.util.List;

public interface UserMapper {
    /**
     * 一对多映射，通过 collection 跨 XML 查询
     * @return
     */
    List<User> selectUserViaResultMapCollectionResultMap();
    /**
     * 一对多映射，通过 collection 设置查询
     * @return
     */
    List<User> selectUserViaResultMapCollectionSimple();
}