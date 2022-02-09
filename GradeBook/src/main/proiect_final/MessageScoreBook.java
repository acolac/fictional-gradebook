package teme.proiect_final;

import java.util.List;

public class MessageScoreBook {
    private List<SCOREdto> scores;

    public MessageScoreBook(List<SCOREdto> scores) {
        this.scores = scores;
    }

    public void getOpenMessageScoreBook() {
        System.out.println("Welcome to ScoreBook, Select from Menu!");
    }

    public int getMinScore() {
        int lowerScore = scores.get(0).getGrade();
        for (SCOREdto scorePerStud : scores) {
            if (scorePerStud.getGrade() < lowerScore)
                lowerScore = scorePerStud.getGrade();
        }
        return lowerScore;
    }

    public int getMaxScore() {
        int highestScore = scores.get(0).getGrade();
        for (SCOREdto scorePerStud : scores) {
            if (scorePerStud.getGrade() > highestScore)
                highestScore = scorePerStud.getGrade();
        }
        return highestScore;
    }

    public double getAverageScore() {
        int total = 0;
        for (SCOREdto scorePerStud : scores) {
            total += scorePerStud.getGrade();
            total /= scores.size();
        }
        return (double) total;
    }

    public void getScoreProcess() {
        System.out.printf("\nClass score average is %.2f\n", getAverageScore());
        System.out.printf("Lowest score is %d\nHighest score is %d\n\n",
                getMinScore(), getMaxScore());
    }

    public void getOutputAll() {
        System.out.println("Students score: \n");
        for (int STUDENT = 0; STUDENT < scores.size(); STUDENT++)
            System.out.printf("Student %2d: %3d\n",
                    STUDENT + 1, scores.get(STUDENT));
    }


}
