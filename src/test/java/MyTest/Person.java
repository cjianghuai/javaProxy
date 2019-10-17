package MyTest;

/**
 * Created by chenjh on 2019/8/1.
 */
public class Person<T> {
    public String weight;
    public String height;
    public Person(String weight,String height){
        super();
        this.weight = weight;
        this.height = height;
    }

    public Person(){
        super();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person [weight=" + weight + ", height=" + height + "]";
    }
}
