
package mx.uv.fei.logic;


public class User {
     private String idUser;
     private String firstName;
     private String middleName;
     private String lastName;
     private String institutionalEmail;
     private int idStatus;
     private int type;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }   
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInstitutionalEmail() {
        return institutionalEmail;
    }

    public void setInstitutionalEmail(String institutionalEmail) {
        this.institutionalEmail = institutionalEmail;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    @Override 
    public boolean equals(Object object) {
        if((object == null) || (object.getClass() != this.getClass())) {
            return false;
        } 
       final User otherUser = (User) object;
       return (this.idUser == null ? otherUser.idUser == null : this.idUser.equals(otherUser.idUser))
            && (this.firstName == null ? otherUser.firstName == null : this.firstName.equals(otherUser.firstName))
            && (this.middleName == null ? otherUser.middleName == null : this.middleName.equals(otherUser.middleName))   
            && (this.lastName == null ? otherUser.lastName == null : this.lastName.equals(otherUser.lastName))
            && (this.institutionalEmail == null ? otherUser.institutionalEmail == null : this.institutionalEmail.equals(otherUser.institutionalEmail))
            && this.idStatus == otherUser.idStatus
            && this.type == otherUser.type;
        }
    }
     
