import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.sqlite.SQLiteDataSource
import tech.itpark.groovy.GroovyConnector

beans {
    propertyPlaceholderConfigurer PropertySourcesPlaceholderConfigurer, {
        location = 'classpath:db.properties'
    }

    sqlLiteDataSource SQLiteDataSource, {
        url = "jdbc:sqlite::memory:"
    }

    groovyConnector GroovyConnector, '${login}', '${password}', ref(sqlLiteDataSource)
}