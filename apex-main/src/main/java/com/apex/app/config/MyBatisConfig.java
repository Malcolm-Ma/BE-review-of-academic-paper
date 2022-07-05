package com.apex.app.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis config
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.apex.app.mapper","com.apex.app.dao"})
public class MyBatisConfig {
}
