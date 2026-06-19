
public class Result {

    private int score;

    public Result(int score) {
        this.score = score;
    }

    public void displayResult() {

        System.out.println("\n===== RESULT =====");
        System.out.println("Your Score: " + score);

        if (score >= 2)
            System.out.println("Status: PASS");
        else
            System.out.println("Status: FAIL");
    }
}