package vip.openpark.advanced.search.mapper;

import vip.openpark.advanced.search.domain.OneToOneUserDO;

import java.util.List;

/**
 * @author anthony
 * @version 2023/11/9 21:11
 */
public interface OneToOneUserDOMapper {

	/**
	 * 查询数据，在同一个 XML 中，使用别名的方式查询数据
	 *
	 * @return
	 */
	List<OneToOneUserDO> selectWithOneXmlAndAlias();

	/**
	 * 查询数据，在同一个 XML 中，使用 <ResultMap> 标签和别名的方式查询数据
	 *
	 * @return
	 */
	List<OneToOneUserDO> selectWithOneXmlAndResultMapAndAlias();

	/**
	 * 查询数据，在同一个 XML 中，使用 <resultMap> 标签和 <association> 标签查询数据
	 *
	 * @return
	 */
	List<OneToOneUserDO> selectWithOneXmlAndResultMapAndAssociation();

	/**
	 * 查询数据，在不同的 XML 中，使用 <resultMap> 标签和 <association> 标签查询数据
	 *
	 * @return
	 */
	List<OneToOneUserDO> selectWithManyXmlAndResultMapAndAssociation();
}