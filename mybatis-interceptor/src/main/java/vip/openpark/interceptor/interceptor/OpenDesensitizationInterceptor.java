package vip.openpark.interceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import vip.openpark.interceptor.domain.UserDO;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.List;

/**
 * 脱敏插件
 * @author anthony
 * @version 2023/11/12 20:43
 */
@Slf4j
@Intercepts({
        @Signature(type = ResultSetHandler.class,
                method = "handleResultSets",
                args = {Statement.class})})
public class OpenDesensitizationInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("Interceptor.intercept");
        // 首先获取到结果集
        List<Object> proceedList = (List<Object>) invocation.proceed();
        // 对结果集进行修饰
        proceedList.forEach(this::desensitizationCore);
        return proceedList;
    }

    /**
     * 脱敏核心业务：这里这是简单示例，具体业务具体实现
     * 姓名：只显示第一个字
     * 手机号码：中间四位脱敏
     * 密码：全脱敏
     *
     * @param source
     */
    private void desensitizationCore(Object source) {
        log.info("Interceptor.desensitizationCore");
        try {
            reflectionDesensitization(source);
        } catch (Throwable e) {
            log.error("the throwable is {}", e.getMessage(), e);
        }
    }

    /**
     * 简易脱敏
     *
     * @param source
     */
    private void simpleDesensitization(Object source) {
        if (source instanceof UserDO) {
            UserDO user = (UserDO) source;
            // 用户名脱敏
            String username = user.getUsername();
            if (null != username && username.length() > 1) {
                String usernameVal = username.charAt(0) + "**";
                user.setUsername(usernameVal);
            }
            // 密码脱敏
            user.setPassword("******");
            // 手机号码脱敏
            String phone = user.getPhone();
            if (null != phone && phone.length() > 0) {
                String phoneVal = phone.substring(0, 3) + "****" + phone.substring(7);
                user.setPhone(phoneVal);
            }
        }
    }

    /**
     * 通过反射脱敏
     *
     * @param source
     */
    private void reflectionDesensitization(Object source) throws IllegalAccessException {
        Class<?> aClass = source.getClass();
        // mybatis 提供的，带有具体属性值的对象，需要传对象的值
        MetaObject metaObject = SystemMetaObject.forObject(source);

        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            OpenDesensitizationAnnotation annotation = field.getAnnotation(OpenDesensitizationAnnotation.class);
            if (null != annotation) {
                String fieldName = field.getName();
                Object value = metaObject.getValue(fieldName);
                if (String.class == metaObject.getGetterType(fieldName) && null != value) {
                    String newVal = null;
                    if ("username".equals(fieldName)) {
                        newVal = value.toString().charAt(0) + "**";
                    } else if ("password".equals(fieldName)) {
                        newVal = "*******";
                    } else if ("phone".equals(fieldName)) {
                        newVal = value.toString().substring(0, 3) + "****" + value.toString().substring(7);
                    }
                    // 重新赋值
                    metaObject.setValue(fieldName, newVal);
                }
            }
        }
    }
}