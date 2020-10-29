package com.shabbir.mathgame;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

/**
 * Created by Shabbir Ali on 27/10/2020.
 */

public class WinActivity extends Activity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        tv = (TextView) findViewById(R.id.congo);
        Bundle b = getIntent().getExtras();
        int y = b.getInt("score");
        tv.setText("FINAL SCORE:" + y);
    }

    public void playagain(View o) {

        Intent intent = new Intent(this, MainQuestionActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Close Math Game?")
                .setMessage("Are you sure you want to close the game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
