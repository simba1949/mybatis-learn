package vip.openpark.cache.ehcache.mapper;

import vip.openpark.cache.ehcache.domain.UserDO;

/**
 * @author anthony
 * @version 2023/11/11 16:19
 */
public interface UserDOMapper {
    UserDO selectByPK(long id);
}