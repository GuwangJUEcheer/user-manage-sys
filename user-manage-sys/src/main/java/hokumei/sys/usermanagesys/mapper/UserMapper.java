package hokumei.sys.usermanagesys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hokumei.sys.usermanagesys.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author guwangjue
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2024-12-02 10:25:29
* @Entity hokumei.sys.usermanagesys.model.User.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select(value = "select * from user where username=#{username} and password=#{password} limit 1")
    public User selectByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

    @Select(value = "select * from user where id = #{id}")
    public User getUserById(@Param("id")long id);

    @Select(value = "select * from user")
    public List<User> getAllUser();
}




