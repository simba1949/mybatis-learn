package top.simba1949;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.simba1949.mapper.SysUserMapper;
import top.simba1949.model.SysUser;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author anthony
 * @date 2021/1/31 13:57
 */
public class Application {
    public static void main(String[] args) throws IOException {
        // 读取数据库配置
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        // 通过Session工厂获取连接
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行SQL获取结果
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        // selectAll(mapper);
        // get(mapper);
        // update(mapper);
        // insert(mapper);
        delete(mapper);
        // 提交结果
        sqlSession.commit();
        // 关闭Session
        sqlSession.close();
    }

    public static void selectAll(SysUserMapper mapper){
        List<SysUser> sysUserList = mapper.selectAll();
        sysUserList.forEach(System.out::println);
    }

    public static void get(SysUserMapper mapper){
        System.out.println(mapper.get(1L));
    }

    public static void update(SysUserMapper mapper){
        SysUser sysUser = mapper.get(1L);
        sysUser.setUserInfo("userInfo update");
        mapper.update(sysUser);
    }

    public static void insert(SysUserMapper mapper){
        SysUser insert = new SysUser();
        insert.setUsername("insert user name");
        insert.setUserPassword("insert user password");
        insert.setUserEmail("insert user email");
        insert.setUserInfo("insert user info");
        insert.setHeadImg("insert head img");
        insert.setCreateTime(LocalDateTime.now());

        mapper.insert(insert);
        System.out.println(insert.getId());
    }

    public static void delete(SysUserMapper mapper){
        mapper.delete(1005L);
    }
}
