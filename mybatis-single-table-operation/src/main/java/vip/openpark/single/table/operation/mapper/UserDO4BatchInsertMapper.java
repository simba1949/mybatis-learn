package vip.openpark.single.table.operation.mapper;

import org.apache.ibatis.annotations.Param;
import vip.openpark.single.table.operation.domain.UserDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author anthony
 * @version 2023/11/7 16:48
 */
public interface UserDO4BatchInsertMapper {
    int batchInsertByList(List<UserDO> list);

    int batchInsertByListWithParam(@Param("listParam") List<UserDO> list);

    int batchInsertBySet(Set<UserDO> set);

    int batchInsertBySetWithParam(@Param("setParam") Set<UserDO> set);

    int batchInsertByMap(Map<String, Object> maps);

    int batchInsertByMapWithParam(@Param("mapParam") Map<String, Object> maps);
}