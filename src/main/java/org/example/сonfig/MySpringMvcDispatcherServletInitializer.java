package org.example.сonfig;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
//        addEncodingFilter(aServletContext);//    додала з інтернету

        super.onStartup(aServletContext);
//        aServletContext.addFilter("characterSetFilter", new CharacterSetFilter()).addMappingForUrlPatterns(null, true, "/*");
        addEncodingFilter(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }
    private void registerHiddenFieldFilter(ServletContext aServletContext) {
        aServletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter())
                .addMappingForUrlPatterns(null, true, "/*");
    }

//    додала з інтернету
    private void addEncodingFilter(ServletContext servletContext) {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        EnumSet<DispatcherType> dispatcherTypes
                = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR);

        FilterRegistration.Dynamic characterEncoding
                = servletContext.addFilter("characterEncoding", characterEncodingFilter);
        characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
        characterEncoding.setAsyncSupported(true);
    }

    //    додала з інтернету
//    @Override
//    protected Filter[] getServletFilters() {
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//        return new Filter[] {characterEncodingFilter};
//    }
//    @Override
//    protected Filter[] getServletFilters() {
//        Filter[] filters;
//        CharacterEncodingFilter encFilter;
//        HiddenHttpMethodFilter httpMethodFilter = new HiddenHttpMethodFilter();
//        encFilter = new CharacterEncodingFilter();
//        encFilter.setEncoding("UTF-8");
//        encFilter.setForceEncoding(true);
//        filters = new Filter[] {httpMethodFilter, encFilter};
//        return filters;
//    }
}
