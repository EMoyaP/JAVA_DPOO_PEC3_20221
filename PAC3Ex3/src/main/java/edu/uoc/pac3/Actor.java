package edu.uoc.pac3;
public class Actor {
    private String name;
    private String surname;

    public Actor(String name, String surname){
        setName(name);
        setSurname(surname);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }
}
