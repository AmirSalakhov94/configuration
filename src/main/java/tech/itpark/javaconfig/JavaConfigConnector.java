package tech.itpark.javaconfig;

import lombok.Data;
import tech.itpark.connector.Connector;

import javax.sql.DataSource;

@Data
public class JavaConfigConnector implements Connector {
    private final String login;
    private final String password;
    private final DataSource dataSource;

    public JavaConfigConnector(String login,
                               String password,
                               DataSource annotationDataSource) {
        this.login = login;
        this.password = password;
        this.dataSource = annotationDataSource;
    }

    @Override
    public String toString() {
        return "JavaConfigConnector{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}