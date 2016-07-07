package lv.ctco.javaschool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> students() {
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> studentById(@PathVariable("id") int id) {
        try {
            Student student = studentRepository.findOne(id);
            //Student student = students.stream().filter((s) -> s.getId() == id).findFirst().get();
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (NoSuchElementException e) {

            return new ResponseEntity<>("There is no such student.", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> studentsPost(@RequestBody Student student) {
        studentRepository.save(student);
        //students.add(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @RequestMapping(path = "/{id}/assignment", method = RequestMethod.POST)
    public ResponseEntity<?> addAssignment(@PathVariable("id") int id, @RequestBody Assignment assignment) {
        Student student = studentRepository.findOne(id);
        student.addAssignment(assignment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> studentsPut(@PathVariable("id") int id, @RequestBody Student student) {
        //Student studentOld = students.stream().filter((s) -> s.getId() == id).findFirst().get();
        //studentOld.setName(student.getName());
        //studentOld.setSurname(student.getSurname());
        Student studentOld = studentRepository.findOne(id);
        studentOld.setName(student.getName());
        studentOld.setSurname(student.getSurname());
        studentRepository.save(studentOld);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> studentsDelete(@PathVariable("id") int id) {
        try {
            studentRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("There is no such student.", HttpStatus.NOT_FOUND);
        }

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>("Wrong student id in request!", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleMessageNotReadable(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Incerrect data!", HttpStatus.BAD_REQUEST);
    }
}
