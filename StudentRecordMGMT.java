import java.util.Scanner;

class Student{
    int studentID;
    String name;
    int age;
    String department;

    Student(int studentID, String name, int age, String department){
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    int getStudentID(){
        return studentID;
    }

    String getName(){
        return name;
    }

    int getAge(){
        return age;
    }

    String getDepartment(){
        return department;
    }

    @Override
    public String toString() {
        return "\nStudent ID = " + studentID + "\nStudent Name = " 
        + name + "\nStudent Age = " + age + "\nStudent Department = " + department;
    }
}

class StudentRecordSystem{
    Student[] students = new Student[100];
    int count=0;

    void addStudent(Student student){
        students[count] = student;
        count++;
    }

    void displayAllStudents(){
        for(int i = 0; i < count; i++){
            System.out.println(students[i]);
        }
    }

    Student getStudent(int studentID){
        for(int i = 0; i < count; i++){
            if (studentID == students[i].getStudentID()) {
                return students[i];
            }
        }
        return null;
    }
}

public class StudentRecordMGMT{
    public static void main(String[] args) {
        int ch;
        StudentRecordSystem srs = new StudentRecordSystem();
        Scanner scan = new Scanner(System.in);

        int id;
        String name;
        int age;
        String dept;

        do {
            System.out.println("\n1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Exit");
            System.out.print("Enter Your Choice : ");
            ch = scan.nextInt();
            scan.nextLine();

            switch (ch) {
                case 1:   
                    System.out.print("Enter Student ID : ");
                    id = scan.nextInt();
                    scan.nextLine();
        
                    System.out.print("Enter Student Name : ");
                    name = scan.nextLine();
                    
                    System.out.print("Enter Student Age : ");
                    age = scan.nextInt();
                    scan.nextLine();
                    
                    System.out.print("Enter Student Department : ");
                    dept = scan.nextLine();
                    
                    Student s = new Student(id, name, age, dept);
                    srs.addStudent(s);
                    break;
            
                case 2:
                    srs.displayAllStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID for search student : ");
                    id = scan.nextInt();
                    scan.nextLine();
                    Student std = srs.getStudent(id);
                    if(std != null) {
                        System.out.println("Student Found !!");
                        System.out.println(std);
                    }
                    else{
                        System.out.println("Student Not Found !!");
                    }
                default:
                    break;
            }
        } while (ch>=1 && ch<=4);
        
    }
}
