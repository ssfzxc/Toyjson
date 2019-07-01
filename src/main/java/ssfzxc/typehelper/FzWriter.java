package ssfzxc.typehelper;

/**
 * Created by ssf on 2018-04-08.
 */
public interface FzWriter {

//    Object setPrimitiveValue(Object name);

    void setValue(String name, Object value) throws FzException;

//    boolean setListValue(Object name, List<Object> value) throws FzException;
}
