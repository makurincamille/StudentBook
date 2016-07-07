package lv.ctco.javaschool;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student {

    @Id
    @Column
    @GeneratedValue
    private int Id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Assignment> assignments;

    public List<Assignment> getAssignments() {
        // assignments = assignmentRepository.fin;
        return assignments;
    }

    public Student() {
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
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

    public void addAssignment(Assignment assignment){
        assignments.add(assignment);
    }

}
