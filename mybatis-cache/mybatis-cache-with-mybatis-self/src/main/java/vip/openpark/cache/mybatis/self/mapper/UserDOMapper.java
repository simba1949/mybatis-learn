package vip.openpark.cache.mybatis.self.mapper;

import vip.openpark.cache.mybatis.self.domain.UserDO;

/**
 * @author anthony
 * @version 2023/11/9 19:39
 */
public interface UserDOMapper {

    UserDO selectByPK(long id);

    void updateByPk(UserDO userDO);

    UserDO selectByPKWithFlushCacheIsTrue(long id);
}