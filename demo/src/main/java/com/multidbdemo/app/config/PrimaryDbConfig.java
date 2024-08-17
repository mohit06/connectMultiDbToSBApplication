package com.multidbdemo.app.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.multidbdemo.app.config.primary"},
        transactionManagerRef = "primaryTransactionManager",
        entityManagerFactoryRef = "primaryEntityManagerFactory"
)
@Profile("!test")
public class PrimaryDbConfig {

    @Bean(name="db1")
    @Primary
    @ConfigurationProperties(prefix = "spring.db")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("primaryEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean getEntityManagerBean(EntityManagerFactoryBuilder builder, @Qualifier("db1") DataSource dataSource){
        HashMap<String,String> prop = new HashMap<>();
        prop.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        return builder.dataSource(dataSource)
                .properties(prop)
                .packages("com.multidbdemo.app.config.primary").build();
    }

    @Bean("primaryTransactionManager")
    public PlatformTransactionManager getTransactionManager(@Qualifier("primaryEntityManagerFactory") EntityManagerFactory factory){
        return new JpaTransactionManager(factory);
    }
}
