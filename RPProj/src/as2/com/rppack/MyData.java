package as2.com.rppack;

import java.util.ArrayList;

public interface MyData {
    int MAX_INVALID = 3;
    int MAX_VALID = 5;
    int MAX_GUESS = 100;
    int MIN_GUESS = 0;
    int NULL = 0;
    int NOTIFY_ID_LOST = 1;
    int NOTIFY_ID_WON = 2;
    int NOTIFY_ID_GL = 3;
    boolean gameEnded[] = new boolean[1];
    boolean win[] = new boolean[1];
    int rand[] = new int[1];
    ArrayList<Integer> valid = new ArrayList<Integer>(MAX_VALID);
    ArrayList<Integer> invalid = new ArrayList<Integer>(MAX_INVALID);
    String BLANK = "";
    String TIME = "Time: ";
    String DATE = "Date: ";
    String COMMA = ", ";
    String YES = "YES";
    String NO = "NO";
    String CONFIRMATION = "Confirmation";
    String TIME_FORMAT = "hh:mm:ss";
    String DATE_FORMAT = "dd-MMM-yyyy";
    String MSG_GAME_ENDED = "The game has ended...";
    String MSG_INVALID_EXCEEDED = "Invalid number of trials exceeded! You have lost!";
    String MSG_INVALID_GUESS = "Invalid guess!";
    String MSG_GUESS_FIELD_EMPTY = "The guess field is empty!";
    String MSG_INVALID_NUM = "Number of invalid trials: ";
    String MSG_VALID_NUM = "Number of valid trials: ";
    String MSG_EXIT_CONFIRM = "Do you really want to exit?";
    String NOTIFY_WON = "You are the winner";
    String NOTIFY_LOST = "The PC is the winner";
    String NOTIFY_GL = "Bonne chance";
    String APP_NAME = "RPActivity";

}
