package cn.sxfei.app.modules.sapress.comm.util;

import java.math.BigInteger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sxfei.app.core.base.constant.Constants;
import cn.sxfei.app.core.kit.HashKit;
import cn.sxfei.app.core.spring.PropertiesHelper;
import cn.sxfei.app.core.utils.StringUtils;

/**
 * 参考：spring-security https://github.com/spring-projects/spring-security/
 * blob/master/web/src/main/java/org/springframework/security/
 * web/authentication/rememberme/TokenBasedRememberMeServices.java
 * ....AbstraresponseememberMeServices.java
 */
public class CookieUtils {

    private final static String COOKIE_SEPARATOR = "#SAP#";

    public static void put(HttpServletResponse response, String key, String value) {
        put(response, key, value, 60 * 60 * 24 * 7);
    }

    public static void put(HttpServletResponse response, String key, BigInteger value) {
        put(response, key, value.toString());
    }

    public static void put(HttpServletResponse response, String key, long value) {
        put(response, key, value + "");
    }

    public static void put(HttpServletResponse response, String key, String value, int maxAgeInSeconds) {
        String encrypt_key = PropertiesHelper.getProperty("encrypt_key");
        String saveTime = System.currentTimeMillis() + "";
        String encrypt_value = encrypt(encrypt_key, saveTime, maxAgeInSeconds + "", value);
        String cookieValue = encrypt_value + COOKIE_SEPARATOR + saveTime + COOKIE_SEPARATOR + maxAgeInSeconds + COOKIE_SEPARATOR + value;
        setCookie(response, key, cookieValue, maxAgeInSeconds);
    }

    private static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        setCookie(response, name, value, maxAgeInSeconds, null, null, null);
    }

    private static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds, String path, String domain,
            Boolean isHttpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAgeInSeconds);

        if (path == null) {
            path = "/";
        }
        cookie.setPath(path);

        if (domain != null) {
            cookie.setDomain(domain);
        }
        if (isHttpOnly != null) {
            cookie.setHttpOnly(isHttpOnly.booleanValue());
        }
        response.addCookie(cookie);

    }

    private static String encrypt(String encrypt_key, String saveTime, String maxAgeInSeconds, String value) {
        return HashKit.md5(encrypt_key + saveTime + maxAgeInSeconds + value);
    }

    public static void remove(HttpServletResponse response) {
        String key = Constants.COOKIE_LOGINED_USER;
        setCookie(response, key, null, 0);
    }

    public static String getCookStr(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;

    }

    public static String getUserId(HttpServletRequest request) {
        String key = Constants.COOKIE_LOGINED_USER;
        String encrypt_key = PropertiesHelper.getProperty("encrypt_key");
        String cookieValue = getCookStr(request, key);
        if (StringUtils.isNotBlank(cookieValue)) {
            String cookieStrings[] = cookieValue.split(COOKIE_SEPARATOR);
            if (null != cookieStrings && 4 == cookieStrings.length) {
                String encrypt_value = cookieStrings[0];
                String saveTime = cookieStrings[1];
                String maxAgeInSeconds = cookieStrings[2];
                String value = cookieStrings[3];

                String encrypt = encrypt(encrypt_key, saveTime, maxAgeInSeconds, value);

                // 保证 cookie 不被人为修改
                if (encrypt_value != null && encrypt_value.equals(encrypt)) {
                    long stime = Long.parseLong(saveTime);
                    long maxtime = Long.parseLong(maxAgeInSeconds) * 1000;
                    // 查看是否过时
                    if ((stime + maxtime) - System.currentTimeMillis() > 0) {
                        return value;
                    }
                }
            }
        }
        return null;
    }
}
