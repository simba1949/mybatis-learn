package vip.openpark.advanced.search.mapper;

import vip.openpark.advanced.search.domain.UserDO;

import java.util.List;

/**
 * @author anthony
 * @version 2023/11/9 19:39
 */
public interface UserDO4SingleTableMapper {

    List<UserDO> selectWithDefault();

    List<UserDO> selectWithAlias();

    List<UserDO> selectWithResultMap();
}