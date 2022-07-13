package com.kurly.test.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.CharacterEncodingFilter;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@AutoConfigureRestDocs(
    uriHost = "gateway.cloud.dev.kurly.services/eventboard",
    uriScheme = "https",
    uriPort = 443
)
@MvcTestConfig
@Import({
    RestDocsConfiguration.class,
    RestDocCommonConfig.MockBeanConfiguration.class,
    CmsUserMock.class
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
