package com.example.koktoh.a_go_calc_for_android;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends Activity implements View.OnClickListener {

    // ボタン
    private Button attackButton;
    private Button bossButton;
    private Button bossWinButton;
    private Button winButton;

    // 進行度テキスト
    private TextView attackTextView;
    private TextView bossTextView;
    private TextView bossWinTextView;
    private TextView winTextView;
    private TextView allTextView;

    // 進行数テキスト
    private TextView attackNumTextView;
    private TextView bossNumTextView;
    private TextView bossWinNumTextView;
    private TextView winNumTextView;

    // 進行度プログレスバー
    private ProgressBar attackProgressBar;
    private ProgressBar bossProgressBar;
    private ProgressBar bossWinProgressBar;
    private ProgressBar winProgressBar;
    private ProgressBar allProgressBar;

    // 終了数
    private final double attackF = 36;
    private final double bossF = 24;
    private final double bossWinF = 12;
    private final double winF = 6;

    // 現在の進行数
    private int attack = 0;
    private int boss = 0;
    private int bossWin = 0;
    private int win = 0;

    // 計算結果入れ
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT < 21) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.relariveLayout);

        }

        // ボタン
        attackButton = (Button) findViewById(R.id.attackButton);
        bossButton = (Button) findViewById(R.id.bossButton);
        bossWinButton = (Button) findViewById(R.id.bossWinButton);
        winButton = (Button) findViewById(R.id.winButton);

        // 進行度テキスト
        attackTextView = (TextView) findViewById(R.id.attackTextView);
        bossTextView = (TextView) findViewById(R.id.bossTextView);
        bossWinTextView = (TextView) findViewById(R.id.bossWinTextView);
        winTextView = (TextView) findViewById(R.id.winTextView);
        allTextView = (TextView) findViewById(R.id.allTextView);

        // 進行数テキスト
        attackNumTextView = (TextView) findViewById(R.id.attackNumTextView);
        bossNumTextView = (TextView) findViewById(R.id.bossNumTextView);
        bossWinNumTextView = (TextView) findViewById(R.id.bossWinNumTextView);
        winNumTextView = (TextView) findViewById(R.id.winNumTextView);

        // 進行度プログレスバー
        attackProgressBar = (ProgressBar) findViewById(R.id.attackProgressBar);
        bossProgressBar = (ProgressBar) findViewById(R.id.bossProgressBar);
        bossWinProgressBar = (ProgressBar) findViewById(R.id.bossWinProgressBar);
        winProgressBar = (ProgressBar) findViewById(R.id.winProgressBar);
        allProgressBar = (ProgressBar) findViewById(R.id.allProgressBar);

        attackButton.setOnClickListener(this);
        bossButton.setOnClickListener(this);
        bossWinButton.setOnClickListener(this);
        winButton.setOnClickListener(this);

        attackProgressBar.setMax(100);
        bossProgressBar.setMax(100);
        bossWinProgressBar.setMax(100);
        winProgressBar.setMax(100);
        allProgressBar.setMax(100);

        checkPref();
    }

    @Override
    protected void onPause() {
        Calendar cal = Calendar.getInstance();

        SharedPreferences pref = getSharedPreferences("AGoClacPref", MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        e.putInt("attack", attack);
        e.putInt("boss", boss);
        e.putInt("bossWin", bossWin);
        e.putInt("win", win);

        e.putInt("year", cal.get(Calendar.YEAR));
        e.putInt("month", cal.get(Calendar.MONTH));
        e.putInt("day", cal.get(Calendar.DAY_OF_MONTH));
        e.putInt("hour", cal.get(Calendar.HOUR_OF_DAY));
        e.putInt("min", cal.get(Calendar.MINUTE));
        e.putInt("sec", cal.get(Calendar.SECOND));
        e.putInt("date", cal.get(Calendar.DAY_OF_WEEK));

        e.commit();

        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == null) return;

        switch (v.getId()) {
            case R.id.attackButton:
                attackButtonClicked();
                break;
            case R.id.bossButton:
                bossButtonClicked();
                break;
            case R.id.bossWinButton:
                bossWinButtonClicked();
                break;
            case R.id.winButton:
                winButtonClicked();
                break;
            default:
                ;
        }

        result = (int) (((attack < attackF) ? attack : attackF / attackF * 25) + ((boss < bossF) ? boss : bossF / bossF * 25) + ((bossWin < bossWinF) ? bossWin : bossWinF / bossWinF * 25) + ((win < winF) ? win : winF / winF * 25));
        allProgressBar.setProgress(result);
        allTextView.setText(String.valueOf(result) + "%");
    }

    private void attackButtonClicked() {
        attack++;

        result = (int) ((attack < attackF) ? attack : attackF / attackF * 100);

        attackNumTextView.setText(String.valueOf(attack));
        attackProgressBar.setProgress(result);
        attackTextView.setText(String.valueOf(result) + "%");
    }

    private void bossButtonClicked() {
        boss++;

        result = (int) ((boss < bossF) ? boss : bossF / bossF * 100);

        bossNumTextView.setText(String.valueOf(boss));
        bossProgressBar.setProgress(result);
        bossTextView.setText(String.valueOf(result) + "%");
    }

    private void bossWinButtonClicked() {
        bossWin++;

        result = (int) ((bossWin < bossWinF) ? bossWin : bossWinF / bossWinF * 100);

        bossWinNumTextView.setText(String.valueOf(bossWin));
        bossWinProgressBar.setProgress(result);
        bossWinTextView.setText(String.valueOf(result) + "%");
    }

    private void winButtonClicked() {
        win++;

        result = (int) ((win < winF) ? win : winF / winF * 100);

        winNumTextView.setText(String.valueOf(win));
        winProgressBar.setProgress(result);
        winTextView.setText(String.valueOf(result) + "%");
    }

    private void checkPref() {
        if(checkDate()) {
            SharedPreferences pref = getSharedPreferences("AGoCalcPref", MODE_PRIVATE);
            attack = pref.getInt("attack", 0);
            boss = pref.getInt("boss", 0);
            bossWin = pref.getInt("bossWin", 0);
            win = pref.getInt("win", 0);
        }
    }

    private boolean checkDate(){
        Calendar carrentCal = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        SharedPreferences pref = getSharedPreferences("AGoCalcPref", MODE_PRIVATE);
        cal.set(Calendar.YEAR, pref.getInt("year", carrentCal.get(Calendar.YEAR)));
        cal.set(Calendar.MONTH, pref.getInt("month", carrentCal.get(Calendar.MONTH)));
        cal.set(Calendar.DAY_OF_MONTH, pref.getInt("day", carrentCal.get(Calendar.DAY_OF_MONTH)));
        cal.set(Calendar.HOUR_OF_DAY, pref.getInt("hour", carrentCal.get(Calendar.HOUR_OF_DAY)));
        cal.set(Calendar.MINUTE, pref.getInt("min", carrentCal.get(Calendar.MINUTE)));
        cal.set(Calendar.SECOND, pref.getInt("sec", carrentCal.get(Calendar.SECOND)));
        cal.set(Calendar.DAY_OF_WEEK, pref.getInt("date", carrentCal.get(Calendar.DAY_OF_WEEK)));

        if(cal == carrentCal)
            return false;

        int dow = carrentCal.get(Calendar.DAY_OF_WEEK);
        if(dow == 1) dow = 8;
        carrentCal.add(Calendar.DAY_OF_MONTH, (2 - dow));
        if(carrentCal.get(Calendar.HOUR_OF_DAY) < 5) carrentCal.add(Calendar.DAY_OF_MONTH, -7);
        carrentCal.set(Calendar.HOUR_OF_DAY, 5);
        carrentCal.set(Calendar.MINUTE, 0);
        carrentCal.set(Calendar.SECOND, 0);

        if(cal.compareTo(carrentCal) < 0) return false;

        return true;
    }
}
