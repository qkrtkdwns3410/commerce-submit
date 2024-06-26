package org.commerce.commercesubmit.config.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;

@Configuration
@EnableJpaRepositories(basePackages = "org.commerce.commercesubmit.member.repository", bootstrapMode = BootstrapMode.DEFERRED)
public class JpaConfig {
}