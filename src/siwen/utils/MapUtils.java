package siwen.utils;

import java.util.Iterator;
import java.util.Map;

/**
 * @projectName: JavaWeb
 * @package: siwen.utils
 * @className: MapTraverse
 * @author: 749291
 * @description: TODO
 * @date: 2/10/2023 12:21 AM
 * @version: 1.0
 */

public class MapUtils {
    private MapUtils() {
    }

    public static <K, V> Iterator<Map.Entry<K, V>> getEntrySetIterator(Map<K, V> map) {
        Iterator<Map.Entry<K, V>> iterator = null;
        iterator = map.entrySet().iterator();
        return iterator;
    }
}
