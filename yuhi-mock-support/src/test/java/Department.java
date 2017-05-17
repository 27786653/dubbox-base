import java.util.ArrayList;
import java.util.List;

public class Department {
	private int id;
	
	private String description;
	
	private List<Student> students = new ArrayList<Student>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	@Override
	public String toString() {
		return "\n\tdepartment id: " + id + " description: " + description + "\n\tstudents: " + students;
	}
}
