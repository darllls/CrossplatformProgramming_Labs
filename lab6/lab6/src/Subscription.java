import java.io.Serializable;

public class Subscription implements Serializable {
    String surname;
    String name;
    String email;
    Reader reader;

    public Subscription(String surname, String name, String email, Reader reader) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.reader = reader;
    }

    public Subscription() {
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "surname='" + surname +
                "', name='" + name +
                "', email='" + email +
                "', reader=" + reader +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
