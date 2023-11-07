package top.simba1949.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import top.simba1949.common.SysUser;
import top.simba1949.mapper.SysUserMapper;

/**
 * @author simba1949@outlook.com
 * @date 2018/9/8 19:42
 */
public class SysUserMapperTest extends BasisMapper{

    SqlSession sqlSession;
    SysUser userOne;

    @Test
    public void testCache(){
        try {
            sqlSession = getSession();
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            userOne = mapper.selectByPrimaryKey(1L);
            userOne.setUserName("New Name");
            SysUser userTwo = mapper.selectByPrimaryKey(1L);
            Assert.assertEquals("New Name",userTwo.getUserName());
            Assert.assertEquals(userOne,userTwo);
        } finally {
            sqlSession.close();
        }

        System.out.println("---------------我是可爱的分割线-------------------");

        try {
            sqlSession = getSession();
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = mapper.selectByPrimaryKey(1L);
            Assert.assertNotEquals("New Name", user.getUserName());
            Assert.assertNotEquals(userOne,user);

            mapper.deleteByPrimaryKey(2L);
            SysUser user3 = mapper.selectByPrimaryKey(1L);
            Assert.assertNotEquals(user,user3);
        } finally {
            sqlSession.close();
        }
    }


    SqlSession session;
    SysUser user1;
    @Test
    public void test2Cache(){
        try {
            session = getSession();
            SysUserMapper mapper = session.getMapper(SysUserMapper.class);
            user1 = mapper.selectByPrimaryKey(1L);
            user1.setUserName("New Name");

            SysUser user2 = mapper.selectByPrimaryKey(1L);

            Assert.assertEquals("New Name", user2.getUserName());
            Assert.assertNotEquals(user1,user2);
        } finally {
            session.close();
        }

        System.out.println("****************");

        try {
            session = getSession();
            SysUserMapper mapper = session.getMapper(SysUserMapper.class);
            SysUser user2 = mapper.selectByPrimaryKey(1L);
            Assert.assertEquals("New Name",user2.getUserName());
            Assert.assertNotEquals(user1,user2);

            SysUser user3 = mapper.selectByPrimaryKey(1L);
            Assert.assertNotEquals(user2,user3);
        } finally {
            session.close();
        }
    }
}
