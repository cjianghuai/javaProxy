package cn.netinnet.server;

/**
 * 项目常量
 */
public final class ProjectConstant_Client {
    public static final String MOUDLE = "question";
    public static final String BASE_PACKAGE = "cn.netinnet.shortitem";//项目基础包名称，根据自己公司的项目修改

    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".domain"+"." + MOUDLE;//Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao" + "." + MOUDLE;//Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service" + "."  + MOUDLE;;//Service所在包
    public static final String SERVICE_IMPL_PACKAGE = BASE_PACKAGE + ".service" + ".impl" +"." +  MOUDLE;//ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller" +"." + MOUDLE;//Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.Mapper";//Mapper插件基础接口的完全限定名
}
