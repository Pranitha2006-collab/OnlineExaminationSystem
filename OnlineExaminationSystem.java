import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineExaminationSystem {

    static Scanner sc = new Scanner(System.in);

    static User user = new User("admin", "1234", "Student");

    static boolean loggedIn = false;
    static boolean timeUp = false;
    static int score = 0;

    static Question[] questions = {

            new Question(
                    "Capital of India?",
                    "Mumbai",
                    "Delhi",
                    "Chennai",
                    "Kolkata",
                    'B'),

            new Question(
                    "Java is a ?",
                    "Programming Language",
                    "Database",
                    "Browser",
                    "Operating System",
                    'A'),

            new Question(
                    "2 + 2 = ?",
                    "3",
                    "4",
                    "5",
                    "6",
                    'B')
    };

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== ONLINE EXAMINATION SYSTEM =====");
            System.out.println("1. Login");
            System.out.println("2. Update Profile");
            System.out.println("3. Change Password");
            System.out.println("4. Start Exam");
            System.out.println("5. Logout");
            System.out.println("6. Exit");

            System.out.print("Choose Option: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    login();
                    break;

                case 2:
                    updateProfile();
                    break;

                case 3:
                    changePassword();
                    break;

                case 4:
                    startExam();
                    break;

                case 5:
                    logout();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public static void login() {

        System.out.print("Enter Username: ");
        String username = sc.next();

        System.out.print("Enter Password: ");
        String password = sc.next();

        if (username.equals(user.getUsername())
                && password.equals(user.getPassword())) {

            loggedIn = true;
            System.out.println("Login Successful");

        } else {

            System.out.println("Invalid Username or Password");
        }
    }

    public static void updateProfile() {

        if (!loggedIn) {
            System.out.println("Please Login First");
            return;
        }

        System.out.println("Profile Updated Successfully");
    }

    public static void changePassword() {

        if (!loggedIn) {
            System.out.println("Please Login First");
            return;
        }

        System.out.print("Enter New Password: ");

        String newPassword = sc.next();

        user.setPassword(newPassword);

        System.out.println("Password Changed Successfully");
    }

    public static void startExam() {

        if (!loggedIn) {
            System.out.println("Please Login First");
            return;
        }

        score = 0;
        timeUp = false;

        System.out.println("\n===== EXAM STARTED =====");
        System.out.println("You have 60 seconds to complete the exam.");

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                timeUp = true;

                System.out.println("\n\nTIME UP!");
                System.out.println("Exam Auto Submitted.");

                Result result = new Result(score);
                result.displayResult();

                System.exit(0);
            }
        }, 60000);

        for (Question q : questions) {

            if (timeUp)
                break;

            System.out.println("\n" + q.question);

            System.out.println("A. " + q.optionA);
            System.out.println("B. " + q.optionB);
            System.out.println("C. " + q.optionC);
            System.out.println("D. " + q.optionD);

            System.out.print("Enter Answer: ");

            char answer = sc.next().toUpperCase().charAt(0);

            if (answer == q.correctAnswer) {
                score++;
            }
        }

        timer.cancel();

        Result result = new Result(score);
        result.displayResult();
    }

    public static void logout() {

        if (!loggedIn) {
            System.out.println("You are already logged out");
            return;
        }

        loggedIn = false;

        System.out.println("Logout Successful");
    }
}
