package com.yuhi.monitor.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import zipkin.Span;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Reporter;
import zipkin.reporter.Sender;
import zipkin.reporter.okhttp3.OkHttpSender;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import com.github.kristofa.brave.http.SpanNameProvider;
import com.github.kristofa.brave.spring.ServletHandlerInterceptor;

/**
 * This adds tracing configuration to any web mvc controllers or rest template clients. This should
 * be configured last.
 */
@Configuration
// import as the interceptors are annotation with javax.inject and not automatically wired
//@Import({BraveClientHttpRequestInterceptor.class, ServletHandlerInterceptor.class})
public class WebTracingConfiguration{
	@Value("${zpk.host}")
	private String host;
	@Value("${zpk.servicename}")
	private String trackNode;
	 /** Configuration for how to send spans to Zipkin */
	  @Bean Sender sender() {
	    return OkHttpSender.create(host+"/api/v1/spans");
	    //return LibthriftSender.create("127.0.0.1");
	    // return KafkaSender.create("127.0.0.1:9092");
	  }

	  /** Configuration for how to buffer spans into messages for Zipkin */
	  @Bean Reporter<Span> reporter() {
	    //return new LoggingReporter();
	    // uncomment to actually send to zipkin!
	    return AsyncReporter.builder(sender()).build();
	  }

	  @Bean Brave brave() {
	    return new Brave.Builder(trackNode).reporter(reporter()).build();
	  }

	  // decide how to name spans. By default they are named the same as the http method.
	  @Bean SpanNameProvider spanNameProvider() {
	    return new DefaultSpanNameProvider();
	  }

	  @Autowired
	  private ServletHandlerInterceptor serverInterceptor;

//	 @Bean  ServletHandlerInterceptor servletHandlerInterceptor() throws Exception {
//		 ServletHandlerInterceptor interceptor=
//		new ServletHandlerInterceptor(brave.serverRequestInterceptor(),
//				brave.serverResponseInterceptor(), nameProvider,
//				brave.serverSpanThreadBinder());
//		 return interceptor;
//	 } 
//	// adds tracing to the application-defined web controllers
//	  @Override
//	  public void addInterceptors(InterceptorRegistry registry) {
//	    registry.addInterceptor(serverInterceptor);
//	  }
}
