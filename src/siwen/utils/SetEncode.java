package siwen.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @projectName: JavaWeb
 * @package: siwen.utils
 * @className: SetEncode
 * @author: 749291
 * @description: TODO
 * @date: 2/10/2023 9:12 PM
 * @version: 1.0
 */

public class SetEncode {
    private SetEncode() {

    }

    public static void setUtfEncode(HttpServletRequest req) throws IOException {
        String method = req.getMethod();
        switch (method) {
            case "get" -> {
                req.setCharacterEncoding("UTF-8");
            }
            case "post" -> {
                req.setCharacterEncoding("UTF-8");
            }
            default -> { }
        }
    }
}
