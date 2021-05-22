package tech.itpark.kotlin;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.context.support.beans
import org.springframework.core.io.ClassPathResource
import org.sqlite.SQLiteDataSource

val beans = beans {

    bean {
        PropertySourcesPlaceholderConfigurer().apply {
            setLocation(ClassPathResource("db.properties"))
        }
    }
    bean("sqLiteDataSource") {
        SQLiteDataSource().apply {
            url = "jdbc:sqlite::memory:"
        }
    }

    bean<KotlinConnector>("kotlinConnector")
}
