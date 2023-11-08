package vip.openpark.single.table.operation.mapper;

import org.apache.ibatis.annotations.Param;
import vip.openpark.single.table.operation.domain.UserDO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author anthony
 * @version 2023/11/8 11:08
 */
public interface UserDO4DynamicSqlIfMapper {

    List<UserDO> select(@Param("usernameStrParam") String username,
                        @Param("genderListParam") List<Byte> genders,
                        @Param("birthdayStartLocalDateTimeParam") LocalDateTime birthdayStart,
                        @Param("birthdayEndLocalDateTimeParam") LocalDateTime birthdayEnd,
                        @Param("blEnableSetParam") Set<Byte> blEnables,
                        @Param("creatorMapParam") Map<Object, Object> creators
    );
}