package com.meow.Utils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class Utils {
    public static String[] getNullPropertyNames (Object source) {
        Set<String> emptyNames = new HashSet<>();
        Class<?> reqClass = source.getClass();
        Field[] fields = reqClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if(field.get(source) == null || field.get(source) == ""){
                    emptyNames.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
