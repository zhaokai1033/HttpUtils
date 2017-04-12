package com.code.open.http;

import com.code.open.http.util.Util;

import org.junit.Before;
import org.junit.Test;

/**
 * ================================================
 * Created by zhaokai on 2017/4/12.
 * Email zhaokai1033@126.com
 * Describe :
 * ================================================
 */
public class UtilTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void isJson() throws Exception {

    }

    @Test
    public void jsonFormat() throws Exception {

    }

    @Test
    public void jsonUtil() throws Exception {
        String json = "{\n" +
                "\t\"content\":\"this is the msg content.\",\n" +
                "\t\"tousers\":\"user1|user2\",\n" +
                "\t\"msgtype\":\"texturl\",\n" +
                "\t\"appkey\":\"test\",\n" +
                "\t\"domain\":\"test\",\n" +
                "\t\"system\":{\n" +
                "\t\t\"wechat\":{\n" +
                "\t\t\t\"safe\":\"1\"\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"texturl\":{\n" +
                "\t\t\"urltype\":\"0\",\n" +
                "\t\t\"user1\":{\n" +
                "\t\t\t\"spStatus\":\"user01\",\n" +
                "\t\t\t\"workid\":\"work01\"\n" +
                "\t\t},\n" +
                "\t\t\"user2\":{\n" +
                "\t\t\t\"spStatus\":\"user02\",\n" +
                "\t\t\t\"workid\":\"work02\"\n" +
                "\t\t}\n" +
                "\t}\n";
        System.out.println("isJson" + new Util().isJson(json));
        System.out.println(Util.jsonFormat(json));
    }
}