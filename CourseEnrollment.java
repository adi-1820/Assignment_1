import java.util.Scanner;

class Course{
    int courseID;
    String courseName;
    int credits;

    Course(int courseID, String courseName, int credits){
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }

    int getcourseID(){
        return courseID;
    }

    String courseName(){
        return courseName;
    }

    int getCredits(){
        return credits;
    }

    @Override
    public String toString() {
        return "\nCourse ID = " + courseID + "\nCourse Name = " + courseName 
        + "\nCourse Credits = " + credits;
    }
    
}

class Enrollment{
    int[] count; // Courses per student
    int[][] studentCourses; // Student Id, Courses

    public Enrollment(int numberOfStudents, int maxCoursesPerStudent) {
        studentCourses = new int[numberOfStudents][maxCoursesPerStudent];
        count = new int[numberOfStudents];
    }

    void enroll(int studentID, int courseID){
       if(count[studentID] < studentCourses[studentID].length){
            studentCourses[studentID][count[studentID]++] = courseID;
            System.out.println("Student " + studentID + " Enrolled in course " + courseID);
        }
        else {
            System.out.println("Can't Enroll. Maximum courses Enrolled for student " + studentID);
        }
    }

    void drop(int studentID, int courseID){
        int flag = 0;

        for(int i=0; i<count[studentID]; i++){
            if(studentCourses[studentID][i] == courseID){
                for(int j=i; j<count[studentID]-1; j++){
                    studentCourses[studentID][j] = studentCourses[studentID][j+1];
                }
                count[studentID]--;
                flag = 1;
                System.out.println("Student " + studentID + " dropped course " + courseID);
            }
        }
        if(flag == 0){
            System.out.println("Student " + studentID + " is not enrolled in course " + courseID);
        }
    }

    void getEnrolledCourse(int studentID, Course[] courseCatalog){
        System.out.println("Course for Student " + studentID);

        for (int i = 0; i < count[studentID]; i++) {
            int courseID = studentCourses[studentID][i];
            for (Course c : courseCatalog) {
                if(c.getcourseID() == courseID){
                    System.out.println(c);
                }
            }
        }
    }
}


public class CourseEnrollment {
    
    Course[] courseCatalog;
    Enrollment enrollment;
    
    CourseEnrollment(){
        courseCatalog = new Course[]{
            new Course(251, "JAVA", 4),
            new Course(261, "DSA", 5),
            new Course(271,"COA",4)
        };
        enrollment = new Enrollment(100, 3);
    }
    public static void main(String[] args) {
        CourseEnrollment ce = new CourseEnrollment();
        Scanner scan = new Scanner(System.in);
        
        int ch;
        int studentID;
        int courseID;

        do {
            System.out.println("\n1. Enroll Student in Course");
            System.out.println("2. Drop Course");
            System.out.println("3. View Student Enrollments");
            System.out.println("4. Exit");
            System.out.print("Enter Your Choice : ");
            ch = scan.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter Student ID : ");
                    studentID = scan.nextInt();
                    System.out.print("Enter Course ID : ");
                    courseID = scan.nextInt();
                    ce.enrollment.enroll(studentID, courseID);
                    break;
            
                case 2:
                    System.out.print("Enter Student ID : ");
                    studentID = scan.nextInt();
                    System.out.print("Enter Course ID : ");
                    courseID = scan.nextInt();
                    ce.enrollment.drop(studentID, courseID);
                    break;

                case 3: 
                    System.out.print("Enter Student ID : ");
                    studentID = scan.nextInt();
                    ce.enrollment.getEnrolledCourse(studentID, ce.courseCatalog);
                    break;

                case 4:
                    System.out.println("Exited......");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice......");
                    break;
            }
        } while (ch>=1 && ch<=4);
    }    
}
