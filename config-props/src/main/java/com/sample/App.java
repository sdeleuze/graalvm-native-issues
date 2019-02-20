package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.type.classreading.MetadataReaderFactory;

@SpringBootConfiguration
public class App {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(App.class);
        app.setApplicationContextClass(GenericApplicationContext.class);
        app.addInitializers(new Initializer());
        app.setWebApplicationType(WebApplicationType.NONE);
        System.err.println(app.run(args).getBean(Config.class).getName());
    }

}

class Initializer implements ApplicationContextInitializer<GenericApplicationContext> {

    @Override
    public void initialize(GenericApplicationContext context) {
        if (context.getBeanFactory().getBeanNamesForType(
                PropertySourcesPlaceholderConfigurer.class, false, false).length == 0) {
            context.registerBean(PropertySourcesPlaceholderConfigurer.class,
                    () -> new PropertySourcesPlaceholderConfigurer());
        }

        if (!context.getBeanFactory().containsBean(
                AnnotationConfigUtils.CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME)) {
            // Switch off the ConfigurationClassPostProcessor
            context.registerBean(
                    AnnotationConfigUtils.CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME,
                    DummyProcessor.class, () -> new DummyProcessor());
            // But switch on other annotation processing
            AnnotationConfigUtils.registerAnnotationConfigProcessors(context);
        }
        if (!context.getBeanFactory()
                .containsBean(ConfigurationBeanFactoryMetadata.BEAN_NAME)) {
            context.registerBean(ConfigurationBeanFactoryMetadata.BEAN_NAME,
                    ConfigurationBeanFactoryMetadata.class,
                    () -> new ConfigurationBeanFactoryMetadata());
            context.registerBean(ConfigurationPropertiesBindingPostProcessor.BEAN_NAME,
                    ConfigurationPropertiesBindingPostProcessor.class,
                    () -> new ConfigurationPropertiesBindingPostProcessor());
        }
        context.registerBean(Config.class, () -> new Config());
    }

    public static class DummyProcessor {

        public void setMetadataReaderFactory(MetadataReaderFactory obj) {
        }

    }

}

@ConfigurationProperties("app")
class Config {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}