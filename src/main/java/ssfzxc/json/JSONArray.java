package ssfzxc.json;

import ssfzxc.json.util.TypeCastUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author ssf
 * @date 2019-01-11
 */
public class JSONArray extends ArrayList<Object> implements JSON {

    public JSONArray getArray(int index) {
        Object value = get(index);
        if (value instanceof JSONArray) {
            return (JSONArray) value;
        }
        return null;
    }

    public JSONObject getJSONObject(int index) {
        Object value = get(index);
        if (value instanceof JSONObject) {
            return (JSONObject) value;
        }
        return null;
    }

    public String getString(int index) {
        Object value = get(index);
        return value == null ? null : value.toString();
    }

    public Long getLong(int index) {
        return TypeCastUtils.castToLong(get(index));
    }

    public long getLongValue(int index) {
        Object value = get(index);
        return value == null ? 0L : TypeCastUtils.castToLong(value);
    }

    public Integer getInteger(int index) {
        return TypeCastUtils.castToInt(get(index));
    }

    public int getIntValue(int index) {
        Object value = get(index);
        return value == null ? 0 : TypeCastUtils.castToInt(value);
    }

    public Short getShort(int index) {
        return TypeCastUtils.castToShort(get(index));
    }

    public short getShortValues(int index) {
        Object value = get(index);
        return value == null ? 0 : TypeCastUtils.castToShort(value);
    }

    public Double getDouble(int index) {
        return TypeCastUtils.castToDouble(get(index));
    }

    public double getDoubleValue(int index) {
        Object value = get(index);
        return value == null ? 0.0 : TypeCastUtils.castToDouble(value);
    }

    public Float getFloat(int index) {
        return TypeCastUtils.castToFloat(get(index));
    }

    public float getFloatValue(int index) {
        Object value = get(index);
        return value == null ? 0.0f : TypeCastUtils.castToFloat(value);
    }

    public BigInteger getBigInteger(int index) {
        return TypeCastUtils.castToBigInteger(get(index));
    }

    public BigDecimal getBigDecimal(int index) {
        return TypeCastUtils.castToBigDecimal(get(index));
    }

    public Byte getByte(int index) {
        return TypeCastUtils.castToByte(get(index));
    }

    public byte getByteValue(int index) {
        Object value = get(index);
        return value == null ? 0 : TypeCastUtils.castToByte(value);
    }

    public Date getDate(int index) {
        return TypeCastUtils.castToDate(get(index));
    }

    public Timestamp getTimestamp(int index) {
        return TypeCastUtils.castToTimestamp(get(index));
    }

    public Boolean getBealoon(int index) {
        return TypeCastUtils.castToBoolean(get(index));
    }

    public boolean getBealoonValue(int index) {
        Object value = get(index);
        return value == null ? false : TypeCastUtils.castToBoolean(value);
    }
}
