package vip.openpark.cache.redis.mapper;

import vip.openpark.cache.redis.domain.UserDO;

/**
 * @author anthony
 * @version 2023/11/11 16:56
 */
public interface UserDOMapper {
    UserDO selectByPK(long id);
}