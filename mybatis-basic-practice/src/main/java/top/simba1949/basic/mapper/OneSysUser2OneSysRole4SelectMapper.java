package top.simba1949.basic.mapper;

import top.simba1949.basic.model.One2OneSysUser;

import java.util.List;

/**
 * @author anthony
 * @date 2021/2/2
 */
public interface OneSysUser2OneSysRole4SelectMapper {
    /**
     * 一对一映射，通过别名设置
     * @return
     */
    List<One2OneSysUser> findAllByTypeAliases();

    /**
     * 一对一映射，通过简单的 resultMap
     * @return
     */
    List<One2OneSysUser> findAllByResultMapSimple();

    /**
     * 一对一映射，通过resultMap中 association 设置
     * @return
     */
    List<One2OneSysUser> findAllByResultMapAssociation();

    /**
     * 一对一映射，通过association中的resultMap对resultMap设置
     * @return
     */
    List<One2OneSysUser> findAllByResultMapAssociationSpanXml();
}
