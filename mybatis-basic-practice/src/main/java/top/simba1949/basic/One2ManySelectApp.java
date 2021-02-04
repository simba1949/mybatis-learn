package top.simba1949.basic;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.simba1949.basic.mapper.OneSysUser2ManySysRole4SelectMapper;

import java.io.IOException;
import java.io.Reader;

/**
 * select一对多映射(collection) 2种映射方式
 * @author anthony
 * @date 2021/2/4
 */
public class One2ManySelectApp {
    public static void main(String[] args) throws IOException {
        // 读取数据库配置
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        // 通过Session工厂获取连接
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OneSysUser2ManySysRole4SelectMapper mapper = sqlSession.getMapper(OneSysUser2ManySysRole4SelectMapper.class);

//        mapper.findAllByResultMapCollectionSimple().forEach(System.out::println);
        System.out.println("---------------------------------------------");
        mapper.findAllByResultMapCollectionSpanXml().forEach(System.out::println);
        // 提交结果
        sqlSession.commit();
        // 关闭Session
        sqlSession.close();
    }
}
