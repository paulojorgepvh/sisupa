package br.com.portovelho.sisupas.config;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.portovelho.sisupas.thymeleaf.UpaDialect;

@Configuration
@EnableSpringDataWebSupport
@EnableCaching
@EnableAsync
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver jasperReportsViewResolver(DataSource datasource) {
		JasperReportsViewResolver resolver = new JasperReportsViewResolver();
		resolver.setPrefix("classpath:/relatorios/");
		resolver.setSuffix(".jasper");
		resolver.setViewNames("relatorio_*");
		resolver.setViewClass(JasperReportsMultiFormatView.class);
		resolver.setJdbcDataSource(datasource);
		resolver.setOrder(0);
		return resolver;
	}

	@Bean
	public UpaDialect upaDialect() {
		return new UpaDialect();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		NumberStyleFormatter bigDecimalFormatter = new NumberStyleFormatter("#,##0.00");
		registry.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);

		DateTimeFormatterRegistrar dateTimeFormatter = new DateTimeFormatterRegistrar();
		dateTimeFormatter.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		dateTimeFormatter.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm"));
		dateTimeFormatter.registerFormatters(registry);
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("classpath:/messages");
		bundle.setDefaultEncoding("UTF-8"); // http://www.utf8-chartable.de/ ou http://native2ascii.net/
		return bundle;
	}

	/*
	 * @Bean public LocalValidatorFactoryBean validator() {
	 * LocalValidatorFactoryBean validatorFactoryBean = new
	 * LocalValidatorFactoryBean();
	 * validatorFactoryBean.setValidationMessageSource(messageSource());
	 * 
	 * return validatorFactoryBean; }
	 * 
	 * @Override public Validator getValidator() { return validator(); }
	 */

	@Bean
	public CacheManager cacheManager() {
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
				.maximumSize(5)
				.expireAfterAccess(10,
				TimeUnit.MINUTES);
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(cacheBuilder);
		return cacheManager;
	}
}
