package vip.openpark.interceptor.interceptor;

import java.lang.annotation.*;

/**
 * 脱敏字段标识注解
 *
 * @author anthony
 * @version 2023/11/12 20:45
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface OpenDesensitizationAnnotation {
}