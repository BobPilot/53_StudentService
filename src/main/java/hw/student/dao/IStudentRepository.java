package hw.student.dao;


import hw.student.entities.Student;

public interface IStudentRepository {
	
	boolean addStudent(Student student);
	
	Student removeStudent(int id);
	
	Student getStudentById(int id);
	
	Student editStudent(Student student);

}
