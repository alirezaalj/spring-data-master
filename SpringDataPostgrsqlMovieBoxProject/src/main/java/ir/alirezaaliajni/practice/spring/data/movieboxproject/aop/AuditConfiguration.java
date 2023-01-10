package ir.alirezaaliajni.practice.spring.data.movieboxproject.aop;

import ir.alirezaaliajni.practice.spring.data.movieboxproject.aop.EntityAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/16/22 - 11:28 PM
 * @project MasterSpringDataAll
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class AuditConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new EntityAuditorAware();
    }
}
