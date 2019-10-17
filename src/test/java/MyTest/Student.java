package MyTest;

import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by chenjh on 2019/8/1.
 */
public class Student extends Person<String> implements Serializable,Runnable {
    public String stuNo;
    private String stuName;

    public Student(){
        super();
    }

//    public Student(String stuNo,String stuName){
//        this.stuNo = stuNo;
//        this.stuName = stuName;
//    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Override
    public String toString() {
        return "Student [stuNo=" + stuNo + ", stuName=" + stuName + "]";

    }

    @Override
    public void run() {

    }

    private int exam(String str, Integer tag) throws NoSuchMethodException {
        System.out.println(str);
        return tag;
    }

    /**
     * 反射
     */
    @Test
    public void test() throws Exception {
//        Class clazz1 = new Student().getClass();

        Class clazz2 = Student.class;

//        String className = "test.MyTest.Student";
//        Class clazz3 = Class.forName(className);
//
//        Class clazz4 = this.getClass().getClassLoader().loadClass(className);

        Student student = (Student)clazz2.newInstance();
        Field field1 = clazz2.getField("stuNo");
        field1.set(student,"s01");
        System.out.println(student);

        Field field2 = clazz2.getDeclaredField("stuName");
//        field2.setAccessible(true);
        field2.set(student,"n01");
        System.out.println(student);

        String str = Modifier.toString(field2.getModifiers());
        System.out.println(str);

        Method method = clazz2.getMethod("setStuNo",String.class);
        method.invoke(student,"s02");
        System.out.println(student);

        System.out.println(method.getReturnType().toString());


    }

}
