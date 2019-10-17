package proxy.dao;

/**
 * Created by chenjh on 2019/10/17.
 */
public class BMW implements ICarDao {
    @Override
    public void run() {
        System.out.println("BMW IS RUNNING...");
    }

    @Override
    public String getColor(String color) {
        System.out.println("THE BMW COLOR IS " + color);
        return color;
    }
}
