package tech.itpark.xml;

import lombok.Data;
import tech.itpark.connector.Connector;

import javax.sql.DataSource;

@Data
public class XMLConnector implements Connector {
    private final String login;
    private final String password;
    private final DataSource dataSource;

    public XMLConnector(String login,
                        String password,
                        DataSource annotationDataSource) {
        this.login = login;
        this.password = password;
        this.dataSource = annotationDataSource;
    }

    @Override
    public String toString() {
        return "XMLConnector{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}