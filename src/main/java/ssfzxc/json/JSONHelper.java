package ssfzxc.json;

import ssfzxc.json.parser.Parser;
import ssfzxc.json.typehelper.TypeHelper;

/**
 * @author ssf
 * @date 2019-07-01
 */
public class JSONHelper {

    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            JSONObject jsonObject = Parser.parse(json);
            return TypeHelper.map2JavaObject(jsonObject, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
