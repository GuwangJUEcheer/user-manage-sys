package hokumei.sys.usermanagesys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import hokumei.sys.usermanagesys.common.ErrorCode;
import hokumei.sys.usermanagesys.exception.BusinessException;
import hokumei.sys.usermanagesys.mapper.UserMapper;
import hokumei.sys.usermanagesys.model.User;
import hokumei.sys.usermanagesys.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guwangjue
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-12-18 20:26:07
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User>
        implements UserService {
    /**
     * 盐值
     */
    private static final String salt = "test01";

    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode) {
        // 1. 校验账号、密码是否为空
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword,planetCode)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"表单必须项目为空"); // 空值
        }
        // 账号长度不符合
        // 2. 校验账号长度
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号过短");
        }

        // 3. 校验密码长度
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度过短");// 密码长度不符合
        }

        if(planetCode.length()>5){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"星球已满");
        }

        // 4. 校验账号是否包含特殊字符
        String validPattern = "^[a-zA-Z0-9_]+$"; // 仅允许字母、数字、下划线
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (!matcher.matches()) {
            return -1; // 包含特殊字符
        }

        // 5. 校验密码是否一致
        if (!userPassword.equals(checkPassword)) {
            return -1; // 密码不一致
        }

        // 6. 检查账号是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userAccount);
        long count = this.count(queryWrapper);
        if (count > 0) {
            return -1; // 账号已存在
        }

        // 6. 检查账号是否重复
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper.eq("planetCode", planetCode);
        if (this.count(queryWrapper) > 0) {
            return -1; // 账号已存在
        }

        // 7. 对密码进行加密后保存到数据库 盐值 salt
        final String salt = "test01";
        String encryptedPassword = DigestUtils.md5DigestAsHex((salt + userPassword).getBytes());
        User user = new User();
        user.setUsername(userAccount);
        user.setPassword(encryptedPassword);
        user.setPlanetCode(planetCode);
        boolean result = this.save(user);
        if(!result){return -1;}
        return user.getId();
    }


    @Override
    public User login(String userAccount, String userPassword, HttpServletRequest request) {

        // 1. 校验账号、密码是否为空
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null; // 空值
        }

        // 2. 校验账号长度
        if (userAccount.length() < 4) {
            return null; // 账号长度不符合
        }

        // 3. 校验密码长度
        if (userPassword.length() < 8) {
            return null; // 密码长度不符合
        }

        // 4. 校验账号是否包含特殊字符
        String validPattern = "^[a-zA-Z0-9_]+$"; // 仅允许字母、数字、下划线
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (!matcher.matches()) {
            return null; // 包含特殊字符
        }

        // 5. 对密码进行加密后保存到数据库 进行查询
        String encryptedPassword = DigestUtils.md5DigestAsHex((salt + userPassword).getBytes());
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", userAccount);
//        queryWrapper.eq("password", encryptedPassword);
        User user  = userMapper.selectByUserNameAndPwd(userAccount,encryptedPassword);
        if(user == null){
            log.info("login error");
            return null;
        }
        User safetyUser = new User();
        safetyUser.setId(user.getId());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setGender(user.getGender());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getEmail());
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        return user;
    }

    @Override
    public User getUserById(long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAllUser();
    }

    @Override
    public void logOut(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
    }
}




