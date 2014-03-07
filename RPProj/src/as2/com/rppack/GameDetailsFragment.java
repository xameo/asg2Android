package as2.com.rppack;

import java.util.ArrayList;

import android.app.*;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GameDetailsFragment extends Fragment implements MyData {

	public void onStart() {
		super.onStart();
        Activity thisActivity = getActivity();
		Button toGame = (Button) thisActivity.findViewById(R.id.btnBack);
		Button notify = (Button) thisActivity.findViewById(R.id.btnGetNotify);
		toGame.setOnClickListener(new clsOnClickListener());
		notify.setOnClickListener(new clsOnClickListener());

		TextView numInv = (TextView) thisActivity.findViewById(R.id.txtNumInv);
		TextView numVal = (TextView) thisActivity.findViewById(R.id.txtNumVal);
		TextView random = (TextView) thisActivity.findViewById(R.id.txtRand);
		numInv.setText( MSG_INVALID_NUM
				+ Integer.toString(invalid.size()));
		numVal.setText( MSG_VALID_NUM
				+ Integer.toString(valid.size()));
		random.setText(Integer.toString(rand[NULL]));

		TextView inv = (TextView) thisActivity.findViewById(R.id.txtInv);
		TextView val = (TextView) thisActivity.findViewById(R.id.txtVal);
		printArrayList(inv, invalid);
		printArrayList(val, valid);
	}

	public void printArrayList(TextView tv, ArrayList<Integer> arr) {
		String str = BLANK;
		for (int i = 0; i < arr.size(); i++) {
			if (i == NULL)
				str += Integer.toString(arr.get(i));
			else
				str += COMMA + Integer.toString(arr.get(i));
		}

		tv.setText(str);

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_game_details, container,
				false);
	}

	public class clsOnClickListener implements View.OnClickListener {

		public void notifyWinner(String message, int id) {
			NotificationManager nm = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

			Notification n1 = new Notification(R.drawable.ic_launcher, message, System.currentTimeMillis());

			n1.setLatestEventInfo(getActivity().getApplicationContext(), APP_NAME, message, null);
			nm.notify(id, n1);

		}

		GameFragment gameFrag = new GameFragment();
		FragmentTransaction ft = getFragmentManager().beginTransaction();

		public void onClick(View v) {
			if (v.getId() == R.id.btnBack) {
				ft.replace(android.R.id.content, gameFrag);
				ft.commit();
			} else if (v.getId() == R.id.btnGetNotify) {
				
				if (valid.size() >= MAX_VALID || invalid.size() >= MAX_INVALID) {
					notifyWinner(NOTIFY_LOST, NOTIFY_ID_LOST);
				} else if (win[NULL]){
					notifyWinner(NOTIFY_WON, NOTIFY_ID_WON);
				} else {
					notifyWinner(NOTIFY_GL, NOTIFY_ID_GL);
				}
				
			
			}

		}
	}

}
