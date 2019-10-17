package MyTest;

import org.apache.poi.util.StringUtil;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by chenjh on 2019/7/26.
 */
public class Java8Test {
    public static void main(String[] args) {

//        (int x,int y) -> x+y;
//        java8Sort();
//        Operation add  = (int a,int b)->a+b;
//        Java8Test java8Test = new Java8Test();
//        System.out.println(java8Test.operation(2,3,add));
//        test();
        lambdaTest();

    }

    private int operation(int a, int b, Operation operation) {
        return operation.opera(a, b);
    }

    public static void java8Sort() {
        List<String> names1 = new ArrayList<String>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");

        List<String> names2 = new ArrayList<String>();
        names2.add("Google ");
        names2.add("Runoob ");
        names2.add("Taobao ");
        names2.add("Baidu ");
        names2.add("Sina ");

        names1.sort(String::compareTo);
        System.out.println(names1.toString());
    }

    interface Operation {
        default void print() {
            System.out.println("default");
        }

        int opera(int a, int b);
    }

    public static void test() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

        String name = "Runoob";
        Integer result = null;

        try {
            nashorn.eval("print('" + name + "')");
            result = (Integer) nashorn.eval("10 + 2");

        } catch (ScriptException e) {
            System.out.println("执行脚本错误: " + e.getMessage());
        }

        System.out.println(result.toString());
    }
    public interface MyFunction{
         String getValue(String a);
    }
    public static String strHandler(String str, MyFunction mf) {
        return mf.getValue(str);
    }
    public static void lambdaTest() {

        String trimStr = strHandler("\t\t 你是大傻逼       ", (str) -> str.trim());

        System.out.println(trimStr);
        List<Integer> list = Arrays.asList(1222,1, 2, 3, 4,8);
        List<Integer> names = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};
//        System.out.println(names.toString());

        List<String> colors = Stream.of("red", "yellow").collect(Collectors.toList());
        List<Integer> l = list.stream().map(e -> e + 1).collect(Collectors.toList());
        Map<Integer,Integer> map = list.stream().collect(Collectors.toMap(e->e,e->e+1));
//        System.out.println(list.stream().findFirst().get());
        list.stream().forEachOrdered(e->Java8Test.sout(e));
//        list.stream().limit(2).forEach(System.out::println);
//        Stream.of("one", "two", "three", "four")
//                        .filter(e -> e.length() > 3)
//                         .peek(e -> System.out.println("Filtered value: " + e))
//                         .map(String::toUpperCase)
//                         .peek(e -> System.out.println("Mapped value: " + e))
//                         .collect(Collectors.toList());

//        System.out.println(list.stream().skip(1).collect(Collectors.toList()));
//        System.out.println(list.stream().mapToInt(e->e).summaryStatistics().getSum());
        List<String> l0 = Arrays.asList("1","2");
//        System.out.println(l0.stream().mapToInt(Java8Test::getInt).summaryStatistics().getMax());
        List sortList = list.stream().sorted().collect(Collectors.toList());
//        System.out.println(sortList.toString());
        List list1 = list.stream().sorted((x,y)->Java8Test.sortTest(x,y)).collect(Collectors.toList());

//        System.out.println(list1.toString());
//        list1.stream().reduce()

//        System.out.println(list.stream().reduce(0,(a,b)->a+b));

        Boolean b = list.stream().noneMatch(integer -> {
            if (integer > 77777){
                return true;
            }else {
                return false;
            }
        });


//        System.out.println(b);
        System.out.println(list.parallelStream().map(e->{
            int x =e+1;return x;
        }).collect(Collectors.toList()));

        Stream.of(1,2,3,4,5,6).collect(Collectors.toMap(e->e+1,e->{int y = e.intValue()+1;return y;}));
        System.out.println((Stream.of(1,2,3,4,5,6).mapToInt(e->e).sum()));
        System.out.println(Stream.of(2,3,4,5).reduce((e1,e2)->{int x = e1*2;int y = e2*2;return x+y;}).get());
        System.out.println(Arrays.asList("a","b","b").stream().reduce((e1,e2)-> e1.toUpperCase()+e2.toUpperCase()).get());

        List<List<Integer>> test = new ArrayList<List<Integer>>();
        test.add(new ArrayList<Integer>(){{
            add(1);
            add(2);
        }});
        test.add(new ArrayList<Integer>(){{
            add(3);
            add(4);
        }});
        System.out.println(test.toString());
//        System.out.println(test.stream().flatMap(e-> e.stream().mapToInt(e1 -> {
//            return e1;
//        }).sum()));

        System.out.println(test.stream().flatMap(e->e.stream()).mapToInt(e0->e0).reduce((x,y)->x+y).getAsInt());

    }

    public static Integer getInt(String str){
        if("".equals(str) || null == str) return 0;
        return Integer.parseInt(str)*3;
    }

    public static Integer retuInt(Integer a){
        return a;
    }

    public static Integer sortTest(Integer x,Integer y){
        return y - x;
    }

    public static void sout(Integer x){
        System.out.println("element:"+x);
    }
}
