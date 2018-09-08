package top.simba1949.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.simba1949.common.User;
import top.simba1949.mapper.UserMapper;

import java.util.List;

/**
 * @author simba1949@outlook.com
 * @date 2018/9/8 16:13
 */
public class UserTest extends BaseMapper {

    @Test
    public void selectSysUserViaTypeAliasesTest(){
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectUserViaResultMapCollectionSimple();
        for (User user : users) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    @Test
    public void selectUserViaResultMapCollectionResultMapTest(){
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectUserViaResultMapCollectionResultMap();
        for (User user : users) {
            System.out.println(user);
        }

        sqlSession.close();
    }

}

