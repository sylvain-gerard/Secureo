package com.twenty.secureo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class SecureoApplication {

	private static final Logger logger = LoggerFactory.getLogger(SecureoApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SecureoApplication.class);
		Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        logger.info("\n\n----------------------------------------------------------------\n\n\t" +
                "Application {} version {} is running!\n\n\t" +
        		"Description: \t{}\n\t" +
        		"Datasource: \t{}\n\t" +
                "Server url: \t{}://localhost:{}{}\n\t" +
                "Java version: \t{}\n",
            env.getProperty("info.app.name"),
            env.getProperty("info.app.version"),
            env.getProperty("info.app.description"),
            env.getProperty("spring.datasource.url"),
            protocol,
            env.getProperty("server.port"),
            env.getProperty("spring.data.rest.base-path"),
            env.getProperty("info.app.java.version"));
	}

}
