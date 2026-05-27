import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO(sc);

        int choice;

        do {

            System.out.println("\n===== STUDENT RECORD MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student (By ID)");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Student Count");
            System.out.println("7. Search Student By Name");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    dao.addStudent();
                    break;

                case 2:
                    dao.viewStudents();
                    break;

                case 3:
                    dao.searchStudent();
                    break;

                case 4:
                    dao.updateStudent();
                    break;

                case 5:
                    dao.deleteStudent();
                    break;

                case 6:
                    dao.studentCount();
                    break;

                case 7:
                    dao.searchByName();
                    break;

                case 8:
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 8);

        sc.close();
    }
}