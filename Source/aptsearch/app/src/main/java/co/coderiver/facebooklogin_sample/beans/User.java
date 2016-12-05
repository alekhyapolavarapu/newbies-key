package co.coderiver.facebooklogin_sample.beans;

/**
 * Created by polavarapu on 11/24/2016.
 */
public class User {
    private String userName;
    private String password;
    private String EmailId;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }
}
