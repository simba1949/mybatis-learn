package top.simba1949.basic;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.simba1949.basic.mapper.OneSysUser2OneSysRole4SelectMapper;

import java.io.IOException;
import java.io.Reader;

/**
 * @author anthony
 * @date 2021/2/4
 */
public class One2OneSelectApp {
    public static void main(String[] args) throws IOException {
        // 读取数据库配置
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        // 通过Session工厂获取连接
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OneSysUser2OneSysRole4SelectMapper mapper = sqlSession.getMapper(OneSysUser2OneSysRole4SelectMapper.class);

        findAllByTypeAliases(mapper);
        System.out.println("------------------------------------");
        findAllByResultMapSimple(mapper);
        System.out.println("------------------------------------");
        findAllByResultMapAssociation(mapper);
        System.out.println("------------------------------------");
        findAllByResultMapAssociationSpanXml(mapper);
        System.out.println("------------------------------------");

        // 提交结果
        sqlSession.commit();
        // 关闭Session
        sqlSession.close();
    }

    /**
     * 一对一映射，通过别名映射
     * @param mapper
     */
    public static void findAllByTypeAliases(OneSysUser2OneSysRole4SelectMapper mapper){
        mapper.findAllByTypeAliases().forEach(System.out::println);
    }

    /**
     * 一对一映射，通过简单的 resultMap 映射
     * @param mapper
     */
    public static void findAllByResultMapSimple(OneSysUser2OneSysRole4SelectMapper mapper){
        mapper.findAllByResultMapSimple().forEach(System.out::println);
    }

    /**
     * 一对一映射，通过 resultMap 中 Association 映射
     * @param mapper
     */
    public static void findAllByResultMapAssociation(OneSysUser2OneSysRole4SelectMapper mapper){
        mapper.findAllByResultMapAssociation().forEach(System.out::println);
    }

    /**
     * 一对一映射，通过 resultMap 中 Association 跨 *mapper.xml 映射
     * @param mapper
     */
    public static void findAllByResultMapAssociationSpanXml(OneSysUser2OneSysRole4SelectMapper mapper){
        mapper.findAllByResultMapAssociationSpanXml().forEach(System.out::println);
    }
}
