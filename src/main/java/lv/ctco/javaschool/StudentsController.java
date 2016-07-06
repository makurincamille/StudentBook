package lv.ctco.javaschool;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/students")
public class StudentsController {


    private List<Student> students = new ArrayList<Student>() {{
        Student student = new Student();
        student.setName("Vlad");
        student.setSurname("Drakula");
        add(student);
    }};

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> students() {
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> studentById(@PathVariable("id") int id) {
        try {
            Student student = students.stream().filter((s) -> s.getId() == id).findFirst().get();
            return new ResponseEntity<>(student, HttpStatus.OK);
        }catch(NoSuchElementException e){

            return new ResponseEntity<>("There is no such student.",HttpStatus.NOT_FOUND);
        }catch (NumberFormatException e){
            return new ResponseEntity<>("Wrong student id",HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> studentsPost(@RequestBody Student student) {
        students.add(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> studentsPut(@PathVariable("id") int id, @RequestBody Student student) {
        Student studentOld = students.stream().filter((s) -> s.getId() == id).findFirst().get();
        studentOld.setName(student.getName());
        studentOld.setSurname(student.getSurname());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> studentsDelete(@PathVariable("id") int id) {
        Student student = students.stream().filter((s) -> s.getId() == id).findFirst().get();
        students.remove(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
