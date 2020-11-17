package com.mht.sc.scmbg;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-11-17 10:10
 **/
public class Config {
    /** 包名：com.mht.sc.scadmin.controller */
    public static final String PACKAGE_NAME_CONTROLLER = "controller";

    /** 包名：com.mht.sc.scadmin.service */
    public static final String PACKAGE_NAME_SERVICE = "service";

    /** 包名：com.mht.sc.scadmin.service.impl */
    public static final String PACKAGE_NAME_SERVICE_IMPL = "service.impl";

    /** 包名：model */
    public static final String PACKAGE_NAME_MODEL = "entity";

    /** 包名：com.mht.sc.scadmin.dao */
    public static final String PACKAGE_NAME_DAO = "mapper";

    /** 包名：xml */
    public static final String PACKAGE_NAME_XML = "xml";

    /** 文件名后缀：Model */
    public static final String FILE_NAME_MODEL = "%s";

    /** 文件名后缀：Dao */
    public static final String FILE_NAME_DAO = "%sMapper";

    /** 文件名后缀：Mapper */
    public static final String FILE_NAME_XML = "%sMapper";

    /** MP开头，Service结尾 */
    public static final String FILE_NAME_SERVICE = "%sService";

    /** 文件名后缀：ServiceImpl */
    public static final String FILE_NAME_SERVICE_IMPL = "%sServiceImpl";

    /** 文件名后缀：Controller */
    public static final String FILE_NAME_CONTROLLER = "%sController";

    /** 逻辑删除字段 */
    public static final String FIELD_LOGIC_DELETE_NAME = "delete_status";

    /** 作者 */
    public static final String AUTHOR = "maht";

    /** 生成文件的输出目录 */
    public static String projectPath = System.getProperty("C:\\Users\\Administrator\\Desktop\\gitafaf\\sc-plat\\sc-mbg");

    /** 输出目录 */
    public static final String outputDir = "C:/Users/Administrator/Desktop/gitafaf/sc-plat/sc-admin" + "/src/main/java";
//    public static final String outputDir = "/Users/code-generator";

    /** 模板引擎。velocity / freemarker / beetl */
    public static final String TEMPLATE_ENGINE = "velocity";

    /** 是否支持Swagger，默认不支持 */
    public static final Boolean SWAGGER_SUPPORT = false;

}
