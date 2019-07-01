package ssfzxc.typehelper.type;

import ssfzxc.typehelper.FzException;
import ssfzxc.typehelper.FzWriter;

import java.util.*;

/**
 * Created by ssf on 2018-04-08.
 */
public class FzJavaObjectToMap {
    public Map<String, Object> toMap(final Object obj) throws FzException {
        final Map<String, Object> data = new HashMap<String, Object>();

        FzTypeConverters.javaObjectToMap(obj, new FzWriter() {
            public void setValue(String name, Object value) throws FzException {
                if (isBaseType(value)) {//基本类型无需转化
                    data.put(name, value);
                } else if (value instanceof List || value instanceof Object[]) {
                    List<?> subValues = null;
                    if (value instanceof List) {
                        subValues = (List<?>) value;
                    } else {
                        Object[] Valuearr = (Object[]) value;
                        subValues = Arrays.asList(Valuearr);
                    }
                    int len = subValues.size();
                    if (len == 0 || isBaseType(subValues.get(0))) {//基本类型无需转化
                        data.put(name, subValues);
                    } else {
                        List<Map<String, Object>> subDatas = new ArrayList<Map<String, Object>>(len);
                        Map<String, Object> subData;
                        Object subObj;
                        for (int i = 0; i < len; i++) {
                            subObj = subValues.get(i);
                            subData = toMap(subObj);
                            subDatas.add(subData);
                        }
                        data.put(name,subDatas);
                    }
                } else {
                    data.put(name,toMap(value));
                }
            }

            private boolean isBaseType(Object obj) {
                boolean b = false;
                b = obj instanceof String;
                b = b || obj instanceof Number;
                b = b || obj instanceof Boolean;
                b = b || obj instanceof Date;
                return b;
            }
        });

        return data;
    }
}
