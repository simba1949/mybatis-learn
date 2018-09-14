package top.simba1949.mapper;

import org.apache.ibatis.annotations.Param;
import top.simba1949.common.CountryDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author simba1949@outlook.com
 * @date 2018/9/6 16:21
 */
public interface CountryMapper {

    void insertStatic();

    void batchSetParam(@Param("sets") Set<CountryDto> sets);
    void batchSet(Set<CountryDto> sets);

    void batchMapParam(@Param("maps") Map<String,String> maps);
    void batchMap(@Param("map") Map<String,String> maps);

    void batchInsertParam(@Param("countryDtos") List<CountryDto> countryDtos);
    void batchInsert(List<CountryDto> countryDtos);

    List<CountryDto> select(@Param("dto1")CountryDto dto1,@Param("dto2")CountryDto dto2);
    /**
     *
     * @param countryDto
     */
    void insertCountryDto(CountryDto countryDto);

    /**
     *
     * @param countryDto
     */
    void insertByJdbc(CountryDto countryDto);

    /**
     *
     * @return
     */
    void insertBySelectKey(CountryDto countryDto);

    List<HashMap<String,Object>> getType();
}
