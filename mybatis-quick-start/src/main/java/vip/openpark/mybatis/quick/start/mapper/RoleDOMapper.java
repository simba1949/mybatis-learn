package vip.openpark.mybatis.quick.start.mapper;

import vip.openpark.infrastructure.domain.RoleDO;

import java.util.List;

/**
 * @author anthony
 * @version 2018/9/5 16:48
 */
public interface RoleDOMapper {
    List<RoleDO> findAll();
}