package top.simba1949.mapper;

import top.simba1949.common.RoleDto;

import java.util.List;

/**
 * @author simba1949@outlook.com
 * @date 2018/9/6 15:23
 */
public interface RoleMapper {
    /**
     * 一对一映射,通过别名设置
     * @return
     */
    List<RoleDto> findAllByTypeAliases();

    /**
     * 一对一映射，通过简单的对resultMap设置
     * @return
     */
    List<RoleDto> findAllByResultMapSimple();

    /**
     * 一对一映射，通过association对resultMap设置
     * @return
     */
    List<RoleDto> findAllByResultMapAssociation();

    /**
     * 一对一映射，通过association中的resultMap对resultMap设置
     * @return
     */
    List<RoleDto> findAllByResultMapAssociationResultMap();



}
