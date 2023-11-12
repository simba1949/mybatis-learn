package vip.openpark.generator.java.mapper;

import java.util.List;
import vip.openpark.generator.java.domain.UserDO;

public interface UserDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserDO row);

    UserDO selectByPrimaryKey(Long id);

    List<UserDO> selectAll();

    int updateByPrimaryKey(UserDO row);
}