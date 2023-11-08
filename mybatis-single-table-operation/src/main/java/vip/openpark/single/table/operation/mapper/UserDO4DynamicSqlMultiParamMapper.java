package vip.openpark.single.table.operation.mapper;

import org.apache.ibatis.annotations.Param;
import vip.openpark.single.table.operation.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * @author anthony
 * @version 2023/11/8 10:19
 */
public interface UserDO4DynamicSqlMultiParamMapper {
    List<UserDO> selectByMultiParamWithMap(Map<Object, Object> map);

    List<UserDO> selectByGetMultiParamWithMap(Map<Object, Object> map);

    List<UserDO> selectByMultiParamWithParam(@Param("codeParam") String code,
                                             @Param("usernameParam") String username);
}