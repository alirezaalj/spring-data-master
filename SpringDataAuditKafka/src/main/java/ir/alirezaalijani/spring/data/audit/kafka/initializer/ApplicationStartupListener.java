package ir.alirezaalijani.spring.data.audit.kafka.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user: alireza
 * @date: 8/5/22 - 2:35 AM
 * @project: exam
 */
@Slf4j
@Component
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private final AppStartupInitializer initializer;

    public ApplicationStartupListener(AppStartupInitializer initializer) {
        this.initializer = initializer;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Startup listener");
        this.initializer.init();
    }
}
