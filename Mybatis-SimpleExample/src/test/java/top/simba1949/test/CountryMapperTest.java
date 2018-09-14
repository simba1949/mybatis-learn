package top.simba1949.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import top.simba1949.common.CountryDto;
import top.simba1949.mapper.CountryMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author simba1949@outlook.com
 * @date 2018/9/5 19:24
 */
public class CountryMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void findAllTest() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
        List<CountryDto> countryDtos = mapper.findAll();
        for (CountryDto countryDto : countryDtos) {
            System.out.println(countryDto);
        }

        sqlSession.close();
    }
}
