package vip.openpark.single.table.operation.mapper;

import java.util.List;

/**
 * @author anthony
 * @version 2023/11/8 9:51
 */
public interface UserDO4DeleteMapper {
    int deleteByIds(List<Long> ids);
}