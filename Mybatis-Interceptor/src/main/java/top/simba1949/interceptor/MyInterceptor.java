package top.simba1949.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @SuppressWarnings({"rawtypes","unchecked"})
 *      作用：用于抑制编译器产生警告信息。
 *
 * 拦截器签名：
 * @Signature 中 type：设置拦截的接口，可选值：
 *                      Executor.class (update/query/flushStatements/commit/rollback/getTransaction/close/isClosed)
 *                      ParameterHandler.class (getParameterObject/setParameters)
 *                      ResultSetHandler.class (handleResultSets/handleCursorResultSets/handleOutputParameters)
 *                      StatementHandler.class (prepare/parameterize/batch/update/query)
 *                 method：设置拦截接口中的方法名，可选值为上述接口括号内的值
 *                 args：设置拦截方法的参数类型数组，通过方法名和参数类型可以确定唯一一个方法
 * @author v_jiayytian@tencent.com
 * @date 2018/9/5 17:12
 */
@SuppressWarnings({"rawtypes","unchecked"})
@Intercepts({
        @Signature(type = Executor.class,method = "query",args = {Object.class})
})
public class MyInterceptor implements Interceptor {
    /**
     * intercept方法是在进行拦截的时候要执行的方法。
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取当前被拦截的对象
        Object target = invocation.getTarget();
        // 获取当前被拦截的方法
        Method method = invocation.getMethod();
        // 返回被拦截方法中的参数
        Object[] args = invocation.getArgs();
        // 执行被拦截对象真正的方法
        Object proceed = invocation.proceed();
        return null;
    }

    /**
     * 该方法是拦截器用于封装目标对象的，通过该方法我们可以返回目标对象本身，也可以返回一个它的代理。
     * 该方法会在创建被拦截的接口实现类时被调用
     * @param target:代表拦截器要拦截的对象
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    /**
     * mybatis配置文件中configuration中的配置的指定属性值。
     * 用于传递插件的参数，配置后的参数在拦截器初始化时会通过 setProperties 方法传递给拦截器，
     * 在拦截器中通过 properteis 获取配置中的值
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {

    }
}
