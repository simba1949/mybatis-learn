# Mybatis 面试题

1. **#{} 和 ${} 的区别**

   ```
   #{}是预编译处理，${}是字符串替换。
   Mybatis在处理#{}时，会将sql中的#{}替换为?号，调用PreparedStatement的set方法来赋值；
   Mybatis在处理${}时，就是把${}替换成变量的值。
   使用#{}可以有效的防止SQL注入，提高系统安全性。
   ```

2. **通常一个Xml映射文件，都会写一个Dao接口与之对应，请问，这个Dao接口的工作原理是什么？Dao接口里的方法，参数不同时，方法能重载吗？**

   ```
   Dao接口，就是人们常说的Mapper接口，接口的全限名，就是映射文件中的namespace的值，接口的方法名，就是映射文件中MappedStatement的id值，接口方法内的参数，就是传递给sql的参数。
   
   Mapper接口是没有实现类的，当调用接口方法时，接口全限名+方法名拼接字符串作为key值，可唯一定位一个MappedStatement，
   
   举例：com.mybatis3.mappers.StudentDao.findStudentById，可以唯一找到namespace为com.mybatis3.mappers.StudentDao下面id = findStudentById的MappedStatement。
   
   在Mybatis中，每一个<select>、<insert>、<update>、<delete>标签，都会被解析为一个MappedStatement对象。
   
   Dao接口里的方法，是不能重载的，因为是全限名+方法名的保存和寻找策略。
   
   Dao接口的工作原理是JDK动态代理，Mybatis运行时会使用JDK动态代理为Dao接口生成代理proxy对象，代理对象proxy会拦截接口方法，转而执行MappedStatement所代表的sql，然后将sql执行结果返回。
   ```

3. **Mybatis是如何进行分页的？分页插件的原理是什么？**

   ```
   Mybatis使用RowBounds对象进行分页，它是针对ResultSet结果集执行的内存分页，而非物理分页，可以在sql内直接书写带有物理分页的参数来完成物理分页功能，也可以使用分页插件来完成物理分页。
   
   分页插件的基本原理是使用Mybatis提供的插件接口，实现自定义插件，在插件的拦截方法内拦截待执行的sql，然后重写sql，根据dialect方言，添加对应的物理分页语句和物理分页参数。
   ```

4. **Mybatis是如何将sql执行结果封装为目标对象并返回的？都有哪些映射形式？**

   ```
   第一种是使用<resultMap>标签，逐一定义列名和对象属性名之间的映射关系。
   
   第二种是使用sql列的别名功能，将列别名书写为对象属性名，比如T_NAME AS NAME，对象属性名一般是name，小写，但是列名不区分大小写，Mybatis会忽略列名大小写，智能找到与之对应对象属性名，你甚至可以写成T_NAME AS NaMe，Mybatis一样可以正常工作。
   
   有了列名与属性名的映射关系后，Mybatis通过反射创建对象，同时使用反射给对象的属性逐一赋值并返回，那些找不到映射关系的属性，是无法完成赋值的。
   ```

5. **如何获取自动生成的(主)键值?**

   ```
   <insert id=”insertname” usegeneratedkeys=”true” keyproperty=”id”> 
   	insert into names (name) values (#{name}) 
   </insert>
   ```

6. **Mybatis的Xml映射文件中，不同的Xml映射文件，id是否可以重？**

   ```
   不同的Xml映射文件，如果配置了namespace，那么id可以重复；如果没有配置namespace，那么id不能重复；毕竟namespace不是必须的，只是最佳实践而已。
    
   原因就是namespace+id是作为Map<String, MappedStatement>的key使用的，如果没有namespace，就剩下id，那么，id重复会导致数据互相覆盖。
   
   有了namespace，自然id就可以重复，namespace不同，namespace+id自然也就不同。
   ```

7. **为什么说Mybatis是半自动ORM映射工具？它与全自动的区别在哪里？**

   ```
   Hibernate属于全自动ORM映射工具，使用Hibernate查询关联对象或者关联集合对象时，可以根据对象关系模型直接获取，所以它是全自动的。
   而Mybatis在查询关联对象或关联集合对象时，需要手动编写sql来完成，所以，称之为半自动ORM映射工具。
   ```

8. **简述Mybatis的插件运行原理，以及如何编写一个插件。**

   ```
   Mybatis仅可以编写针对ParameterHandler、ResultSetHandler、StatementHandler、Executor这4种接口的插件，Mybatis使用JDK的动态代理，为需要拦截的接口生成代理对象以实现接口方法拦截功能，每当执行这4种接口对象的方法时，就会进入拦截方法，具体就是InvocationHandler的invoke()方法，当然，只会拦截那些你指定需要拦截的方法。
   
   实现Mybatis的Interceptor接口并复写intercept()方法，然后在给插件编写注解，指定要拦截哪一个接口的哪些方法即可，记住，别忘了在配置文件中配置你编写的插件。
   ```

9. **Mybatis是否支持延迟加载？如果支持，它的实现原理是什么**

   ```
   Mybatis仅支持association关联对象和collection关联集合对象的延迟加载，association指的就是一对一，collection指的就是一对多查询。
   
   在Mybatis配置文件中，可以配置是否启用延迟加载lazyLoadingEnabled=true|false。
   
   它的原理是，使用CGLIB创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，比如调用a.getB().getName()，拦截器invoke()方法发现a.getB()是null值，那么就会单独发送事先保存好的查询关联B对象的sql，把B查询上来，然后调用a.setB(b)，于是a的对象b属性就有值了，接着完成a.getB().getName()方法的调用。这就是延迟加载的基本原理。
   
   当然了，不光是Mybatis，几乎所有的包括Hibernate，支持延迟加载的原理都是一样的。
   ```

10. **Mybatis中如何执行批处理？**

    ```
    使用BatchExecutor完成批处理。
    ```

11. **Mybatis都有哪些Executor执行器？它们之间的区别是什么？**

    ```
    Mybatis有三种基本的Executor执行器，SimpleExecutor、ReuseExecutor、BatchExecutor。
    
    SimpleExecutor：每执行一次update或select，就开启一个Statement对象，用完立刻关闭Statement对象。
    
    ReuseExecutor：执行update或select，以sql作为key查找Statement对象，存在就使用，不存在就创建，用完后，不关闭Statement对象，而是放置于Map<String, Statement>内，供下一次使用。简言之，就是重复使用Statement对象。
    
    BatchExecutor：执行update（没有select，JDBC批处理不支持select），将所有sql都添加到批处理中（addBatch()），等待统一执行（executeBatch()），它缓存了多个Statement对象，每个Statement对象都是addBatch()完毕后，等待逐一执行executeBatch()批处理。与JDBC批处理相同。
    
    作用范围：Executor的这些特点，都严格限制在SqlSession生命周期范围内。
    ```

12. **Mybatis中如何指定使用哪一种Executor执行器？**

    ```
    在Mybatis配置文件中，可以指定默认的ExecutorType执行器类型，也可以手动给DefaultSqlSessionFactory的创建SqlSession的方法传递ExecutorType类型参数。
    ```

13. **Mybatis是否可以映射Enum枚举类？**

    ```
    Mybatis可以映射枚举类，不单可以映射枚举类，Mybatis可以映射任何对象到表的一列上。映射方式为自定义一个TypeHandler，实现TypeHandler的setParameter()和getResult()接口方法。
    
    TypeHandler有两个作用，一是完成从javaType至jdbcType的转换，二是完成jdbcType至javaType的转换，体现为setParameter()和getResult()两个方法，分别代表设置sql问号占位符参数和获取列查询结果。
    ```

14. **Mybatis映射文件中，如果A标签通过include引用了B标签的内容，请问，B标签能否定义在A标签的后面，还是说必须定义在A标签的前面？**

    ```
    虽然Mybatis解析Xml映射文件是按照顺序解析的，但是，被引用的B标签依然可以定义在任何地方，Mybatis都可以正确识别。
    
    原理是，Mybatis解析A标签，发现A标签引用了B标签，但是B标签尚未解析到，尚不存在，此时，Mybatis会将A标签标记为未解析状态，然后继续解析余下的标签，包含B标签，待所有标签解析完毕，Mybatis会重新解析那些被标记为未解析的标签，此时再解析A标签时，B标签已经存在，A标签也就可以正常解析完成了。
    ```

15. **简述Mybatis的Xml映射文件和Mybatis内部数据结构之间的映射关系？**

    ```
    Mybatis将所有Xml配置信息都封装到All-In-One重量级对象Configuration内部。在Xml映射文件中，<parameterMap>标签会被解析为ParameterMap对象，其每个子元素会被解析为ParameterMapping对象。
    
    <resultMap>标签会被解析为ResultMap对象，其每个子元素会被解析为ResultMapping对象。
    
    每一个<select>、<insert>、<update>、<delete>标签均会被解析为MappedStatement对象，标签内的sql会被解析为BoundSql对象。
    ```

16.  **JDBC编程有哪些不足之处，MyBatis是如何解决这些问题的？**

    ```
    1、数据库链接创建、释放频繁造成系统资源浪费从而影响系统性能，如果使用数据库链接池可解决此问题。
    
    解决：在SqlMapConfig.xml中配置数据链接池，使用连接池管理数据库链接。
    
    2、Sql语句写在代码中造成代码不易维护，实际应用sql变化的可能较大，sql变动需要改变java代码。
    
    解决：将Sql语句配置在XXXXmapper.xml文件中与java代码分离。
    
    3、向sql语句传参数麻烦，因为sql语句的where条件不一定，可能多也可能少，占位符需要和参数一一对应。 
    
    解决： Mybatis自动将java对象映射至sql语句。
    
    4、对结果集解析麻烦，sql变化导致解析代码变化，且解析前需要遍历，如果能将数据库记录封装成pojo对象解析比较方便。
    
    解决：Mybatis自动将sql执行结果映射至java对象。
    ```

17. **MyBatis编程步骤是什么样的？**

    ```
    1、 创建SqlSessionFactory
    2、 通过SqlSessionFactory创建SqlSession
    3、 通过sqlsession执行数据库操作
    4、 调用session.commit()提交事务
    5、 调用session.close()关闭会话
    ```

18. **MyBatis与Hibernate有哪些不同？**

    ```
    1.Mybatis和hibernate不同，它不完全是一个ORM框架，因为MyBatis需要程序员自己编写Sql语句。
    
    mybatis可以通过XML或注解方式灵活配置要运行的sql语句，并将java对象和sql语句映射生成最终执行的sql，最后将sql执行的结果再映射生成java对象。
    
    2.Mybatis学习门槛低，简单易学，程序员直接编写原生态sql，可严格控制sql执行性能，灵活度高，非常适合对关系数据模型要求不高的软件开发。
    
    例如互联网软件、企业运营类软件等，因为这类软件需求变化频繁，一但需求变化要求成果输出迅速。
    
    但是灵活的前提是mybatis无法做到数据库无关性，如果需要实现支持多种数据库的软件则需要自定义多套sql映射文件，工作量大。
    
    3.Hibernate对象/关系映射能力强，数据库无关性好，对于关系模型要求高的软件（例如需求固定的定制化软件）如果用hibernate开发可以节省很多代码，提高效率。
    
    但是Hibernate的学习门槛高，要精通门槛更高，而且怎么设计O/R映射，在性能和对象模型之间如何权衡，以及怎样用好Hibernate需要具有很强的经验和能力才行。
    
    总之，按照用户的需求在有限的资源环境下只要能做出维护性、扩展性良好的软件架构都是好架构，所以框架只有适合才是最好。 
    ```

19. **使用MyBatis的mapper接口调用时有哪些要求？**

    ```
    1、Mapper接口方法名和mapper.xml中定义的每个sql的id相同。
    2、Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同。
    3、Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同。
    4、Mapper.xml文件中的namespace即是mapper接口的类路径。
    ```

20. 
