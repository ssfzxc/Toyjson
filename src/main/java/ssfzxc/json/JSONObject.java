package ssfzxc.json;

import ssfzxc.json.util.TypeCastUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * @author ssf
 * @date 2019-01-11
 */
public class JSONObject extends LinkedHashMap<String, Object> implements JSON {

    public String getString(String key) {
        Object value = get(key);
        return value == null ? null : value.toString();
    }

    public JSONObject getJSONObject(String key) {
        Object value = get(key);
        if (value instanceof JSONObject) {
            return (JSONObject) value;
        }
        return null;
    }

    public JSONArray getJSONArray(String key) {
        Object value = get(key);
        if (value instanceof JSONArray) {
            return (JSONArray) value;
        }
        return null;
    }

    public Long getLong(String key) {
        return TypeCastUtils.castToLong(get(key));
    }

    public long getLongValue(String key) {
        Object value = get(key);
        return value == null ? 0L : TypeCastUtils.castToLong(value);
    }

    public Integer getInteger(String key) {
        return TypeCastUtils.castToInt(get(key));
    }

    public int getIntValue(String key) {
        Object value = get(key);
        return value == null ? 0 : TypeCastUtils.castToInt(value);
    }

    public Short getShort(String key) {
        return TypeCastUtils.castToShort(get(key));
    }

    public short getShortValues(String key) {
        Object value = get(key);
        return value == null ? 0 : TypeCastUtils.castToShort(value);
    }

    public Double getDouble(String key) {
        return TypeCastUtils.castToDouble(get(key));
    }

    public double getDoubleValue(String key) {
        Object value = get(key);
        return value == null ? 0.0 : TypeCastUtils.castToDouble(value);
    }

    public Float getFloat(String key) {
        return TypeCastUtils.castToFloat(get(key));
    }

    public float getFloatValue(String key) {
        Object value = get(key);
        return value == null ? 0.0f : TypeCastUtils.castToFloat(value);
    }

    public BigInteger getBigInteger(String key) {
        return TypeCastUtils.castToBigInteger(get(key));
    }

    public BigDecimal getBigDecimal(String key) {
        return TypeCastUtils.castToBigDecimal(get(key));
    }

    public Byte getByte(String key) {
        return TypeCastUtils.castToByte(get(key));
    }

    public byte getByteValue(String key) {
        Object value = get(key);
        return value == null ? 0 : TypeCastUtils.castToByte(value);
    }

    public Date getDate(String key) {
        return TypeCastUtils.castToDate(get(key));
    }

    public Timestamp getTimestamp(String key) {
        return TypeCastUtils.castToTimestamp(get(key));
    }

    public Boolean getBealoon(String key) {
        return TypeCastUtils.castToBoolean(get(key));
    }

    public boolean getBealoonValue(String key) {
        Object value = get(key);
        return value == null ? false : TypeCastUtils.castToBoolean(value);
    }
}
