package com.event.reward.test.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@AutoConfigureRestDocs(
    uriHost = "localhost/reward",
    uriScheme = "http",
    uriPort = 8080
)
@Import({
    RestDocsConfiguration.class,
    RestDocCommonConfig.MockBeanConfiguration.class,
})
public @interface RestDocCommonConfig {

  class MockBeanConfiguration implements InitializingBean {

    @Override
    public void afterPropertiesSet() {
      // empty
    }
  }

  /**
   * spring 버전 이슈를 해결하는 설정
   */
  class SpringMigrationConfiguration {

    @Bean
    MockMvcBuilderCustomizer utf8Config() {
      return builder ->
          builder.addFilters(new CharacterEncodingFilter("UTF-8", true));
    }
  }
}
