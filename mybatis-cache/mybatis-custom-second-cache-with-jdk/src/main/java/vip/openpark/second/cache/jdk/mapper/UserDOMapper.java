package vip.openpark.second.cache.jdk.mapper;

import vip.openpark.second.cache.jdk.domain.UserDO;

/**
 * @author anthony
 * @version 2023/11/9 19:39
 */
public interface UserDOMapper {

    UserDO selectByPK(long id);
}