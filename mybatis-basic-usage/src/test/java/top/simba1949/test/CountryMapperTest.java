package top.simba1949.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import top.simba1949.common.CountryDto;
import top.simba1949.mapper.CountryMapper;

import java.util.*;

/**
 * @author simba1949@outlook.com
 * @date 2018/9/6 16:24
 */
public class CountryMapperTest extends BaseMapper{

    @Test
    public void insertCountryDtoTest(){
        SqlSession sqlSession = getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        CountryDto countryDto = new CountryDto();
        countryDto.setCountryName("意大利");
        countryDto.setCountryCode("YDL");
        countryMapper.insertCountryDto(countryDto);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void insertByJdbcTest(){
        SqlSession sqlSession = getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        CountryDto countryDto = new CountryDto();
        countryDto.setCountryName("意大利");
        countryDto.setCountryCode("YDL");
        countryMapper.insertByJdbc(countryDto);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void insertBySelectKeyTest(){
        SqlSession sqlSession = getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        CountryDto countryDto = new CountryDto();
        countryDto.setCountryName("意大利");
        countryDto.setCountryCode("YDL");
        countryMapper.insertBySelectKey(countryDto);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getTypeTest(){
        SqlSession sqlSession = getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        List<HashMap<String, Object>> type = countryMapper.getType();
        for (HashMap<String, Object> map : type) {
            for (String key : map.keySet()) {
                Object value = map.get(key);
                System.out.println("key=" + key + " value=" + value);
            }
        }
        sqlSession.close();
    }

    @Test
    public void batchListTest(){
        SqlSession sqlSession = getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        List<CountryDto> countryDtos = new ArrayList<>();
        for (int i = 0; i < 5 ; i++){
            CountryDto countryDto = new CountryDto();
            countryDto.setCountryName("美国" + i);
            countryDto.setCountryCode("US" + i);
            countryDtos.add(countryDto);
        }
        countryMapper.batchInsert(countryDtos);

        sqlSession.close();
    }

    @Test
    public void batchMapTest(){
        SqlSession sqlSession = getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        Map<String,String> maps = new HashMap<>();
        for (int i = 0; i < 5; i++){
            maps.put("美国" + i, "US" + i);
        }
        countryMapper.batchMap(maps);

        sqlSession.close();
    }

    @Test
    public void batchSetTest(){
        SqlSession sqlSession = getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        HashSet<CountryDto> countryDtos = new HashSet<>();
        for (int i = 0; i < 6 ; i++){
            CountryDto countryDto = new CountryDto();
            countryDto.setCountryName("美国" + i);
            countryDto.setCountryCode("US" + i);
            countryDtos.add(countryDto);
        }
        countryMapper.batchSet(countryDtos);

        sqlSession.close();
    }

    @Test
    public void insertStaticTest(){
        SqlSession sqlSession = getSqlSession();
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        countryMapper.insertStatic();

        sqlSession.commit();
        sqlSession.close();
    }
}
