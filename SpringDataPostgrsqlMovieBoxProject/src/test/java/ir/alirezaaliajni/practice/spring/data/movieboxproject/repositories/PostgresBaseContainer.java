package ir.alirezaaliajni.practice.spring.data.movieboxproject.repositories;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresBaseContainer {
    static PostgreSQLContainer<?> postgreSQLContainer=new PostgreSQLContainer<>("postgres:14.1-alpine")
            .withDatabaseName("moviebox-db-test")
            .withUsername("root")
            .withPassword("root");
    static {
        postgreSQLContainer.start();
    }
}
