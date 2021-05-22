package tech.itpark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.sqlite.SQLiteDataSource;
import tech.itpark.annotation.AnnotationConnector;
import tech.itpark.groovy.GroovyConnector;
import tech.itpark.javaconfig.JavaConfig;
import tech.itpark.javaconfig.JavaConfigConnector;
import tech.itpark.kotlin.BeansKt;
import tech.itpark.kotlin.KotlinConnector;
import tech.itpark.programmatic.ProgrammaticConnector;
import tech.itpark.xml.XMLConnector;

class TestConfiguration {

    @Test
    void testAnnotationConfig() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext("tech.itpark.annotation");
        AnnotationConnector annotationConnector = context.getBean(AnnotationConnector.class);
        Assertions.assertNotNull(annotationConnector.getDataSource());
        Assertions.assertEquals("user", annotationConnector.getLogin());
        Assertions.assertEquals("admin", annotationConnector.getPassword());
    }

    @Test
    void testXMLConfig() {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("beans.xml");
        XMLConnector xmlConnector = context.getBean(XMLConnector.class);
        Assertions.assertNotNull(xmlConnector.getDataSource());
        Assertions.assertEquals("user", xmlConnector.getLogin());
        Assertions.assertEquals("admin", xmlConnector.getPassword());
    }

    @Test
    void testGroovyConfig() {
        GenericGroovyApplicationContext context
                = new GenericGroovyApplicationContext("context.groovy");
        GroovyConnector groovyConnector = context.getBean(GroovyConnector.class);
        Assertions.assertNotNull(groovyConnector.getDataSource());
        Assertions.assertEquals("user", groovyConnector.getLogin());
        Assertions.assertEquals("admin", groovyConnector.getPassword());
    }

    @Test
    void testProgrammaticConfig() {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(PropertySourcesPlaceholderConfigurer.class, () -> {
            PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocation(new ClassPathResource("db.properties"));
            return configurer;
        });
        context.registerBean("programmaticDataSource", SQLiteDataSource.class, () -> {
            SQLiteDataSource dataSource = new SQLiteDataSource();
            dataSource.setUrl("jdbc:sqlite::memory:");
            return dataSource;
        });

        BeanReference ref = new RuntimeBeanReference("programmaticDataSource");
        context.registerBean("programmaticConnector", ProgrammaticConnector.class, "${login}", "${password}", ref);
        context.refresh();
        ProgrammaticConnector programmaticConnector = context.getBean(ProgrammaticConnector.class);

        Assertions.assertNotNull(programmaticConnector.getDataSource());
        Assertions.assertEquals("user", programmaticConnector.getLogin());
        Assertions.assertEquals("admin", programmaticConnector.getPassword());
    }

    @Test
    void testKotlinConfig() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
        GenericApplicationContext context = new GenericApplicationContext(factory);
        BeansKt.getBeans().initialize(context);
        context.refresh();
        KotlinConnector kotlinConnector = context.getBean(KotlinConnector.class);
        Assertions.assertNotNull(kotlinConnector.getDataSource());
        Assertions.assertEquals("user", kotlinConnector.getLogin());
        Assertions.assertEquals("admin", kotlinConnector.getPassword());
    }

    @Test
    void testJavaConfig() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(JavaConfig.class);
        JavaConfigConnector javaConfigConnector = context.getBean(JavaConfigConnector.class);
        Assertions.assertNotNull(javaConfigConnector.getDataSource());
        Assertions.assertEquals("user", javaConfigConnector.getLogin());
        Assertions.assertEquals("admin", javaConfigConnector.getPassword());
    }
}
