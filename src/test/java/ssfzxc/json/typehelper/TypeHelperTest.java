package ssfzxc.json.typehelper;
import org.junit.Test;

import java.util.Date;
import java.util.Map;


/**
 *
 * @date : 2018-10-05
 * @author : ssf
 */
public class TypeHelperTest {

    @Test
    public void javaObject2Map() throws Exception {
        TypeTestBean bean = new TypeTestBean();
        bean.setId(0);
        bean.setName("Tom");
        bean.setAge(14);
        bean.setSex("man");
        bean.setBirthday(new Date());

        System.out.println(TypeHelper.javaObject2Map(bean));
    }


    @Test
    public void map2JavaObject() throws Exception {
        TypeTestBean bean = new TypeTestBean();
        bean.setId(0);
        bean.setName("Tom");
        bean.setAge(15);
        bean.setSex("man");
        bean.setBirthday(new Date());

        Map<String, Object> map = TypeHelper.javaObject2Map(bean);

        System.out.println(TypeHelper.map2JavaObject(map, TypeTestBean.class));
    }
}
