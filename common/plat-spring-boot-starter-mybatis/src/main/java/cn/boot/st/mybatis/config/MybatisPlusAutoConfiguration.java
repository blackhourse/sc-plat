package cn.boot.st.mybatis.config;

import cn.boot.st.mybatis.core.injector.CustomSqlInject;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author Hccake 2020/8/3
 * @version 1.0
 */
public class MybatisPlusAutoConfiguration {

    /**
     * 自定义方法扩展注入器
     *
     * @return ISqlInjector CustomSqlInject
     */
    @Bean
    @ConditionalOnMissingBean(ISqlInjector.class)
    public ISqlInjector sqlInjector() {
        return new CustomSqlInject();
    }
}
