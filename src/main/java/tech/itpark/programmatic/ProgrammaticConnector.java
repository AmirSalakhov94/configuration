package tech.itpark.programmatic;

import lombok.Data;
import tech.itpark.connector.Connector;

import javax.sql.DataSource;

@Data
public class ProgrammaticConnector implements Connector {
    private final String login;
    private final String password;
    private final DataSource dataSource;

    public ProgrammaticConnector(String login, String password, DataSource dataSource) {
        this.login = login;
        this.password = password;
        this.dataSource = dataSource;
    }

    @Override
    public String toString() {
        return "Connector{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
