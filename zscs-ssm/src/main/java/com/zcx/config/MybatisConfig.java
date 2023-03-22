package com.zcx.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.io.IOException;


public class MybatisConfig {
    @Bean
    public SqlSessionFactoryBean factoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.zcx.pojo");
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:com/zcx/dao/*.xml");
        factoryBean.setMapperLocations(resources);
        return factoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.zcx.dao");
        return mapperScannerConfigurer;
    }
}
