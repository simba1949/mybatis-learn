package vip.openpark.pagehelper.mapper;

import org.apache.ibatis.session.RowBounds;
import vip.openpark.pagehelper.domain.UserDO;

import java.util.List;

public interface UserDOMapper {

	List<UserDO> selectByRowBounds(RowBounds rowBounds);

	List<UserDO> selectByStartPage();

	List<UserDO> selectByOffsetPage();
}