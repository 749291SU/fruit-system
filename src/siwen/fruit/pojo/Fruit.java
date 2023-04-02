package siwen.fruit.pojo;

import lombok.*;

/**
 * @projectName: LearnJava
 * @package: com.siwen.fruit.pojo
 * @className: Fruit
 * @author: 749291
 * @description: define fruit class
 * @date: 2/7/2023 9:40 PM
 * @version: 1.0
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Fruit {
    private Integer id;
    private String name;
    private Double price;
    private Integer inventory;
    private String remark;

//    public Fruit(Map<String, String> map) {
//        Iterator<Map.Entry<String, String>> iterator = MapUtils.getEntrySetIterator(map);
//        Class<Fruit> clazz = Fruit.class;
//
//        while (iterator.hasNext()) {
//            String key = iterator.next().getKey();
//            String value = iterator.next().getValue();
//
//            Field field = null;
//            try {
//                field = clazz.getDeclaredField(propertyName);
//            } catch (NoSuchFieldException e) {
//                throw new RuntimeException(e);
//            }
//            field.setAccessible(true);
//
//            Class propertyTypeClass = field.getType();
//            field.set(this, );
//        }
//    }
}
