/**
 * 
 */
package com.sunsharing.xshare.framework.web.security.crypto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密工具。
 *
 * @author Kison 2017年6月29日
 */
public class PasswordUtils {

    public static String encode(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

}
