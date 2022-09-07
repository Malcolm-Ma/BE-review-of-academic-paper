package com.apex.app.util;

import org.springframework.stereotype.Component;

import java.util.*;
import java.io.*;
import java.security.*;

/**
 * MD5 encode util
 *
 * @author Mingze Ma
 */
@Component
public class MD5Util {
    public static String hex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(Integer.toHexString((b
                    & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public static String md5Hex(String message) {
        try {
            MessageDigest md =
                    MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ignored) {
        }
        return null;
    }
}