package mx.uv.fei.controllers;

/**
 *
 * @author Star3oy
 */
public class TableUsers {
    String identificator;
    String name;

    public TableUsers(String identificator, String name) {
        this.identificator = identificator;
        this.name = name;
    }

    public String getIdentificator() {
        return identificator;
    }

    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
