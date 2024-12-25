package hokumei.sys.usermanagesys.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有路径
                .allowedOrigins("http://localhost:8000") // 指定允许的前端地址
                .allowedMethods("*") // 允许所有 HTTP 方法
                .allowedHeaders("*") // 允许所有请求头
                .allowCredentials(true); // 允许携带 Cookies
    }
}
