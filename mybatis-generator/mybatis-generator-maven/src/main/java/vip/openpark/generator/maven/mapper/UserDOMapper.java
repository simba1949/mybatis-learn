package vip.openpark.generator.maven.mapper;

import java.util.List;
import vip.openpark.generator.maven.domain.UserDO;

public interface UserDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserDO row);

    UserDO selectByPrimaryKey(Long id);

    List<UserDO> selectAll();

    int updateByPrimaryKey(UserDO row);
}