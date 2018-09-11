package hw.student.controller;

import hw.student.api.StudentsURI;
import hw.student.dto.ScoreDto;
import hw.student.dto.StudentDto;
import hw.student.dto.StudentEditDto;
import hw.student.dto.StudentResponseDto;
import hw.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentsController {

	@Autowired
	IStudentService studentService;

	@PostMapping(StudentsURI.STUDENT) // "/student"
	public boolean addStudent(@RequestBody StudentDto student) {
		return studentService.addStudent(student);
	}

	@DeleteMapping(StudentsURI.STUDENT)
	public StudentResponseDto removeStudent(@RequestParam int id) {
		return studentService.deleteStudent(id);
	}

	@PutMapping(StudentsURI.STUDENT + "/{id}")
	public StudentDto editStudent(@PathVariable int id,
			@RequestBody StudentEditDto student) {
		return studentService.editStudent(id, student);
	}

	@GetMapping(StudentsURI.STUDENT + "/{id}")
	public StudentResponseDto getStudent(@PathVariable int id) {
		return studentService.getStudent(id);
	}

	@PutMapping(StudentsURI.TEACHER + "/{id}")
	public boolean addScore(@PathVariable int id,
			@RequestBody ScoreDto score) {
		return studentService.addScore(id, score);
	}

}




