package vip.openpark.single.table.operation.mapper;

import vip.openpark.single.table.operation.domain.UserDO;

/**
 * @author anthony
 * @version 2023/11/7 14:43
 */
public interface UserDOMapper {
    int insertByCommon(UserDO userDO);

    int insertByJdbc(UserDO userDO);

    int insertBySelectKey(UserDO userDO);

    int insertByStaticFieldAndMethod();
}
