package as2.com.rppack;

import java.util.Random;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class RPActivity extends Activity implements MyData {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rp__vh);

        Random r = new Random(System.currentTimeMillis());
        rand[NULL] = r.nextInt(MAX_GUESS);

        GameFragment game = new GameFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(android.R.id.content, game);
        ft.commit();
        
    }
}