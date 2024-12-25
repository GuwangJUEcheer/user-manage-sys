package hokumei.sys.usermanagesys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("hokumei.sys.usermanagesys.mapper")
public class UserManageSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManageSysApplication.class, args);
	}

}
