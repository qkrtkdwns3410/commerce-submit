package org.commerce.commercesubmit.common.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * packageName    : org.commerce.commercesubmit.config
 * fileName       : JpaConfig
 * author         : ipeac
 * date           : 24. 4. 23.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 23.        ipeac       최초 생성
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.commerce.commercesubmit.member.repository")
public class JpaConfig {
}