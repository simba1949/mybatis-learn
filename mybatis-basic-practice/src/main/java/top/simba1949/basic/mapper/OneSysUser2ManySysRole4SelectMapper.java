package top.simba1949.basic.mapper;

import top.simba1949.basic.model.One2ManySysUser;

import java.util.List;

/**
 * @author anthony
 * @date 2021/2/4
 */
public interface OneSysUser2ManySysRole4SelectMapper {
    /**
     * 一对多映射，通过简单的 collection 设置查询
     * @return
     */
    List<One2ManySysUser> findAllByResultMapCollectionSimple();

    /**
     * 一对多映射，通过collection 跨 XML 查询
     * @return
     */
    List<One2ManySysUser> findAllByResultMapCollectionSpanXml();
}
