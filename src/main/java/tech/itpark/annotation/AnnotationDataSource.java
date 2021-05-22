package tech.itpark.annotation;

import org.springframework.stereotype.Component;
import org.sqlite.SQLiteDataSource;

@Component
public class AnnotationDataSource extends SQLiteDataSource {

    public AnnotationDataSource() {
        setUrl("jdbc:sqlite::memory:");
    }
}
