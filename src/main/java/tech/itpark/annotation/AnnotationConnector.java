package tech.itpark.annotation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.itpark.connector.Connector;

import javax.sql.DataSource;

@Data
@Component("annotationConnector")
public class AnnotationConnector implements Connector {
    private final String login;
    private final String password;
    private final DataSource dataSource;

    public AnnotationConnector(@Value("${login}") String login,
                               @Value("${password}") String password,
                               @Autowired DataSource annotationDataSource) {
        this.login = login;
        this.password = password;
        this.dataSource = annotationDataSource;
    }

    @Override
    public String toString() {
        return "AnnotationConnector{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}