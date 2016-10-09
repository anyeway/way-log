package com.way.log;

import com.nd.gaea.rest.config.WafWebMvcConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * Created by way on 2016/10/8.
 */
@Configuration
public class OperateLogApplication extends WafWebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new OperateLogInterceptor());
    }




//    public static class GaeaWebApplicationInitializer extends AbstractWafWebApplicationInitializer {
//        public GaeaWebApplicationInitializer() {
//        }
//
//        protected Class<?>[] getServletConfigClasses() {
//            return new Class[0];
//        }
//
//        public void onStartup(ServletContext servletContext) throws ServletException {
//            this.registerFilters(servletContext);
//        }
//
//        protected void registerFilters(ServletContext servletContext) {
//            this.addFilter(servletContext, "operateLogFilter", new DelegatingFilterProxy("operateLogFilter"));
//        }
//    }
}
