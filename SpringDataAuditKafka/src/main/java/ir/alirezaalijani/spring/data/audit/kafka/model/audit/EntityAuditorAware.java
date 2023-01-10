package ir.alirezaalijani.spring.data.audit.kafka.model.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/16/22 - 10:09 PM
 * @project MasterSpringDataAll
 */
public class EntityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Atta");
    }
}
