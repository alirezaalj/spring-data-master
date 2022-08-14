package ir.alirezaalijani.spring.data.audit.kafka.config.kafka;

public class KafkaAdminException extends RuntimeException{
    public KafkaAdminException() {
        super();
    }

    public KafkaAdminException(String message) {
        super(message);
    }

    public KafkaAdminException(String message, Throwable cause) {
        super(message, cause);
    }
}
