/**
 * Student class for managing student records
 * Part of CLI-based CRUD system for student record management
 */
public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String course;
    private int enrollmentYear;
    private double gpa;
    
    // Default constructor
    public Student() {
    }
    
    // Parameterized constructor
    public Student(int studentId, String firstName, String lastName, String email, 
                   String phoneNumber, String course, int enrollmentYear, double gpa) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.course = course;
        this.enrollmentYear = enrollmentYear;
        this.gpa = gpa;
    }
    
    // Getter methods
    public int getStudentId() {
        return studentId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getCourse() {
        return course;
    }
    
    public int getEnrollmentYear() {
        return enrollmentYear;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    // Setter methods
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }
    
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    
    // toString method for displaying student information
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", course='" + course + '\'' +
                ", enrollmentYear=" + enrollmentYear +
                ", gpa=" + gpa +
                '}';
    }
    
    // equals method for comparing students
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return studentId == student.studentId;
    }
    
    // hashCode method
    @Override
    public int hashCode() {
        return Integer.hashCode(studentId);
    }
    
    // Method to get full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    // Method to validate student data
    public boolean isValidStudent() {
        return studentId > 0 && 
               firstName != null && !firstName.trim().isEmpty() &&
               lastName != null && !lastName.trim().isEmpty() &&
               email != null && email.contains("@") &&
               phoneNumber != null && !phoneNumber.trim().isEmpty() &&
               course != null && !course.trim().isEmpty() &&
               enrollmentYear > 1900 && enrollmentYear <= java.time.Year.now().getValue() &&
               gpa >= 0.0 && gpa <= 4.0;
    }
}
