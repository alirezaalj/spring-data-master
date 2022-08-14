package ir.alirezaaliajni.practice.spring.data.movieboxproject.aop;

import ir.alirezaaliajni.practice.spring.data.movieboxproject.model.User;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * @author alireza alijani : https://alirezaalijani.ir
 * @user alireza
 * @date 8/13/22 - 10:45 PM
 * @project MasterSpringDataAll
 */
@Slf4j
public class AuditTrailListener {

    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(User user) {
        if (user.getId() == 0) {
            log.info("[USER AUDIT] About to add a user");
        } else {
            log.info("[USER AUDIT] About to update/delete user: " + user.getId());
        }
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(User user) {
        log.info("[USER AUDIT] add/update/delete complete for user: " + user.getId());
    }

    @PostLoad
    private void afterLoad(User user) {
        log.info("[USER AUDIT] user loaded from database: " + user.getId());
    }
}
