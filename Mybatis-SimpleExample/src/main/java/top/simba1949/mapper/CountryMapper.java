package top.simba1949.mapper;

import top.simba1949.common.CountryDto;

import java.util.List;

/**
 * @author simba1949@outlook.com
 * @date 2018/9/5 16:48
 */
public interface CountryMapper {

    List<CountryDto> findAll();
}
