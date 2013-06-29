package blog;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { BlogConfiguration.class, SecurityConfiguration.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/mvc/*" };
    }
    
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new DelegatingFilterProxy("springSecurityFilterChain")};
    }
      
    
}
