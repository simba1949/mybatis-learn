package vip.openpark.generator.java.config;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;

/**
 * @author anthony
 * @version 2023/11/12 11:03
 */
public class MybatisGenerator {
    public static void main(String[] args) throws Exception {
        ArrayList<String> warnings = new ArrayList<>();
        boolean overWrite = true;

        String path = MybatisGenerator.class.getClassLoader().getResource("").getPath();
        File configFile = new File(path + "/generator-config.xml");

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);

        DefaultShellCallback callback = new DefaultShellCallback(overWrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}