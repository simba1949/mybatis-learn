package vip.openpark.generator.cmd.mapper;

import java.util.List;
import vip.openpark.generator.cmd.domain.UserDO;

public interface UserDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserDO row);

    UserDO selectByPrimaryKey(Long id);

    List<UserDO> selectAll();

    int updateByPrimaryKey(UserDO row);
}