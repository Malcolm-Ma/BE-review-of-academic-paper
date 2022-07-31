package com.apex.app.util;

import org.springframework.stereotype.Component;

/**
 * sql parse util
 *
 * @author Mingze Ma
 */
@Component
public class SqlUtil {

    private static final String FUZZY_SYMBOLS = "%";

    public static String parseQuery(String originalQuery) {
        return FUZZY_SYMBOLS + originalQuery + FUZZY_SYMBOLS;
    }

}
