package cn.netinnet;

import cn.netinnet.server.ProjectConstant_Client1;
import com.google.common.base.CaseFormat;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static cn.netinnet.server.ProjectConstant_Client1.*;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGenerator {
    //JDBC配置，请修改为你项目的实际配置
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/nin_gx?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "123456";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver"; //"com.mysql.cj.jdbc.Driver";

    private static final String PROJECT_PATH = System.getProperty("user.dir") ;//项目在硬盘上的基础路径
    private static final String TEMPLATE_PATH = System.getProperty("user.dir") ;//模板在硬盘上的基础路径
    private static final String TEMPLATE_FILE_PATH = TEMPLATE_PATH + "/src/test/java/resources/generator/clientTemplate";//模板位置

    private static final String JAVA_PATH = "/src/main/java"; //java文件路径
    private static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径

    private static final String PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);//生成的Service存放路径
    private static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);//生成的Service实现存放路径
    private static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);//生成的Controller存放路径

    private static final String AUTHOR = "administrator";//@author
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date

    public static void main(String[] args) {

//        for(String table: getAllTableNames()) {
//            genCode(table);
//        }

        genCode("nin_gx_notes");
        //genCodeByCustomModelName("输入表名","输入自定义Model名称");
    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     * @param tableNames 数据表名称...
     */
    public static void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            genCodeByCustomModelName(tableName, null);
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public static void genCodeByCustomModelName(String tableName, String modelName) {
        genModelAndMapper(tableName, modelName);
        genService(tableName, modelName);
//        genController(tableName, modelName);
        //getPrimaryKeyName(tableName);
    }


    public static void genModelAndMapper(String tableName, String modelName) {
        Context context = new Context(ModelType.FLAT);
        context.setId("Potato");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
        jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
        jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
        jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("cn.netinnet.server.PaginationPlugin");
        pluginConfiguration.addProperty("mappers", MAPPER_INTERFACE_REFERENCE);
        context.addPluginConfiguration(pluginConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(MODEL_PACKAGE);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + RESOURCES_PATH );
        sqlMapGeneratorConfiguration.setTargetPackage("mapper" +"/"+ ProjectConstant_Client1.MOUDLE);
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        // edit by xianlaioy at 20171211
        // 自定义类型转成 sql tinyint-->java int
        JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
        javaTypeResolverConfiguration.setConfigurationType("cn.netinnet.server.JavaTypeResolverDefault");
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);


        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(MAPPER_PACKAGE);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        if (StringUtils.isNotEmpty(modelName))tableConfiguration.setDomainObjectName(modelName);
        //GeneratedKey gk = new GeneratedKey("customer_id", "Mysql", true, null);
        GeneratedKey gk = new GeneratedKey(getPrimaryKeyName(tableName), "JDBC", true, null);

        tableConfiguration.setGeneratedKey(gk);

//        CommentGeneratorConfiguration commentGenerator = new CommentGeneratorConfiguration();
//        commentGenerator.addProperty("suppressDate", "true");
//        commentGenerator.addProperty("suppressAllComments", "true");cn.netinnet.server
        CommentGeneratorConfiguration  commentGeneratorConfiguration = new CommentGeneratorConfiguration();
        commentGeneratorConfiguration.setConfigurationType("cn.netinnet.server.GenCommentGenerator");
        context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
//        context.setCommentGeneratorConfiguration(commentGenerator);


        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<String>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        if (StringUtils.isEmpty(modelName)) modelName = tableNameConvertUpperCamel(tableName);
        System.out.println(modelName + ".java 生成成功");
        System.out.println(modelName + "Mapper.java 生成成功");
        System.out.println(modelName + "Mapper.xml 生成成功");
    }

    public static void genService(String tableName, String modelName) {
        try {
            System.out.println("path:"+PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE);

            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", BASE_PACKAGE);
            data.put("module", MOUDLE);
            data.put("keyName", tableNameConvertUpperCamel(getPrimaryKeyName(tableName)));
            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    public static void genController(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            data.put("module", MOUDLE);
            data.put("keyName", tableNameConvertUpperCamel(getPrimaryKeyName(tableName)));

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            //cfg.getTemplate("controller-restful.ftl").process(data, new FileWriter(file));
            cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    private static String tableNameConvertMappingPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }


    private static String getPrimaryKeyName(String table) {
        try {
            Class.forName(JDBC_DIVER_CLASS_NAME).newInstance();
            Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USERNAME,JDBC_PASSWORD);
            PreparedStatement ps = conn.prepareStatement("desc " + table);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsme = rs.getMetaData();

            int columnCount = rsme.getColumnCount();
            System.out.println("ResultSet对象中的列数"+ columnCount);
            while(rs.next()) {
                String columnName = rs.getString("Field");
                String  columnType = rs.getString("Type");
                String pri = rs.getString("Key");
                System.out.println(columnName+" "+columnType+" "+pri);
                if("PRI".equals(pri)) {
                    System.out.println(tableNameConvertUpperCamel(columnName));
                    return columnName;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> getAllTableNames() {
        List<String> list = new ArrayList<>();
        try {
            Class.forName(JDBC_DIVER_CLASS_NAME).newInstance();
            Connection conn = DriverManager.getConnection(JDBC_URL,JDBC_USERNAME,JDBC_PASSWORD);
            PreparedStatement ps = conn.prepareStatement("show tables " );
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsme = rs.getMetaData();

            int columnCount = rsme.getColumnCount();
            System.out.println("ResultSet对象中的列数"+ columnCount);
            while(rs.next()) {
                String columnName = rs.getString(1);
                list.add(columnName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
