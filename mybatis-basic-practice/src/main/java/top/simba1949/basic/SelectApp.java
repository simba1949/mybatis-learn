package top.simba1949.basic;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.simba1949.basic.mapper.SysUserMapper4Select;

import java.io.IOException;
import java.io.Reader;

/**
 * @author anthony
 * @date 2021/2/2
 */
public class SelectApp {
    public static void main(String[] args) throws IOException {
        // 读取数据库配置
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        // 通过Session工厂获取连接
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper4Select mapper = sqlSession.getMapper(SysUserMapper4Select.class);

        selectAllByAlias(mapper);
        selectAllByResultMap(mapper);

        // 提交结果
        sqlSession.commit();
        // 关闭Session
        sqlSession.close();
    }

    /**
     * 通过 别名 映射查询
     * @param mapper
     */
    public static void selectAllByAlias(SysUserMapper4Select mapper){
        mapper.selectAllByAlias().forEach(System.out::println);
        System.out.println("--------------------------------");
    }

    /**
     * 通过resultMap映射
     * @param mapper
     */
    public static void selectAllByResultMap(SysUserMapper4Select mapper){
        mapper.selectAllByResultMap().forEach(System.out::println);
    }
}
