package vip.openpark.advanced.search.mapper;

import vip.openpark.advanced.search.domain.OneToManyRoleDO;

import java.util.List;

/**
 * @author anthony
 * @version 2023/11/10 17:17
 */
public interface OneToManyRoleDOMapper {
	List<OneToManyRoleDO> selectWithOneXml();

	List<OneToManyRoleDO> selectWithManyXml();
}