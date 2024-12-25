package hokumei.sys.usermanagesys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hokumei.sys.usermanagesys.common.BaseResponse;
import hokumei.sys.usermanagesys.common.ResultUtils;
import hokumei.sys.usermanagesys.domain.UserLoginRequest;
import hokumei.sys.usermanagesys.domain.UserRegisterRequest;
import hokumei.sys.usermanagesys.model.User;
import hokumei.sys.usermanagesys.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest userRegisterRequest) {

        if(userRegisterRequest == null) {
            return new BaseResponse<Long>(0, (long) -1,"NG");
        }

        String userAccount = userRegisterRequest.getUsername();
        String userPassword = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        Long result = userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
        return ResultUtils.success(result);
    }

    @PostMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAll();
    }


    @PostMapping("/login")
    public User login(@RequestBody UserLoginRequest loginRequest, HttpServletRequest request) {

        if(loginRequest == null) {
            return null;
        }
        String userAccount = loginRequest.getUsername();
        String userPassword = loginRequest.getPassword();
        if(StringUtils.isAnyBlank(userAccount, userPassword)){
            return null;
        }
        return userService.login(userAccount,userPassword,request);
    }

    /**
     * 根据用户名模糊查询所有的user
     * @param userAccount 用户名
     * @return 用户列表
     */
    @GetMapping("/search")
    public List<User> search(@RequestParam String userAccount,HttpServletRequest request) {
        //仅有管理员可以查询
        Object userObject = request.getSession().getAttribute(UserService.USER_LOGIN_STATE);
        User user = (User) userObject;
        if (user == null || user.getRoleId() != 1) {
            return new ArrayList<User>();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username",userAccount);
        return userService.list(queryWrapper);
    }

    /**
     * 根据id 删除用户
     * @param id 用户id
     * @return 返回逻辑删除结果
     */
    @PostMapping("/deleteUser")
    public boolean delete(@RequestBody Long id) {

       if(id == null) {
           return false;
       }
       return userService.removeById(id);
    }

    @PostMapping("/logOut")
    public void logOut(HttpServletRequest request) {
       userService.logOut(request);
    }

    /**
     * 获取当前用户
     * @param request 请求
     * @return 返回当前用户详细信息
     */
    @GetMapping("/current")
    public User getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(UserService.USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            return null;
        }
        long userId = currentUser.getId();
        return userService.getUserById(userId);
    }
}
