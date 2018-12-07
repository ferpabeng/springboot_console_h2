package es.ferpabeng.console.config;

import javax.sql.DataSource;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class WebConfiguration {
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
    
    @Bean
    @Profile("default")
    public DataSource embeddedDatasource() {
	return new EmbeddedDatabaseBuilder()
			.setName("testDB")
			.setType(EmbeddedDatabaseType.H2)
			.addScript("classpath:schema_h2.sql")
			.build();
    }
}
