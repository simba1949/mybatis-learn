package top.simba1949.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import top.simba1949.common.RoleDto;
import top.simba1949.mapper.RoleMapper;

import java.util.List;

/**
 * @author simba1949@outlook.com
 * @date 2018/9/6 15:31
 */
public class RoleMapperTest extends BaseMapper{


    @Test
    public void findAllTest(){
        SqlSession sqlSession = getSqlSession();
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        List<RoleDto> roleDtos = roleMapper.findAllByTypeAliases();
        for (RoleDto roleDto : roleDtos) {
            System.out.println(roleDto);
        }
        sqlSession.close();
    }

    @Test
    public void findAllByResultMapSimpleTest(){
        SqlSession sqlSession = getSqlSession();
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        List<RoleDto> roleDtos = roleMapper.findAllByResultMapSimple();
        for (RoleDto roleDto : roleDtos) {
            System.out.println(roleDto);
        }
        sqlSession.close();
    }

    @Test
    public void findAllByResultMapAssociationTest(){
        SqlSession sqlSession = getSqlSession();
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        List<RoleDto> roleDtos = roleMapper.findAllByResultMapAssociation();
        for (RoleDto roleDto : roleDtos) {
            System.out.println(roleDto);
        }
        sqlSession.close();
    }

    @Test
    public void findAllByResultMapAssociationResultMapTest(){
        SqlSession sqlSession = getSqlSession();
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        List<RoleDto> roleDtos = roleMapper.findAllByResultMapAssociationResultMap();
        for (RoleDto roleDto : roleDtos) {
            System.out.println(roleDto);
        }
        sqlSession.close();
    }
}
