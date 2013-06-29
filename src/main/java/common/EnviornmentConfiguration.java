package common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

public class EnviornmentConfiguration implements InvocationHandler {
             
    final Environment env;
    
    private EnviornmentConfiguration(Environment env) {
        this.env = env;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T configuration(Environment env, Class<T> configuration) {
        EnviornmentConfiguration configProxy = new EnviornmentConfiguration(env);
        return (T) Proxy.newProxyInstance(configuration.getClassLoader(), new Class[]{ configuration }, configProxy);
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Property property = method.getAnnotation(Property.class);
        Assert.notNull(property, "The config method has no Property annotation!");
        return env.getProperty(property.value(), method.getReturnType());
    }

}
