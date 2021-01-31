package top.simba1949;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.simba1949.mapper.SysUserMapper;
import top.simba1949.model.SysUser;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
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
        List<SysUser> sysUserList = mapper.selectAll();
        sysUserList.forEach(System.out::println);
        // 关闭Session
        sqlSession.close();
    }
}
