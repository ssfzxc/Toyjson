package ssfzxc.json.util;

import ssfzxc.json.JSONException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ssf
 * @date 2019-01-11
 */
public final class TypeCastUtils {

    private TypeCastUtils() {
    }

    public static final String castToString(Object value) {
        return value == null ? null : value.toString();
    }

    public static final Byte castToByte(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Number) {
            return ((Number) value).byteValue();
        } else if (value instanceof String) {
            String strVal = (String) value;
            return strVal.length() == 0 ? null : Byte.parseByte(strVal);
        } else {
            throw new JSONException("can not cast to byte, value : " + value);
        }
    }

    public static final Character castToChar(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Character) {
            return (Character) value;
        } else if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            } else if (strVal.length() != 1) {
                throw new JSONException("can not cast to byte, value : " + value);
            } else {
                return strVal.charAt(0);
            }
        } else {
            throw new JSONException("can not cast to byte, value : " + value);
        }
    }

    public static final Short castToShort(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Number) {
            return ((Number) value).shortValue();
        } else if (value instanceof String) {
            String strVal = (String) value;
            return strVal.length() == 0 ? null : Short.parseShort(strVal);
        } else {
            throw new JSONException("can not cast to short, value : " + value);
        }
    }

    public static final BigDecimal castToBigDecimal(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        } else {
            String strVal = value.toString();
            return strVal.length() == 0 ? null : new BigDecimal(strVal);
        }
    }

    public static final BigInteger castToBigInteger(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof BigInteger) {
            return (BigInteger) value;
        } else if (!(value instanceof Float) && !(value instanceof Double)) {
            String strVal = value.toString();
            return strVal.length() == 0 ? null : new BigInteger(strVal);
        } else {
            return BigInteger.valueOf(((Number) value).longValue());
        }
    }

    public static final Float castToFloat(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Number) {
            return ((Number) value).floatValue();
        } else if (value instanceof String) {
            String strVal = value.toString();
            return strVal.length() == 0 ? null : Float.parseFloat(strVal);
        } else {
            throw new JSONException("can not cast to float, value : " + value);
        }
    }

    public static final Double castToDouble(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else if (value instanceof String) {
            String strVal = value.toString();
            return strVal.length() == 0 ? null : Double.parseDouble(strVal);
        } else {
            throw new JSONException("can not cast to double, value : " + value);
        }
    }

    public static final Date castToDate(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Calendar) {
            return ((Calendar) value).getTime();
        } else if (value instanceof Date) {
            return (Date) value;
        } else {
            long longValue = -1L;
            if (value instanceof Number) {
                longValue = ((Number) value).longValue();
            }

            if (value instanceof String) {
                String strVal = (String) value;
                if (strVal.indexOf(45) != -1) {
                    String format;
                    if (strVal.length() == 10) {
                        format = "yyyy-MM-dd";
                    } else if (strVal.length() == "yyyy-MM-dd HH:mm:ss".length()) {
                        format = "yyyy-MM-dd HH:mm:ss";
                    } else {
                        format = "yyyy-MM-dd HH:mm:ss.SSS";
                    }

                    SimpleDateFormat dateFormat = new SimpleDateFormat(format);

                    try {
                        return dateFormat.parse(strVal);
                    } catch (ParseException var7) {
                        throw new JSONException("can not cast to Date, value : " + strVal);
                    }
                }

                if (strVal.length() == 0) {
                    return null;
                }

                longValue = Long.parseLong(strVal);
            }

            if (longValue < 0L) {
                throw new JSONException("can not cast to Date, value : " + value);
            } else {
                return new Date(longValue);
            }
        }
    }

    public static final Timestamp castToTimestamp(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Calendar) {
            return new Timestamp(((Calendar) value).getTimeInMillis());
        } else if (value instanceof Timestamp) {
            return (Timestamp) value;
        } else if (value instanceof Date) {
            return new Timestamp(((Date) value).getTime());
        } else {
            long longValue = 0L;
            if (value instanceof Number) {
                longValue = ((Number) value).longValue();
            }

            if (value instanceof String) {
                String strVal = (String) value;
                if (strVal.length() == 0) {
                    return null;
                }

                longValue = Long.parseLong(strVal);
            }

            if (longValue <= 0L) {
                throw new JSONException("can not cast to Date, value : " + value);
            } else {
                return new Timestamp(longValue);
            }
        }
    }

    public static final Long castToLong(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Number) {
            return ((Number) value).longValue();
        } else {
            if (value instanceof String) {
                String strVal = (String) value;
                if (strVal.length() == 0) {
                    return null;
                }

                try {
                    return Long.parseLong(strVal);
                } catch (NumberFormatException var4) {

                }
            }
            throw new JSONException("can not cast to long, value : " + value);
        }
    }

    public static final Integer castToInt(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof String) {
            String strVal = (String) value;
            return strVal.length() == 0 ? null : Integer.parseInt(strVal);
        } else {
            throw new JSONException("can not cast to int, value : " + value);
        }
    }

    public static final Boolean castToBoolean(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (value instanceof Number) {
            return ((Number) value).intValue() == 1;
        } else {
            if (value instanceof String) {
                String str = (String) value;
                if (str.length() == 0) {
                    return null;
                }

                if ("true".equals(str)) {
                    return Boolean.TRUE;
                }

                if ("false".equals(str)) {
                    return Boolean.FALSE;
                }

                if ("1".equals(str)) {
                    return Boolean.TRUE;
                }
            }

            throw new JSONException("can not cast to int, value : " + value);
        }
    }

    public static boolean isGenericParamType(Type type) {
        if (type instanceof ParameterizedType) {
            return true;
        } else {
            return type instanceof Class ? isGenericParamType(((Class) type).getGenericSuperclass()) : false;
        }
    }

    public static Type getGenericParamType(Type type) {
        if (type instanceof ParameterizedType) {
            return type;
        } else {
            return type instanceof Class ? getGenericParamType(((Class) type).getGenericSuperclass()) : type;
        }
    }

    public static Class<?> getClass(Type type) {
        if (type.getClass() == Class.class) {
            return (Class) type;
        } else {
            return type instanceof ParameterizedType ? getClass(((ParameterizedType) type).getRawType()) : Object.class;
        }
    }
}
