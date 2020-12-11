package util;

import java.util.UUID;

public class StringUtils {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
    public static String valueOf(Object obj) {
        if (obj == null) return "";
       /* if (obj instanceof oracle.sql.CLOB) {
            return clobToString((oracle.sql.CLOB) obj);
        }*/
        return obj.toString();
    }
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static String firstUpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    public static String UUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }
}
