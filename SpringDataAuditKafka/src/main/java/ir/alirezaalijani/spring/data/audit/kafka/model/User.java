package ir.alirezaalijani.spring.data.audit.kafka.model;

import ir.alirezaalijani.spring.data.audit.kafka.model.audit.KafkaAuditListener;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(KafkaAuditListener.class)
public class User implements BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true,nullable = false,length = 50,updatable = false)
    private String username;
    @Column(unique = true,nullable = false,length = 100,updatable = false)
    private String email;
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


    public void addRole(Role role){
        if (roles==null){
            roles=new HashSet<>();
        }
        roles.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public String getIdentity() {
        return String.valueOf(this.id);
    }
}
