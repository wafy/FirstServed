package com.event.reward.test.util;

import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

/**
 * 레스트독 컨피그레이션.
 *
 * {@link org.springframework.restdocs.templates.asciidoctor 스니펫 커스텀}
 */
@TestConfiguration
public class RestDocsConfiguration {

  @Bean
  public RestDocsMockMvcConfigurationCustomizer restDocsMockMvcConfigurationCustomizer() {
    return configurer -> configurer.operationPreprocessors()
        .withRequestDefaults(prettyPrint())
        .withResponseDefaults(prettyPrint());
  }
}
