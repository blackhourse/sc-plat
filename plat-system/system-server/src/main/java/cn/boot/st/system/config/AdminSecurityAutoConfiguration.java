package cn.boot.st.system.config;

import cn.boot.st.system.interceptor.AdminSecurityInterceptor;
import cn.boot.st.web.config.CommonWebAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AutoConfigureAfter(CommonWebAutoConfiguration.class) // 在 CommonWebAutoConfiguration 之后自动配置，保证过滤器的顺序
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class AdminSecurityAutoConfiguration implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public AdminSecurityInterceptor adminSecurityInterceptor() {
        return new AdminSecurityInterceptor();
    }


    // ========== 拦截器相关 ==========
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // AdminSecurityInterceptor 拦截器
        registry.addInterceptor(this.adminSecurityInterceptor());
        logger.info("[addInterceptors][加载 AdminSecurityInterceptor 拦截器完成]");
        System.out.println(applicationName);

    }
}
