package ssfzxc.json.typehelper;


import java.util.List;

/**
 * 对象reader
 * Created by ssf on 2018-04-04.
 */
public interface FzReader<T> {
    boolean hasField(Object name);

    Object getPrimitiveValue(Object name);

    <T> T getValue(Object name, Class<T> type) throws FzException;

    <T> List<T> getListValue(Object name, Class<T> type) throws FzException;
}
