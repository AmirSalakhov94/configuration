package tech.itpark.kotlin;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import tech.itpark.connector.Connector;

import javax.sql.DataSource;

@Data
public class KotlinConnector implements Connector {

    private final String login;
    private final String password;
    private final DataSource dataSource;

    public KotlinConnector(@Value("${login}") String login,
                           @Value("${password}") String password,
                           @Autowired DataSource sqLiteDataSource) {
        this.login = login;
        this.password = password;
        this.dataSource = sqLiteDataSource;
    }

    @Override
    public String toString() {
        return "KotlinConnector{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}