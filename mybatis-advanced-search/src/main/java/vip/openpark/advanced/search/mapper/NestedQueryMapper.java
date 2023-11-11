package vip.openpark.advanced.search.mapper;

import vip.openpark.advanced.search.domain.OneToManyRoleDO;
import vip.openpark.advanced.search.domain.OneToOneUserDO;

import java.util.List;

/**
 * @author anthony
 * @version 2023/11/11 11:22
 */
public interface NestedQueryMapper {

    /**
     * 一对一嵌套查询
     *
     * @return
     */
    List<OneToOneUserDO> oneToOneNestedQueryUser();

    /**
     * 一对多嵌套查询
     *
     * @return
     */
    List<OneToManyRoleDO> oneToManyNestedQueryRole();
}