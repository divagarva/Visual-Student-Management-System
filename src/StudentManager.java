import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student searchStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public void updateStudent(int id, Student updatedStudent) {
        Student student = searchStudent(id);
        if (student != null) {
            student.setName(updatedStudent.getName());
            student.setAge(updatedStudent.getAge());
            student.setCourse(updatedStudent.getCourse());
        }
    }

    public void deleteStudent(int id) {
        Student student = searchStudent(id);
        if (student != null) {
            students.remove(student);
        }
    }
}
