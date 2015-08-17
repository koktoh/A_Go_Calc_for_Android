package com.example.koktoh.a_go_calc_for_android;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.security.acl.AclEntry;

public class SettingActivity extends Activity {

    NumberPicker attackNumberPicker;
    NumberPicker bossNumberPicker;
    NumberPicker bossWinNumberPicker;
    NumberPicker winNumberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        attackNumberPicker = (NumberPicker)findViewById(R.id.attackNumberPicker);
        bossNumberPicker = (NumberPicker)findViewById(R.id.bossNumberPicker);
        bossWinNumberPicker = (NumberPicker)findViewById(R.id.bossWinNumberPicker);
        winNumberPicker = (NumberPicker)findViewById(R.id.winNumberPicker);

        attackNumberPicker.setMinValue(0);
        bossNumberPicker.setMinValue(0);
        bossWinNumberPicker.setMinValue(0);
        winNumberPicker.setMinValue(0);

        attackNumberPicker.setMaxValue(100);
        bossNumberPicker.setMaxValue(100);
        bossWinNumberPicker.setMaxValue(100);
        winNumberPicker.setMaxValue(100);

        Button button = (Button)findViewById(R.id.okButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();

                i.putExtra("attack", attackNumberPicker.getValue());
                i.putExtra("boss", bossNumberPicker.getValue());
                i.putExtra("bossWin", bossWinNumberPicker.getValue());
                i.putExtra("win", winNumberPicker.getValue());

                setResult(0, i);

                finish();
            }
        });

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        attackNumberPicker.setValue(bundle.getInt("attack"));
        bossNumberPicker.setValue(bundle.getInt("boss"));
        bossWinNumberPicker.setValue(bundle.getInt("bossWin"));
        winNumberPicker.setValue(bundle.getInt("win"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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
}
