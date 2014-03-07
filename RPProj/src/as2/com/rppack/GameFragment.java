package as2.com.rppack;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class GameFragment extends Fragment implements MyData {



    public void onStart() {
        super.onStart();
        Activity thisActivity = getActivity();
        Button ok = (Button) thisActivity.findViewById(R.id.btnOk);
        Button clear = (Button) thisActivity.findViewById(R.id.btnClear);
        Button exit = (Button) thisActivity.findViewById(R.id.btnExit);
        Button newGame = (Button) thisActivity.findViewById(R.id.btnStartGame);
        Button showDetails = (Button) thisActivity.findViewById(R.id.btnShowDetails);

        ok.setOnClickListener(new clsOnClickListener());
        clear.setOnClickListener(new clsOnClickListener());
        exit.setOnClickListener(new clsOnClickListener());
        newGame.setOnClickListener(new clsOnClickListener());
        showDetails.setOnClickListener(new clsOnClickListener());

        if (gameEnded[NULL]) {
            ok.setVisibility(View.INVISIBLE);
            EditText msg = (EditText) thisActivity.findViewById(R.id.etMessage);
            msg.setText(MSG_GAME_ENDED);
        }

        EditText numVal = (EditText) thisActivity.findViewById(R.id.etNumValid);
        EditText numInv = (EditText) thisActivity.findViewById(R.id.etNumInv);
        numVal.setText(Integer.toString(valid.size()));
        numInv.setText(Integer.toString(invalid.size()));

        // show current date
        TextView date = (TextView) thisActivity.findViewById(R.id.txtDate);
        String formattedDate = new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime());
        date.setText(DATE + formattedDate);

        TextView time = (TextView) thisActivity.findViewById(R.id.txtTime);
        updateTime(time);
    }

    public void updateTime(TextView time) {
        String formattedTime = new SimpleDateFormat(TIME_FORMAT).format(Calendar.getInstance().getTime());
        time.setText(TIME + formattedTime);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    public boolean isValid(int num) {
        return (num >= MIN_GUESS && num <= MAX_GUESS);
    }

    public void clearGuessEt() {
        EditText guess = (EditText) getActivity().findViewById(R.id.etGuess);
        guess.setText(BLANK);
    }

    public void clearGameData() {
        valid.clear();
        invalid.clear();
        gameEnded[NULL] = false;
        win[NULL] = false;
    }

    public class clsOnClickListener implements View.OnClickListener {

        GameDetailsFragment gameInfoFrag = new GameDetailsFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        TextView time = (TextView) getActivity().findViewById(R.id.txtTime);


        public void onClick(View v) {

            updateTime(time);

            if (v.getId() == R.id.btnShowDetails) {
                ft.replace(android.R.id.content, gameInfoFrag);
                ft.commit();
            } else if (v.getId() == R.id.btnOk) {
                Activity thisActivity = getActivity();
                EditText guess = (EditText) thisActivity.findViewById(R.id.etGuess);
                EditText msg = (EditText) thisActivity.findViewById(R.id.etMessage);

                if (guess.getText().toString().trim().length() > NULL) {
                    int num = Integer.parseInt(guess.getText().toString());

                    if (isValid(num)) {
                        valid.add(num);
                        EditText numVal = (EditText) thisActivity.findViewById(R.id.etNumValid);
                        numVal.setText(Integer.toString(valid.size()));

                        if (num == rand[NULL] || valid.size() >= MAX_VALID) {
                            v.setVisibility(View.INVISIBLE);
                            gameEnded[NULL] = true;
                            if (num == rand[NULL]) {
                                win[NULL] = true;
                                ft.replace(android.R.id.content, gameInfoFrag);
                                ft.commit();
                            }

                        }
                    } else {
                        invalid.add(num);
                        EditText numInv = (EditText) thisActivity.findViewById(R.id.etNumInv);
                        numInv.setText(Integer.toString(invalid.size()));
                        if (invalid.size() >= MAX_INVALID) {
                            v.setVisibility(View.INVISIBLE);
                            gameEnded[NULL] = true;
                            msg.setText(MSG_INVALID_EXCEEDED);
                        } else {
                            msg.setText(MSG_INVALID_GUESS);
                        }
                    }
                } else
                    msg.setText(MSG_GUESS_FIELD_EMPTY);
                clearGuessEt();
            } else if (v.getId() == R.id.btnClear) {
                clearGuessEt();
            } else if (v.getId() == R.id.btnExit) {

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle(CONFIRMATION);
                alertDialog.setMessage(MSG_EXIT_CONFIRM);

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, YES,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                clearGameData();
                                getActivity().finish();
                            }
                        });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, NO,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

                alertDialog.show();

            } else if (v.getId() == R.id.btnStartGame) {
                clearGameData();
                Random r = new Random(System.currentTimeMillis());
                rand[NULL] = r.nextInt(MAX_GUESS);
                GameFragment game = new GameFragment();
                ft.replace(android.R.id.content, game);
                ft.commit();
            }
        }
    }
}

