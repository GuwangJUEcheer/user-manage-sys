package hokumei.sys.usermanagesys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import hokumei.sys.usermanagesys.model.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
* @author guwangjue
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-12-02 10:25:29
*/
public interface UserService extends IService<User> {

    String USER_LOGIN_STATE = "LoginState";
    /**
     * 用户注册
     *
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 注册结果（返回用户ID）
     */
    long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode);

    /**
     * 登录方法
     *
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return
     */
    User login (String userAccount, String userPassword, HttpServletRequest request);

    User getUserById(long id);

    List<User> getAll();

    void logOut(HttpServletRequest request);
}
