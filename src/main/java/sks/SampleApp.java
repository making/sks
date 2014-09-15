package sks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import sks.domain.repository.dummy.DummyRepository;

@EnableAutoConfiguration(exclude = {
        EmbeddedServletContainerAutoConfiguration.class,
        JmxAutoConfiguration.class,
        WebMvcAutoConfiguration.class,
        ThymeleafAutoConfiguration.class,
        MultipartAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class
})
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(pattern = "sks.app.*", type = FilterType.REGEX),
        @ComponentScan.Filter(value = SecurityConfig.class, type = FilterType.ASSIGNABLE_TYPE)
})
public class SampleApp implements CommandLineRunner {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext ctx = new SpringApplicationBuilder()
                .sources(SampleApp.class)
                .web(false)
                .run(args)) {
        }
    }

    @Autowired
    DummyRepository dummyRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(dummyRepository.findOne());
    }
}
