package com.itheima.test;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class MyTest {

/*        public static void  printValur(String str){
            System.out.println("print value : "+str);
        }

        public static void main(String[] args) {
            List<String> al = Arrays.asList("a", "b", "c", "d");
            al.forEach(MyTest::printValur);
            //下面的方法和上面等价的
            Consumer<String> methodParam = MyTest::printValur; //方法参数
            al.forEach(x -> methodParam.accept(x));//方法执行accept

    }*/
@Test
public void test04() throws ScriptException {
    ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
    String s = "213.34*2+（2.2+0.5）/2*120";
    String s1 = s.replaceAll("（", "(");
    String s2 = s1.replaceAll("）", ")");
    System.out.println(jse.eval(s2));

}
}
