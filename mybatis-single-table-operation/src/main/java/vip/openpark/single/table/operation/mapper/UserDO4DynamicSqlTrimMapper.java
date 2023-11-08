package vip.openpark.single.table.operation.mapper;

import vip.openpark.single.table.operation.domain.UserDO;

/**
 * @author anthony
 * @version 2023/11/8 15:01
 */
public interface UserDO4DynamicSqlTrimMapper {

    int updateWithSetAndWhere(UserDO userDO);

    int updateWithTrim(UserDO userDO);
}
