package vip.openpark.interceptor.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import vip.openpark.interceptor.domain.UserDO;

import java.util.List;

public interface UserDOMapper extends Mapper<UserDO> {

    List<UserDO> selectByPage(@Param("pageNum") Integer PageNum,
                              @Param("pageSize") Integer pageSize);
}