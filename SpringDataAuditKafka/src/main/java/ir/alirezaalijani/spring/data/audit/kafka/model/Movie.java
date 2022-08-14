package ir.alirezaalijani.spring.data.audit.kafka.model;

import ir.alirezaalijani.spring.data.audit.kafka.model.audit.KafkaAuditListener;

import javax.persistence.*;

@Entity
@Table(name = "movie")
@EntityListeners(KafkaAuditListener.class)
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(length = 200)
    private String description;
}
