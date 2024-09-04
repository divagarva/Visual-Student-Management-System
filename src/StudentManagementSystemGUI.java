import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagementSystemGUI {
    private StudentManager studentManager;
    private JFrame frame;
    private JTextField idField, nameField, ageField, courseField;
    private JTextArea studentDisplayArea;

    public StudentManagementSystemGUI() {
        studentManager = new StudentManager();
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        courseField = new JTextField();

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Course:"));
        inputPanel.add(courseField);

        frame.add(inputPanel, BorderLayout.NORTH);

        studentDisplayArea = new JTextArea();
        studentDisplayArea.setEditable(false);
        frame.add(new JScrollPane(studentDisplayArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View All Students");
        JButton searchButton = new JButton("Search Student");
        JButton deleteButton = new JButton("Delete Student");

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllStudents();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        frame.setVisible(true);
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String course = courseField.getText();

            Student student = new Student(id, name, age, course);
            studentManager.addStudent(student);
            clearInputFields();
            JOptionPane.showMessageDialog(frame, "Student added successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input, please try again.");
        }
    }

    private void viewAllStudents() {
        studentDisplayArea.setText("");
        for (Student student : studentManager.getStudents()) {
            studentDisplayArea.append(student.toString() + "\n");
        }
    }

    private void searchStudent() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter student ID to search:"));
            Student student = studentManager.searchStudent(id);
            if (student != null) {
                JOptionPane.showMessageDialog(frame, student.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "Student not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input, please try again.");
        }
    }

    private void deleteStudent() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter student ID to delete:"));
            studentManager.deleteStudent(id);
            JOptionPane.showMessageDialog(frame, "Student deleted successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input, please try again.");
        }
    }

    private void clearInputFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        courseField.setText("");
    }

    public static void main(String[] args) {
        new StudentManagementSystemGUI();
    }
}
