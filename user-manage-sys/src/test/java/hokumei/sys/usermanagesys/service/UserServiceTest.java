package hokumei.sys.usermanagesys.service;

import hokumei.sys.usermanagesys.UserManageSysApplication;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserManageSysApplication.class})
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void insert() {
//        User user = new User();
//        user.setUsername("Aaa");
//        user.setAvatarurl("sa");
//        user.setGender(0);
//        user.setPassword("sasa2213");
//        user.setPhone("64646420");
//        user.setEmail("");
//        user.setIsvalid(0);
//        user.setCreatetime(new Date());
//        user.setUpdatetime(new Date());
//        user.setIsdelete(0);
//        userService.save(user);
        System.out.println("aaa");
    }
}