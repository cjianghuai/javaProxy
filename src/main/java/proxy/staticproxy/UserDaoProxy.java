package proxy.staticproxy;

import proxy.dao.IUserDao;
import proxy.dao.UserDao;

/**
 * Created by chenjh on 2019/10/17.
 */
public class UserDaoProxy implements IUserDao {
    private IUserDao target;

    UserDaoProxy(UserDao target){
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开始事务...");
        this.target.save();
        System.out.println("结束事务...");

    }
}
