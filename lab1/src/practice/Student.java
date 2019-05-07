package practice;

public class Student extends Person implements Notifiable {

    String login;
    String email;
    public Student(String n, String l, String e) {
        super(n);
        login = l;
        email = e;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void notify(String message) {
        System.out.println(this.getName()+ " message: "+message);
    }
}
