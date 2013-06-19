package blog.core;

import java.io.Serializable;

public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String names = "";

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
    
}
