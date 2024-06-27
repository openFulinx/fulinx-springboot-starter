package com.fulinx.web;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.fulinx.spring.web.SpringWebApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = SpringWebApplication.class)
@RunWith(SpringRunner.class)
class MyBatisPlusGeneratorTests {

    /**
     * Items to be configured
     */
    private static String author = "fulinx";
    private static String databaseName = "fulinx_spring_starter";
    private static String dbUsername = "root";
    private static String dbPassword = "root";
    private static String dbUrl = String.format("jdbc:mysql://localhost:3306/%s?useUnicode=true" +
            "&characterEncoding=UTF-8" +
            "&zeroDateTimeBehavior=convertToNull" +
            "&autoReconnect=true " +
            "&failOverReadOnly=false&" +
            "useSSL=false&" +
            "allowMasterDownConnections=false&" +
            "allowSlavesDownConnections=false&" +
            "readFromMasterWhenNoSlaves=true", databaseName);
    private static String dataPackage = "com.fulinx.spring.data.mysql";
    private static String dataModuleName = "spring-data";
    private static String outPutRootPath = "D:\\develop\\study\\fulinx-springboot-starter";

    /**
     * Items not to be configured
     */
    private static String entityServicePackage = "service";
    private static String entityServiceImplPackage = "service.impl";
    private static String entityPackage = "entity";
    private static String entityXmlPackage = "entity.sql";
    private static String entityMapperPackage = "entity.mapper";
    private static String dataSourceBasePath = outPutRootPath + String.format("/%s/src", dataModuleName);
    private static String dataOutputDir = dataSourceBasePath + "/main/java";


    @Test
    void create() {

        FastAutoGenerator.create(dbUrl, dbUsername, dbPassword)
                .globalConfig(builder -> builder
                        .author(author)
                        .outputDir(dataOutputDir)
                        .enableSwagger()
                        .commentDate("yyyy-MM-dd")
                )
                .packageConfig(builder -> {
                    builder.parent(dataPackage)
                            .service(entityServicePackage)
                            .serviceImpl(entityServiceImplPackage)
                            .entity(entityPackage)
                            .xml(entityXmlPackage)
                            .mapper(entityMapperPackage);

                    Map<OutputFile, String> pathInfo = new HashMap<>();
                    String entityPath = joinPath(dataOutputDir, dataPackage + "." + entityPackage);
                    String mapperPath = joinPath(dataOutputDir, dataPackage + "." + entityMapperPackage);
                    String xmlPath = joinPath(dataOutputDir, dataPackage + "." + entityXmlPackage);
                    String servicePath = joinPath(dataOutputDir, dataPackage + "." + entityServicePackage);
                    String serviceImplPath = joinPath(dataOutputDir, dataPackage + "." + entityServiceImplPackage);

                    pathInfo.put(OutputFile.entity, entityPath);
                    pathInfo.put(OutputFile.mapper, mapperPath);
                    pathInfo.put(OutputFile.xml, xmlPath);
                    pathInfo.put(OutputFile.service, servicePath);
                    pathInfo.put(OutputFile.serviceImpl, serviceImplPath);
                    builder.pathInfo(pathInfo);
                })
                .strategyConfig(builder -> builder
                        .entityBuilder()
                        .enableLombok()
                        .addTableFills(
                                new Column("record_create_name", FieldFill.INSERT),
                                new Column("record_create_time", FieldFill.INSERT),
                                new Column("record_update_name", FieldFill.INSERT_UPDATE),
                                new Column("record_update_time", FieldFill.INSERT_UPDATE)
                        )
                        .enableActiveRecord()
                        .formatFileName("%sEntity")
                        .naming(NamingStrategy.underline_to_camel)
                        .versionColumnName("record_version")
                        .logicDeleteColumnName("is_delete")
                        .enableColumnConstant()
                        .enableLombok()
                        .enableChainModel()
                        .build()
                        .serviceBuilder().formatServiceFileName("%sEntityService")
                        .formatServiceImplFileName("%sEntityServiceImpl")
                        .mapperBuilder()
                        .enableBaseColumnList()
                        .enableBaseResultMap()
                        .formatMapperFileName("%sMapper")
                        .build())
                .templateConfig(builder -> builder.controller(null))
                .execute();
    }

    /**
     * 连接路径字符串
     *
     * @param parentDir   路径常量字符串
     * @param packageName 包名
     * @return 连接后的路径
     */
    private String joinPath(String parentDir, String packageName) {
        if (StringUtils.isBlank(parentDir)) {
            parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
        }
        if (!StringUtils.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
        return parentDir + packageName;
    }

}
