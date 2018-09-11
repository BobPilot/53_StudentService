package hw.student.dao;

import hw.student.entities.Student;
import org.springframework.stereotype.Repository;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StudentRepositoryImpl implements IStudentRepository {
	Map<Integer, Student> students = new ConcurrentHashMap<>();

	@Override
	public boolean addStudent(Student student) {
		return students.putIfAbsent(student.getId(), student) == null;
	}

	@Override
	public Student removeStudent(int id) {
		return students.remove(id);
	}

	@Override
	public Student getStudentById(int id) {
		return students.get(id);
	}

	@Override
	public Student editStudent(Student student) {
		return students.replace(student.getId(), student);
	}

}
