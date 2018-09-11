package hw.student.service;

import hw.student.dao.IStudentRepository;
import hw.student.dto.ScoreDto;
import hw.student.dto.StudentDto;
import hw.student.dto.StudentEditDto;
import hw.student.dto.StudentResponseDto;
import hw.student.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class StudentServiceImpl implements IStudentService {
	
	@Autowired
	IStudentRepository studentRepository;

	@Override
	public boolean addStudent(StudentDto student) {
		Student newStudent = new Student(student.getId(),
				student.getName(), student.getPassword());
		return studentRepository.addStudent(newStudent);
	}

	@Override
	public StudentResponseDto deleteStudent(int id) {

		Student student = studentRepository.removeStudent(id);
		return studentResponseDto(student);
	}

	@Override
	public StudentDto editStudent(int id, StudentEditDto student) {

		Student editS = new Student(id, student.getName(), student.getPassword());
		Student oldS = studentRepository.editStudent(editS);

		if(oldS == null){
			return null;
		}

		copyData(oldS, editS);

		StudentDto studentDto = null;

		try {
			studentDto = createStudentDto(oldS);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return studentDto;
	}

	private void copyData(Student oldS, Student editS) {

		oldS.getScores().forEach(editS::addScore);

		if(editS.getName() == null){
			editS.setName(oldS.getName());
		}
		if(editS.getPassword() == null){
			editS.setPassword(oldS.getPassword());
		}

	}

	private StudentDto createStudentDto(Student studentOld) throws NoSuchFieldException, IllegalAccessException {

		StudentDto studentDto = new StudentDto();

		for (Field field : StudentDto.class.getDeclaredFields()) {

			field.setAccessible(true);
			Field fieldOld = Student.class.getDeclaredField(field.getName());
			fieldOld.setAccessible(true);
			field.set(studentDto, fieldOld.get(studentOld));

		}

		return studentDto;

	}

	@Override
	public StudentResponseDto getStudent(int id) {
		Student student = studentRepository.getStudentById(id);
		return studentResponseDto(student);
	}

	private StudentResponseDto studentResponseDto(Student student) {
		if(student == null){
			return null;
		}
		StudentResponseDto studentResponse = new StudentResponseDto();
		studentResponse.setId(student.getId());
		studentResponse.setName(student.getName());
		studentResponse.setScores(student.getScores());
		return studentResponse;
	}

	@Override
	public boolean addScore(int id, ScoreDto score) {

		Student student = studentRepository.getStudentById(id);
		return student.addScore(score.getExamName(), score.getScore());
	}

}
