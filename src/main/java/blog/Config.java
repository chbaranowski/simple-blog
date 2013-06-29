package blog;

import common.Property;

public interface Config {

    @Property("rest.auth.username")
    String restAuthUsername();
    
    @Property("rest.auth.password")
    String restAuthPassword();
    
    @Property("rest.auth.secret")
    String restAuthSecret();
    
    @Property("db.driverClass")
    String dbDriverClass();
    
    @Property("db.url")
    String dbUrl();
    
    @Property("db.username")
    String dbUsername();
    
    @Property("db.password")
    String dbPassword();
    
}
