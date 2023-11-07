package top.simba1949.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.Reader;

/**
 * @author simba1949@outlook.com
 * @date 2018/9/6 15:31
 */
public class BaseMapper {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public static void init() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
