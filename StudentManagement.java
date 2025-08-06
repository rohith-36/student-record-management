import java.util.*;
import java.io.*;

public class StudentManagement {
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String DATA_FILE = "students.txt";
    
    public static void main(String[] args) {
        loadStudentsFromFile();
        
        while (true) {
            showMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    deleteStudent();
                    break;
                case 6:
                    saveStudentsToFile();
                    System.out.println("Data saved successfully!");
                    break;
                case 7:
                    saveStudentsToFile();
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void showMenu() {
        System.out.println("\n=== Student Record Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Save Data");
        System.out.println("7. Exit");
        System.out.print("Enter your choice (1-7): ");
    }
    
    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void addStudent() {
        System.out.println("\n=== Add New Student ===");
        
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        
        // Check if student ID already exists
        if (findStudentById(id) != null) {
            System.out.println("Error: Student with ID " + id + " already exists!");
            return;
        }
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Age: ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age format!");
            return;
        }
        
        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine();
        
        Student student = new Student(id, name, age, grade);
        students.add(student);
        System.out.println("Student added successfully!");
    }
    
    private static void viewAllStudents() {
        System.out.println("\n=== All Students ===");
        
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        System.out.printf("%-10s %-20s %-5s %-10s%n", "ID", "Name", "Age", "Grade");
        System.out.println("-".repeat(50));
        
        for (Student student : students) {
            System.out.printf("%-10s %-20s %-5d %-10s%n", 
                student.getId(), student.getName(), student.getAge(), student.getGrade());
        }
    }
    
    private static void searchStudent() {
        System.out.println("\n=== Search Student ===");
        System.out.print("Enter Student ID to search: ");
        String id = scanner.nextLine();
        
        Student student = findStudentById(id);
        if (student != null) {
            System.out.println("\nStudent Found:");
            System.out.printf("ID: %s%n", student.getId());
            System.out.printf("Name: %s%n", student.getName());
            System.out.printf("Age: %d%n", student.getAge());
            System.out.printf("Grade: %s%n", student.getGrade());
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }
    
    private static void updateStudent() {
        System.out.println("\n=== Update Student ===");
        System.out.print("Enter Student ID to update: ");
        String id = scanner.nextLine();
        
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }
        
        System.out.println("Current details:");
        System.out.printf("Name: %s%n", student.getName());
        System.out.printf("Age: %d%n", student.getAge());
        System.out.printf("Grade: %s%n", student.getGrade());
        
        System.out.print("\nEnter new name (or press Enter to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            student.setName(newName);
        }
        
        System.out.print("Enter new age (or press Enter to keep current): ");
        String ageInput = scanner.nextLine();
        if (!ageInput.trim().isEmpty()) {
            try {
                int newAge = Integer.parseInt(ageInput);
                student.setAge(newAge);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age format. Age not updated.");
            }
        }
        
        System.out.print("Enter new grade (or press Enter to keep current): ");
        String newGrade = scanner.nextLine();
        if (!newGrade.trim().isEmpty()) {
            student.setGrade(newGrade);
        }
        
        System.out.println("Student updated successfully!");
    }
    
    private static void deleteStudent() {
        System.out.println("\n=== Delete Student ===");
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();
        
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }
        
        System.out.printf("Are you sure you want to delete student %s (%s)? (y/N): ", 
            student.getName(), student.getId());
        String confirmation = scanner.nextLine();
        
        if (confirmation.toLowerCase().equals("y") || confirmation.toLowerCase().equals("yes")) {
            students.remove(student);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    private static Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
    
    private static void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String grade = parts[3];
                    students.add(new Student(id, name, age, grade));
                }
            }
            System.out.println("Data loaded from file: " + students.size() + " students.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing data file found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error reading data file: " + e.getMessage());
        }
    }
    
    private static void saveStudentsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Student student : students) {
                writer.printf("%s,%s,%d,%s%n", 
                    student.getId(), student.getName(), student.getAge(), student.getGrade());
            }
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }
}

class Student {
    private String id;
    private String name;
    private int age;
    private String grade;
    
    public Student(String id, String name, int age, String grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGrade() { return grade; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setGrade(String grade) { this.grade = grade; }
}
