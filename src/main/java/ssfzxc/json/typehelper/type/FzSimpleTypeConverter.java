package ssfzxc.json.typehelper.type;

import ssfzxc.json.typehelper.FzException;
import ssfzxc.json.typehelper.FzReader;
import ssfzxc.json.typehelper.FzTypeConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ssf on 2018-04-05.
 */
public final class FzSimpleTypeConverter implements FzTypeConverter<Map<String, Object>> {

    public FzSimpleTypeConverter() {

    }

    public <E> E convert(final Map<String, Object> data, Class<E> clazz) throws FzException {
        return FzTypeConverters.toJavaObject(clazz, new FzReader() {
            public boolean hasField(Object name) {
                return data.containsKey(name);
            }

            public Object getPrimitiveValue(Object name) {
                return data.get(name);
            }

            public Object getValue(Object name, Class type) throws FzException {
                Object tmp = data.get(name);
                if(tmp instanceof Map) {
                    Map<String, Object> map = (Map<String, Object>)tmp;
                    return FzSimpleTypeConverter.this.convert(map, type);
                } else {
                    return tmp;
                }
            }

            public List getListValue(Object name, Class type) throws FzException {
                Object tmp = data.get(name);
                List<Object> listObjs = null;
                Object subObj = null;
                Map<String, Object> map = null;
                if (tmp instanceof List) {
                    List tmpList = (List) tmp;
                    int len = tmpList.size();
                    listObjs = new ArrayList<Object>(len);
                    for (int i = 0; i < len; i++) {
                        tmp = tmpList.get(i);
                        if(tmp instanceof Map) {
                            map = (Map<String, Object>)tmp;
                            subObj = FzSimpleTypeConverter.this.convert(map, type);
                            if(subObj != null) {
                                listObjs.add(subObj);
                            }
                        } else {
                            listObjs.add(tmp);
                        }
                    }
                }
                return listObjs;
            }
        });
    }

}
