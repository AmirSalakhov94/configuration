package tech.itpark;

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
import tech.itpark.javaconfig.JavaConfig;
import tech.itpark.kotlin.BeansKt;
import tech.itpark.programmatic.ProgrammaticConnector;

public class Main {
    public static void main(String[] args) {
        {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("tech.itpark.annotation");
            System.out.println(context.getBean("annotationConnector"));
        }
        {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
            System.out.println(context.getBean("javaConfigConnector"));
        }
        {
            GenericGroovyApplicationContext context = new GenericGroovyApplicationContext("context.groovy");
            System.out.println(context.getBean("groovyConnector"));
        }
        {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            System.out.println(context.getBean("xmlConnector"));
        }
        {
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
            System.out.println(context.getBean("programmaticConnector"));
        }
        {
            DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
            factory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
            GenericApplicationContext context = new GenericApplicationContext(factory);
            BeansKt.getBeans().initialize(context);
            context.refresh();
            System.out.println(context.getBean("kotlinConnector"));
        }
    }
}
