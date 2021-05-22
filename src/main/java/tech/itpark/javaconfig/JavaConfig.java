package tech.itpark.javaconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

public class JavaConfig {

    @Value("${login}")
    private String login;
    @Value("${password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocation(new ClassPathResource("db.properties"));
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public DataSource dataSource() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite::memory:");
        return dataSource;
    }

    @Bean
    public JavaConfigConnector javaConfigConnector() {
        return new JavaConfigConnector(login, password, dataSource());
    }
}
