package KioskClasses;

public class Admin {


    //Must be private so other classes can't alter the values directly
    private String Username;
    private String Password;

    //Gets and Sets ensures that the values are correct
    public void setPassword(String password) {
        Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUsername() {
        return Username;
    }
}
