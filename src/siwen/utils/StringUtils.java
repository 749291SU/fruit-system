package siwen.utils;

/**
 * @projectName: JavaWeb
 * @package: siwen.utils
 * @className: StringUtils
 * @author: 749291
 * @description: TODO
 * @date: 2/9/2023 7:31 PM
 * @version: 1.0
 */
public class StringUtils {
    private StringUtils() {

    }
    public static boolean isNull(String str) {
        return str == null || "".equals(str);
    }
}