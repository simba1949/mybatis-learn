package vip.openpark.advanced.search.mapper;

import vip.openpark.advanced.search.domain.OneToManyUserDO;

import java.util.List;

/**
 * @author anthony
 * @version 2023/11/10 17:17
 */
public interface OneToManyUserDOMapper {
	List<OneToManyUserDO> selectWithOneXml();

	List<OneToManyUserDO> selectWithManyXml();
}