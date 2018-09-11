package hw.student.service;


import hw.student.dto.ScoreDto;
import hw.student.dto.StudentDto;
import hw.student.dto.StudentEditDto;
import hw.student.dto.StudentResponseDto;

public interface IStudentService {
	
	boolean addStudent(StudentDto student);
	
	StudentResponseDto deleteStudent(int id);
	
	StudentDto editStudent(int id, StudentEditDto student);
	
	StudentResponseDto getStudent(int id);
	
	boolean addScore(int id, ScoreDto score);
	
	
	
	

}
