package tech.itpark.annotation;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class AnnotationPropertyPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {
    public AnnotationPropertyPlaceholderConfigurer() {
        setLocation(new ClassPathResource("db.properties"));
    }
}
