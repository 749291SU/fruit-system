package siwen.utils;

/**
 * @projectName: JavaWeb
 * @package: siwen.utils
 * @className: OperateEnumerate
 * @author: 749291
 * @description: TODO
 * @date: 2/10/2023 9:19 PM
 * @version: 1.0
 */

public enum OperateEnumerate {

    INDEX("index"), ADD("add"), DELETE("delete"), UPDATE("update"), EDIT("edit"), ADD_PRE("addPre");

    final String operate;
    OperateEnumerate(String operate) {
        this.operate = operate;
    }
}
