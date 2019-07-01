package ssfzxc.json.typehelper;

import ssfzxc.json.typehelper.type.FzJavaObjectToMap;
import ssfzxc.json.typehelper.type.FzSimpleTypeConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ssf on 2018-04-08.
 */
public final class TypeHelper {
    private TypeHelper() {
    }

    private static final FzSimpleTypeConverter fzTypeConverter = new FzSimpleTypeConverter();
    private static final FzJavaObjectToMap fzJavaObjectToMap = new FzJavaObjectToMap();

    public static <T> T map2JavaObject(Map<String, Object> data, Class<T> clazz) throws FzException {
        return fzTypeConverter.convert(data, clazz);
    }

    public static <T> List<T> listMap2JavaObject(List<Map<String, Object>> dataSet, Class<T> clazz) throws FzException {
        int len = dataSet.size();
        Map<String, Object> data;
        List<T> objs = new ArrayList<T>(len);
        for (int i = 0; i < len; i++) {
            data = dataSet.get(i);
            T obj = fzTypeConverter.convert(data, clazz);
            objs.add(obj);
        }
        return objs;
    }

    public static Map<String, Object> javaObject2Map(Object obj) throws FzException {
        return fzJavaObjectToMap.toMap(obj);
    }
}
