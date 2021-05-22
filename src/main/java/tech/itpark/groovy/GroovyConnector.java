package tech.itpark.groovy;

import lombok.Data;
import tech.itpark.connector.Connector;

import javax.sql.DataSource;

@Data
public class GroovyConnector implements Connector {
    private final String login;
    private final String password;
    private final DataSource dataSource;

    public GroovyConnector(String login, String password,
                           DataSource annotationDataSource) {
        this.login = login;
        this.password = password;
        this.dataSource = annotationDataSource;
    }

    @Override
    public String toString() {
        return "GroovyConnector{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}