package com.cs175.chenyulong.memorygame;

import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity implements GridView.OnItemClickListener {

    private boolean flag = false;
    View cellView = null;
    private int first = -1;
    private TextView pointView;
    private GridView gradView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gradView = (GridView)findViewById(R.id.gridView);

        if (!Singleton.getInstance().getProgress()) {
            Singleton.getInstance().startGame();
            startNewgame();
        } else {
            Singleton.getInstance().addBlank();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
             builder.setMessage("Restart the game?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Singleton.getInstance().startGame();
                            startNewgame();
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    })
             .show();

        }

        pointView = (TextView)findViewById(R.id.pointView);



        gradView.setAdapter(new ImageAdapter(this, Singleton.getInstance().getImageArr(), Singleton.getInstance().getFinishedArr()));
        gradView.setOnItemClickListener(this);
        pointView.setText(""+Singleton.getInstance().getPoints());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("Saved", "Called instance state");
        super.onSaveInstanceState(outState);

    }

    protected void startNewgame() {
        gradView.setAdapter(new ImageAdapter(this, Singleton.getInstance().getImageArr(), Singleton.getInstance().getFinishedArr()));
    }
    @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long d) {

            if (Singleton.getInstance().checkFinished(position) == 0) {
                final View tempView = v;
                final int tempPos = position;
                if (!flag) {
                    first = position;
                    cellView = v;
                    ImageView firstImage = (ImageView) v.findViewById(R.id.hideImage);
                    firstImage.setVisibility(View.INVISIBLE);
                    flag = true;
                } else {
                    if (position != first) {
                        ImageView firstImage = (ImageView) v.findViewById(R.id.hideImage);
                        firstImage.setVisibility(View.INVISIBLE);

                        if (Singleton.getInstance().checkPositions(first, tempPos)) {
                            Singleton.getInstance().addFinished(first, position);
                        }

                        new CountDownTimer(1000, 100) {

                            public void onTick(long millisUntilFinished) {

                            }

                            public void onFinish() {
                                if (!Singleton.getInstance().checkPositions(first, tempPos)) {
                                    cellView.findViewById(R.id.hideImage).setVisibility(View.VISIBLE);
                                    tempView.findViewById(R.id.hideImage).setVisibility(View.VISIBLE);
                                } else {
                                    cellView.findViewById(R.id.realImage).setVisibility(View.INVISIBLE);
                                    tempView.findViewById(R.id.realImage).setVisibility(View.INVISIBLE);
                                    Singleton.getInstance().addPoints();
                                    pointView.setText(""+Singleton.getInstance().getPoints());
                                }
                            }
                        }.start();

                        flag = false;

                    }
                }
            }






    }

}
