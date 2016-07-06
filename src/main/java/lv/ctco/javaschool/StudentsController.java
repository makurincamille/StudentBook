package lv.ctco.javaschool;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class StudentsController {

    @RestController
    @RequestMapping("/students")
    public class StudentController {

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
            Student student = students.stream().filter((s) -> s.getId() == id).findFirst().get();
            return new ResponseEntity<>(student, HttpStatus.OK);
        }

        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity<?> studentsPost(@RequestBody Student student) {
            students.add(student);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
