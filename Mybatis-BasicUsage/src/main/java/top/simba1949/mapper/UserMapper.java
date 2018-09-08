package top.simba1949.mapper;

import top.simba1949.common.UserDto;

import java.util.List;

/**
 * @author v_jiayytian@tencent.com
 * @date 2018/9/6 10:48
 */
public interface UserMapper {

    List<UserDto> findAllByResultMap();

    List<UserDto> findAllByTypeAliases();

    /**
     * 一对多
     * @param id
     * @return
     */
    UserDto findById(Long id);
}
