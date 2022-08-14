package ir.alirezaaliajni.practice.spring.data.movieboxproject.model;

import ir.alirezaaliajni.practice.spring.data.movieboxproject.aop.AuditTrailListener;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(AuditTrailListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 50)
    @Column(unique = true,nullable = false,length = 50,updatable = false)
    private String username;
    @Size(max = 100)
    @Column(unique = true,nullable = false,length = 100,updatable = false)
    private String email;
    @Column(nullable = false)
    private Boolean accountNonExpired;
    @Column(nullable = false)
    private Boolean accountNonLocked;
    @Column(nullable = false)
    private Boolean credentialsNonExpired;
    @Column(nullable = false)
    private Boolean emailVerification=false;
    @Column(nullable = false)
    private Boolean enable;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @Column(length = 80)
    private String password;
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @Transient
    private String someDummyString;

    @PrePersist
    public void logNewUserAttempt() {
        log.info("Attempting to add new user with username: " + username);
    }

    @PostPersist
    public void logNewUserAdded() {
        log.info("Added user '{}' with ID: {}" ,username, id);
    }

    @PreRemove
    public void logUserRemovalAttempt() {
        log.info("Attempting to delete user: {}" , username);
    }

    @PostRemove
    public void logUserRemoval() {
        log.info("Deleted user: " + username);
    }

    @PreUpdate
    public void logUserUpdateAttempt() {
        log.info("Attempting to update user: {}" , username);
    }

    @PostUpdate
    public void logUserUpdate() {
        log.info("Updated user: {}" , username);
    }

    @PostLoad
    public void logUserLoad() {
//        fullName = firstName + " " + lastName;

        log.info("After load user: {}",username);
        this.someDummyString = "dummy-"+username;
    }

    public void addRole(Role role){
        if (roles==null){
            roles=new HashSet<>();
        }
        roles.add(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", emailVerification=" + emailVerification +
                ", enable=" + enable +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", password='" + password + '\'' +
                '}';
    }
}
