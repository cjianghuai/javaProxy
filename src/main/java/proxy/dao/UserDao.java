package proxy.dao;

/**
 * Created by chenjh on 2019/10/17.
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("save success!");
    }
}
