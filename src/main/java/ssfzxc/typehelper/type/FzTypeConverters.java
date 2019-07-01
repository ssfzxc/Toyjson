package ssfzxc.typehelper.type;

import ssfzxc.typehelper.FzException;
import ssfzxc.typehelper.FzReader;
import ssfzxc.typehelper.FzUtil;
import ssfzxc.typehelper.FzWriter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ssf on 2018-04-05.
 */
public final class FzTypeConverters {

    //检查类型,是否抛出异常
    private static final boolean isCheckType = false;//isIgnoreType = true;
    //忽略类型检查时,是否强制转化
    private static final boolean isForceTypeConvert = true;
    //忽略null值
    private static final boolean isIgnoreNull = false;
    private static final Map<String, Object> readMethodCache;
    private static final Map<String, Object> writeMethodCache;
    private static final Map<String, Object> fieldCache;
    private static final Object emptyCache = new Object();

    static {
        readMethodCache = new ConcurrentHashMap<String, Object>(128);
        writeMethodCache = new ConcurrentHashMap<String, Object>(128);
        fieldCache = new ConcurrentHashMap<String, Object>(128);
    }

    public static <T> T toJavaObject(Class<T> type, FzReader reader) throws FzException {
        try {
            T bean = type.newInstance();

            //系统有缓存
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] pdArr = beanInfo.getPropertyDescriptors();
            PropertyDescriptor pd;

            String typeName = type.getName();
            String exceptionFormat = "%s (%s) is not a %s ";
            String fieldName;
            Field field;
            Object value;
            List<?> listValue;
            Method method;

            for(int i=0,len = pdArr.length;i<len;i++) {
                pd = pdArr[i];
                //获取set方法
                method = getWriteMethod(type, pd);
                if (method != null) {
                    fieldName = pd.getName();
                    //获取类属性元数据
                    field = getField(type, pd);

                    if (field != null) {
                        //alias(别名注解)

                        if (reader.hasField(fieldName)) {
                            Class<?> fieldClazz = field.getType();

                            if (String.class.isAssignableFrom(fieldClazz)) {//字符串类型
                                value = reader.getPrimitiveValue(fieldName);
                                if (value instanceof String) {
                                    method.invoke(bean, new Object[]{value.toString()});
                                } else {
                                    if(isCheckType && value != null) {
                                        throw new FzException(String.format(
                                                exceptionFormat,
                                                fieldName,
                                                typeName,
                                                "String"));
                                    }

                                    if (isForceTypeConvert && value != null) {
                                        method.invoke(bean, new Object[]{value.toString()});
                                    }
                                }
                            } else if (Long.class.isAssignableFrom(fieldClazz) || long.class.isAssignableFrom(fieldClazz)) {//长整型
                                value = reader.getPrimitiveValue(fieldName);
                                if (value instanceof Long) {
                                    method.invoke(bean, new Object[]{value});
                                } else if(value != null) {
                                    if(isCheckType) {
                                        throw new FzException(String.format(
                                                exceptionFormat,
                                                fieldName,
                                                typeName,
                                                "Long"));
                                    }

                                    if (isForceTypeConvert && FzUtil.isNumeric(value)) {
                                        method.invoke(bean, new Object[]{Long.valueOf(value.toString())});
                                    }
                                }
                            } else if (Integer.class.isAssignableFrom(fieldClazz) || int.class.isAssignableFrom(fieldClazz)) {//整型
                                value = reader.getPrimitiveValue(fieldName);
                                if (value instanceof Integer) {
                                    method.invoke(bean, new Object[]{value});
                                } else if(value != null) {
                                    if(isCheckType) {
                                        throw new FzException(String.format(
                                                exceptionFormat,
                                                fieldName,
                                                typeName,
                                                "Integer"));
                                    }

                                    if (isForceTypeConvert && FzUtil.isNumeric(value)) {
                                        method.invoke(bean, new Object[]{Integer.valueOf(value.toString())});
                                    }
                                }
                            } else if (Double.class.isAssignableFrom(fieldClazz) || double.class.isAssignableFrom(fieldClazz)) {//浮点数
                                value = reader.getPrimitiveValue(fieldName);
                                if (value instanceof Double) {
                                    method.invoke(bean, new Object[]{value});
                                } else if(value != null) {
                                    if(isCheckType) {
                                        throw new FzException(String.format(
                                                exceptionFormat,
                                                fieldName,
                                                typeName,
                                                "Double"));
                                    }

                                    if (isForceTypeConvert && FzUtil.isNumeric(value)) {
                                        method.invoke(bean, new Object[]{Double.valueOf(value.toString())});
                                    }
                                }
                            } else if (Number.class.isAssignableFrom(fieldClazz)) {//数字类型
                                value = reader.getPrimitiveValue(fieldName);
                                if (value instanceof Number) {
                                    method.invoke(bean, new Object[]{value});
                                } else if(value != null) {
                                    if(isCheckType) {
                                        throw new FzException(String.format(
                                                exceptionFormat,
                                                fieldName,
                                                typeName,
                                                "Number"));
                                    }
                                }
                            } else if (Boolean.class.isAssignableFrom(fieldClazz) || boolean.class.isAssignableFrom(fieldClazz)) {//布尔型
                                value = reader.getPrimitiveValue(fieldName);
                                if (value instanceof Boolean) {
                                    method.invoke(bean, new Object[]{value});
                                } else if(value != null) {
                                    if(isCheckType) {
                                        throw new FzException(String.format(
                                                exceptionFormat,
                                                fieldName,
                                                typeName,
                                                "Boolean"));
                                    }

                                    if (isForceTypeConvert) {
                                        method.invoke(bean, new Object[]{Boolean.valueOf(value.toString())});
                                    }
                                }
                            } else if (Date.class.isAssignableFrom(fieldClazz)) {//时间格式
                                value = reader.getPrimitiveValue(fieldName);
                                if (value instanceof Date) {
                                    method.invoke(bean, new Object[]{value});
                                } else if(value != null) {
                                    if(isCheckType) {
                                        throw new FzException(String.format(
                                                exceptionFormat,
                                                fieldName,
                                                typeName,
                                                "Boolean"));
                                    }

                                    if (isForceTypeConvert) {
                                        method.invoke(bean, new Object[]{FzUtil.parseDateTime(value.toString())});
                                    }
                                }
                            } else if (List.class.isAssignableFrom(fieldClazz)) {//集合
                                //泛型的元数据,即元素类型
                                Type subObjectType = field.getGenericType();
                                if (subObjectType instanceof ParameterizedType) {
                                    ParameterizedType paramType = (ParameterizedType)subObjectType;
                                    Type[] genericTypes = paramType.getActualTypeArguments();
                                    if(genericTypes != null && genericTypes.length > 0 && genericTypes[0] instanceof Class) {
                                        Class<?> subType = (Class)genericTypes[0];
                                        listValue = reader.getListValue(fieldName, subType);
                                        if(listValue != null) {
                                            method.invoke(bean, new Object[]{listValue});
                                        }
                                    }
                                }
                            } else if (Object[].class.isAssignableFrom(fieldClazz)) {//数组
                                Class<?> subType = fieldClazz.getComponentType();
                                listValue = reader.getListValue(fieldName, subType);
                                Object[] subValue = (Object[]) Array.newInstance(subType, listValue.size());
                                if(listValue != null) {
                                    method.invoke(bean, new Object[]{listValue.toArray(subValue)});
                                }
                            } else {
                                value = reader.getValue(fieldName, fieldClazz);
                                if (value != null) {
                                    method.invoke(bean, new Object[]{value});
                                }
                            }
                        }
                    }
                }
            }
            return bean;
        } catch (Exception e) {
            throw new FzException(e);
        }
    }

    public static void javaObjectToMap(Object obj, FzWriter writer) throws FzException {
        try {
            Class<?> type = obj.getClass();
            //系统有缓存
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] pdArr = beanInfo.getPropertyDescriptors();
            PropertyDescriptor pd;

            String typeName = type.getName();
            String exceptionFormat = "%s (%s) is not a %s ";
            Method method;
            Object value;
            String fieldName;
            for (int i = 0, len = pdArr.length; i < len; i++) {
                pd = pdArr[i];
                fieldName = pd.getName();
                if ("class".equals(fieldName)) {
                    continue;
                }
                method = getReadMethod(type, pd);
                if (method != null) {
                    value = method.invoke(obj, new Object[]{});
                    if (isIgnoreNull && value == null) {
                        continue;
                    }
                    writer.setValue(fieldName, value);
                }
            }

        } catch (Exception e) {
            throw new FzException(e);
        }
    }

    private static Method getWriteMethod(Class clazz, PropertyDescriptor pd) {
        String key = clazz.getName() + "_" + pd.getName();
        Object method = writeMethodCache.get(key);
        if (method == null) {
            method = pd.getWriteMethod();
            if (method == null) {
                method = emptyCache;
            }
            writeMethodCache.put(key, method);
        }

        return method == emptyCache ? null : (Method) method;
    }

    private static Method getReadMethod(Class clazz, PropertyDescriptor pd) {
        String key = clazz.getName() + "_" + pd.getName();
        Object method = readMethodCache.get(key);
        if (method == null) {
            method = pd.getReadMethod();
            if (method == null) {
                method = emptyCache;
            }
            readMethodCache.put(key, method);
        }

        return method == emptyCache ? null : (Method) method;
    }

    public static Field getField(Class clazz, PropertyDescriptor pd) {
        String key = clazz.getName() + "_" + pd.getName();
        Object field = fieldCache.get(key);
        if (field == null) {
            try {
                field = clazz.getDeclaredField(pd.getName());
            } catch (NoSuchFieldException e) {
                field = emptyCache;
            }
            fieldCache.put(key, field);
        }

        return field == emptyCache ? null : (Field) field;
    }

}
