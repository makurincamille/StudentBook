package lv.ctco.javaschool;

public class Student {
    private static int Id;
    private String name;
    private String surname;

    public Student() {
        this.Id++;
    }

    public int getId() {
        return Id;
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

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
