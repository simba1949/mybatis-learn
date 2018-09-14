package top.simba1949.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import top.simba1949.common.UserDto;
import top.simba1949.mapper.UserMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author simba1949@outlook.com
 * @date 2018/9/5 19:24
 */
public class UserMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void findAllTest() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<UserDto> userDtos = mapper.findAllByResultMap();
        for (UserDto userDto : userDtos) {
            System.out.println(userDto);
        }
        sqlSession.close();
    }

    @Test
    public void findOneByIdTest(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<UserDto> userDtos = mapper.findAllByTypeAliases();
        for (UserDto userDto : userDtos) {
            System.out.println(userDto.toString());
        }
        sqlSession.close();
    }
}
