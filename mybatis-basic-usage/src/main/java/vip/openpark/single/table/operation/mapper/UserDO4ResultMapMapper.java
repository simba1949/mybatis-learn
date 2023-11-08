package vip.openpark.single.table.operation.mapper;

import vip.openpark.single.table.operation.domain.UserDO;

import java.util.List;

/**
 * @author anthony
 * @version 2023/11/8 15:33
 */
public interface UserDO4ResultMapMapper {

    List<UserDO> selectAll();
}