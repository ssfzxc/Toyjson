package ssfzxc.json.parser;

import org.junit.Assert;
import org.junit.Test;
import ssfzxc.json.JSON;
import ssfzxc.json.JSONArray;
import ssfzxc.json.JSONHelper;
import ssfzxc.json.JSONObject;
import ssfzxc.json.typehelper.TypeHelper;
import ssfzxc.json.typehelper.TypeTestBean;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author ssf
 * @date 2019-01-11
 */
public class ParserTest {

    @Test
    public void test_parser() throws ParseException, java.text.ParseException {
        JSONObject jsonObject = Parser.parse(
            "{\"name\":\"tom\", \"age\":12, \"birthday\":\"2019-01-11\"," +
                " \"null\":null, \"timestamp\":1547136000000, \"num\":-12}");
        Assert.assertEquals("tom", jsonObject.getString("name"));

        Assert.assertEquals("12", jsonObject.getString("age"));

        Assert.assertEquals(12, jsonObject.getIntValue("age"));
        Assert.assertEquals(Integer.valueOf(12), jsonObject.getInteger("age"));

        Assert.assertEquals(12L, jsonObject.getLongValue("age"));
        Assert.assertEquals(Long.valueOf(12), jsonObject.getLong("age"));

        Assert.assertEquals(12, jsonObject.getShortValues("age"));
        Assert.assertEquals(Short.valueOf((short) 12), jsonObject.getShort("age"));

        Assert.assertEquals(12, jsonObject.getDoubleValue("age"), 0.00);
        Assert.assertEquals(Double.valueOf(12), jsonObject.getDouble("age"));

        Assert.assertEquals(12f, jsonObject.getFloatValue("age"),0.00);
        Assert.assertEquals(Float.valueOf(12f), jsonObject.getFloat("age"));

        Assert.assertEquals(BigInteger.valueOf(12), jsonObject.getBigInteger("age"));
        Assert.assertEquals(BigDecimal.valueOf(12), jsonObject.getBigDecimal("age"));

        Assert.assertEquals(12, jsonObject.getByteValue("age"));
        Assert.assertEquals(Byte.valueOf((byte) 12), jsonObject.getByte("age"));

        Assert.assertEquals("2019-01-11", jsonObject.get("birthday"));
        Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-11"), jsonObject.getDate("birthday"));

        Assert.assertEquals(
            new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-11").getTime()),
            jsonObject.getTimestamp("timestamp")
        );

        Assert.assertEquals(null, jsonObject.get("null"));

        Assert.assertEquals(-12, jsonObject.getIntValue("num"));
    }

    @Test
    public void test_unicode() throws ParseException {
        JSONObject jsonObject = Parser.parse("{\"unicode\": \"\\u0061\\u0062\"}");
        String s = "\u0061\u0062";
        Assert.assertEquals(s, jsonObject.getString("unicode"));
        jsonObject = Parser.parse("{\"unicode\": \"测试\"}");
        Assert.assertEquals("测试", jsonObject.getString("unicode"));
        jsonObject = Parser.parse("{\"zore\":0, \"double\":12.322}");
        Assert.assertEquals(0, jsonObject.getIntValue("zore"));
        Assert.assertEquals(12.322, jsonObject.getDoubleValue("double"), 0.000);
//        System.out.println(jsonObject.getString("unicode"));
//        System.out.println(s);
    }

    @Test
    public void test_json2() throws ParseException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("test.json");
        JSONArray jsonArray = Parser.parseArray(inputStream);
        Assert.assertEquals(2, jsonArray.size());
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        Assert.assertEquals(2, jsonObject.getIntValue("id"));
        Assert.assertEquals(12.50, jsonObject.getDoubleValue("price"), 0.00);

        jsonObject = jsonArray.getJSONObject(1);
        Assert.assertEquals(3, jsonObject.getIntValue("id"));
        Assert.assertEquals(25.50, jsonObject.getDoubleValue("price"), 0.00);

        System.out.println(jsonArray);
    }

    @Test
    public void test_1() throws java.text.ParseException {
        String str = "123";
        System.out.println(str.substring(1, str.length() - 1));
        System.out.println(new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-11").getTime()).getTime());
    }

    @Test
    public void json() throws Exception {
        String json = "{\"id\":0, \"name\":\"Tom\", \"age\": 15, \"sex\":\"man\"}";
        Assert.assertNotNull(JSONHelper.parseObject(json, TypeTestBean.class));
    }

}