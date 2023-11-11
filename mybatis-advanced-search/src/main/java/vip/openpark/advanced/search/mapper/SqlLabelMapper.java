package vip.openpark.advanced.search.mapper;

import vip.openpark.advanced.search.domain.UserDO;

import java.util.List;

/**
 * <sql> <include> <property> 标签的使用
 *
 * @author anthony
 * @version 2023/11/11 10:35
 */
public interface SqlLabelMapper {

    List<UserDO> selectBySqlLabel();
}