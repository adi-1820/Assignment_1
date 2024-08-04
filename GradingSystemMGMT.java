import java.util.Scanner;

class Student1 {
    int studentID;
    String name;

    Student1(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    int getStudentID() {
        return studentID;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name;
    }
}

class Grade {
    int studentID;
    int courseID;
    char grade;

    Grade(int studentID, int courseID, char grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    int getStudentID() {
        return studentID;
    }

    int getCourseID() {
        return courseID;
    }

    char getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "\nStudent ID: " + studentID + "\nCourse ID: " + courseID + "\nGrade: " + grade;
    }
}

class GradingSystem {
    Student1[] students;
    Grade[] grades;
    int[] courseCredits;
    int studentCount;
    int gradeCount;

    GradingSystem(int maxStudents, int maxCourses) {
        students = new Student1[maxStudents];
        grades = new Grade[maxStudents * maxCourses];
        courseCredits = new int[maxCourses];
        studentCount = 0;
        gradeCount = 0;
    }

    void addStudent(Student1 student) {
        if (studentCount < students.length) {
            students[studentCount++] = student;
        } else {
            System.out.println("Maximum number of students reached.");
        }
    }

    void addGrade(Grade grade) {
        if (gradeCount < grades.length) {
            grades[gradeCount++] = grade;
        } else {
            System.out.println("Maximum number of grades reached.");
        }
    }

    void addCourseCredits(int courseID, int credits) {
        if (courseID < courseCredits.length) {
            courseCredits[courseID] = credits;
        } else {
            System.out.println("Invalid course ID.");
        }
    }

    double calculateGPA(int studentID) {
        int totalPoints = 0;
        int totalCredits = 0;
        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentID() == studentID) {
                int courseID = grades[i].getCourseID();
                char grade = grades[i].getGrade();
                int points = gradeToPoints(grade);
                int credits = courseCredits[courseID];
                totalPoints += points * credits;
                totalCredits += credits;
            }
        }
        if (totalCredits == 0)
            return 0.0;
        return (double) totalPoints / totalCredits;
    }

    int gradeToPoints(char grade) {
        switch (grade) {
            case 'A':
                return 10;
            case 'B':
                return 9;
            case 'C':
                return 8;
            case 'D':
                return 7;
            case 'F':
                return 0;
            default:
                return 0;
        }
    }

    void printGradeReport(int studentID) {
        System.out.println("Grade Report for Student ID: " + studentID);
        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentID() == studentID) {
                System.out.println(grades[i]);
            }
        }
        System.out.println("GPA: " + calculateGPA(studentID));
    }
}

public class GradingSystemMGMT {
    public static void main(String[] args) {
        int studentID,courseID;
        String name;
        int ch;
        int credits;
        char grade;
        double gpa;

        Scanner scan = new Scanner(System.in);
        GradingSystem system = new GradingSystem(100, 10);
        
        
        do {

            System.out.println("\n1. Add new student");
            System.out.println("2. Add grade for student");
            System.out.println("3. Add course credits");
            System.out.println("4. Calculate GPA");
            System.out.println("5. Print grade report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            ch = scan.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    studentID = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter Name: ");
                    name = scan.nextLine();
                    system.addStudent(new Student1(studentID, name));
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    studentID = scan.nextInt();
                    System.out.print("Enter Course ID: ");
                    courseID = scan.nextInt();
                    System.out.print("Enter Grade (A/B/C/D/F): ");
                    grade = scan.next().charAt(0);
                    system.addGrade(new Grade(studentID, courseID, grade));
                    break;
                case 3:
                    System.out.print("Enter Course ID: ");
                    courseID = scan.nextInt();
                    System.out.print("Enter Credits: ");
                    credits = scan.nextInt();
                    system.addCourseCredits(courseID, credits);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scan.nextInt();
                    gpa = system.calculateGPA(studentID);
                    System.out.println("GPA for Student ID " + studentID + ": " + gpa);
                    break;
                case 5:
                    System.out.print("Enter Student ID: ");
                    studentID = scan.nextInt();
                    system.printGradeReport(studentID);
                    break;
                case 6:
                    System.out.println("Exited.....");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (ch >= 1 && ch <= 6);
    }
}