package com.apex.app.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Mingze Ma
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoredUrlConfig {

    private List<String> urls = new ArrayList<>();

}
