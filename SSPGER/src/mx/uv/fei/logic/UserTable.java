package mx.uv.fei.logic;

import javafx.scene.control.CheckBox;

/**
 *
 * @author sue
 */
public class UserTable extends User {
    private CheckBox userSelected;

    public UserTable() {
        this.userSelected = new CheckBox();
    }
    
    public CheckBox getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(CheckBox userSelected) {
        this.userSelected = userSelected;
    }

}
