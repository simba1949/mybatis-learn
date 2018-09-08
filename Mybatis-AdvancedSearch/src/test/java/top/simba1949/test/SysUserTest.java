package top.simba1949.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.simba1949.common.SysUser;
import top.simba1949.mapper.SysUserMapper;

import java.util.List;

/**
 * @author simba1949@outlook.com
 * @date 2018/9/8 16:13
 */
public class SysUserTest extends BaseMapper {

    @Test
    public void selectSysUserViaTypeAliasesTest(){
        SqlSession sqlSession = getSqlSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> sysUsers = mapper.selectSysUserViaTypeAliases();
        for (SysUser sysUser : sysUsers) {
            System.out.println(sysUser);
        }

        sqlSession.close();
    }

    @Test
    public void selectSysUserViaResultMapSimpleTest(){
        SqlSession sqlSession = getSqlSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> sysUsers = mapper.selectSysUserViaResultMapSimple();
        for (SysUser sysUser : sysUsers) {
            System.out.println(sysUser);
        }

        sqlSession.close();
    }

    @Test
    public void selectSysUserViaResultMapAssociationTest(){
        SqlSession sqlSession = getSqlSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> sysUsers = mapper.selectSysUserViaResultMapAssociation();
        for (SysUser sysUser : sysUsers) {
            System.out.println(sysUser);
        }

        sqlSession.close();
    }

    @Test
    public void selectSysUserViaResultMapAssociationResultMapTest(){
        SqlSession sqlSession = getSqlSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> sysUsers = mapper.selectSysUserViaResultMapAssociationResultMap();
        for (SysUser sysUser : sysUsers) {
            System.out.println(sysUser);
        }

        sqlSession.close();
    }
}

