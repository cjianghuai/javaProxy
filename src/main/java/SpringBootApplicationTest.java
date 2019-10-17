import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created by chenjh on 2019/7/26.
 */
@SpringBootApplication(scanBasePackages={"cn.sample"})
@MapperScan({"cn.sample.**.dao"})
public class SpringBootApplicationTest extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationTest.class, args);
    }
}
