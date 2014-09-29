package org.seandeng.util;

/**
 * Created by myuser on 2014/9/5.
 */
public class StringUtils {

    /**
     * 测试给定的字符串是否包含给定的子字符串。
     */
    public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
        for (int j = 0; j < substring.length(); j++) {
            int i = index + j;
            if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
