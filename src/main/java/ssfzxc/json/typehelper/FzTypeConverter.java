package ssfzxc.json.typehelper;

/**
 * 将类型T1装换为类型T2的数据
 * Created by ssf on 2018-03-16.
 */
public interface FzTypeConverter<T> {
    <E> E convert(final T data, Class<E> clazz) throws FzException;
}
