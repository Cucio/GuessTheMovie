import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Game {
    String movieName;
    String hiddenMovieName;
    String retainCh = "";
    boolean OK = true, isValidLetter = true, wasIntroduced = false;
    int wrongAttempts = 0;
    String wrongAttemptsArray = "";

    private static int getRandomNumber(int max) {
        Random r = new Random();
        return r.nextInt(max + 1);
    }

    void readTitle() {
        try {
            File file = new File("Fisier.txt");
            Scanner scanner = new Scanner(file);

            for (int i = 0; i < getRandomNumber(25); i++) {
                movieName = scanner.nextLine();
            }
            codeTitle();
        } catch (Exception e) {
            System.out.println("File Not Found");
        }
    }

    void codeTitle() {
        hiddenMovieName = movieName.replaceAll("[a-zA-Z]", "_");
    }

    boolean wasIntroduced(char c) {
        return retainCh.indexOf(c) > -1;
    }

    boolean isValidLetter(char c) {
        return (c < 'a' || c > 'z');
    }

    void verify(char c) {
        do {

            if (!wasIntroduced(c)) {
                retainCh += c;
                OK = true;
            } else {
                wasIntroduced = true;
                break;
            }

        } while (!OK);


        int index = movieName.indexOf(c);


        if (!isValidLetter(c) && wasIntroduced(c))
            if (index != -1)
                while (index >= 0) {
                    char[] numeFilmChar = hiddenMovieName.toCharArray();
                    numeFilmChar[index] = c;
                    hiddenMovieName = String.valueOf(numeFilmChar);
                    index = movieName.indexOf(c, index + 1);
                }
            else {
                wrongAttempts++;
                wrongAttemptsArray = wrongAttemptsArray + " " + c;
            }
    }

    boolean checkIfWon() {
        return hiddenMovieName.indexOf('_') == -1;
    }

    String getMovieName() {
        return hiddenMovieName;
    }

    boolean getValidLetter() {
        return isValidLetter;
    }

    boolean wasIntroduced() {
        return wasIntroduced;
    }

    int getWrongAttempts() {
        return wrongAttempts;
    }

    String getWrongAttemptsArray() {
        return "Numar greseli: " + wrongAttempts + " literele gresite: " + wrongAttemptsArray;
    }

    public void test() {
        readTitle();
        Scanner scanner = new Scanner(System.in);
        System.out.println(movieName);
        do {
            char c = scanner.next().charAt(0);
            verify(c);
            System.out.println(getWrongAttemptsArray());
            System.out.println(hiddenMovieName);
            if (checkIfWon())
                System.out.println("Congratulations!");
        } while (!checkIfWon());
    }

}
