package com.example.miglezlor.kedisesimon;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer sndblue,sndred,sndgreen,sndyellow;
    Button btnGreen, btnYellow, btnRed, btnBlue, start;


    TimerTask timerTarea;
    Timer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sndblue = MediaPlayer.create(this, R.raw.sonidoblue);
        sndred = MediaPlayer.create(this, R.raw.sonidored);
        sndgreen = MediaPlayer.create(this, R.raw.sonidogreen);
        sndyellow = MediaPlayer.create(this, R.raw.sonidoyellow);

        start = (Button) findViewById(R.id.empezar);
        btnBlue = (Button) findViewById(R.id.blueB);
        btnRed = (Button) findViewById(R.id.redB);
        btnGreen = (Button) findViewById(R.id.greenB);
        btnYellow = (Button) findViewById(R.id.yellowB);

    }
    ArrayList userColors = new ArrayList();
    ArrayList simonColors = new ArrayList();

    protected static int cont=0;

    void start(View j){

        cont=0;
        startTimer();


    }

    //evento para cuando el usuario pulsa el boton azul
    void blueEvent(View a){


        userColors.add(0);
        btnBlue.setBackgroundColor(Color.BLUE);
        final long changeTime = 200L;
        btnBlue.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnBlue.setBackgroundColor(Color.parseColor("#136CF1"));
                sndblue.start();
            }
        }, changeTime);
        cont++;
        check();

    }

    //evento para cuando el usuario pulsa el boton rojo
    void redEvent(View r){


        userColors.add(1);
        btnRed.setBackgroundColor(Color.RED);
        final long changeTime = 200L;
        btnRed.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnRed.setBackgroundColor(Color.parseColor("#CD3813"));
                sndred.start();
            }
        }, changeTime);
        cont++;
        check();

    }

    //evento para cuando el usuario pulsa el boton verde
    void greenEvent(View ve){


        userColors.add(2);
        btnGreen.setBackgroundColor(Color.GREEN);

        final long changeTime = 200L;
        btnGreen.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnGreen.setBackgroundColor(Color.parseColor("#1EA307"));
                sndgreen.start();
            }
        }, changeTime);
        cont++;
        check();

    }

    //evento para cuando el usuario pulsa el boton amarillo
    void yellowEvent(View a){


        userColors.add(3);
        btnYellow.setBackgroundColor(Color.YELLOW);
        final long changeTime = 200L;
        btnYellow.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnYellow.setBackgroundColor(Color.parseColor("#D4E113"));
                sndyellow.start();
            }
        }, changeTime);

        cont++;
        check();

    }



    //metodos para comenzar y parar el timer

    public void startTimer(){
        timer = new Timer();
        simonDecides();
        timer.schedule(timerTarea, 0, 1000);
    }
    public void stopTimer(){
        if (timer !=null){
            timer.cancel();
            timer= null;
        }
    }

    //metodo en el cual Simon dira sus opciones.
    void simonDecides(){
        timerTarea = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int numero= random();
                        simonColors.add(numero);

                        if (numero == 0) {
                            btnBlue.setBackgroundColor(Color.BLUE);

                            btnBlue.postDelayed(new Runnable() {
                                public void run() {
                                    btnBlue.setBackgroundColor(Color.parseColor("#136CF1"));
                                    sndblue.start();
                                }
                            }, 500);

                        }

                        if (numero == 1) {
                            btnRed.setBackgroundColor(Color.RED);

                            btnRed.postDelayed(new Runnable() {
                                public void run() {
                                    btnRed.setBackgroundColor(Color.parseColor("#CD3813"));
                                    sndred.start();
                                }
                            }, 500);


                        }
                        if (numero == 2) {
                            btnGreen.setBackgroundColor(Color.GREEN);

                            btnGreen.postDelayed(new Runnable() {
                                public void run() {
                                    btnGreen.setBackgroundColor(Color.parseColor("#1EA307"));
                                    sndgreen.start();
                                }
                            }, 500);

                        }
                        if (numero == 3) {
                            btnYellow.setBackgroundColor(Color.YELLOW);

                            btnYellow.postDelayed(new Runnable() {
                                public void run() {
                                    btnYellow.setBackgroundColor(Color.parseColor("#D4E113"));
                                    sndyellow.start();
                                }
                            }, 500);


                        }
                        cont++;
                        if(cont==4){
                            stopTimer();
                            cont=0;
                        }

                    }
                });

            }
        };


    }
    //metodo para comprobar si las elecciones del usuario coinciden con las elegidas por la maquina
    void check(){
        if(cont==4) {
            String simonElect = simonColors.toString();
            String userElect = userColors.toString();

            if (simonElect.equalsIgnoreCase(userElect)) {
                start.setText("JUEGA DE NUEVO");
                Toast.makeText(getApplicationContext(), "GG WP VICTORIA", Toast.LENGTH_SHORT).show();

            } else {
                start.setText("REINTENTAR");
                Toast.makeText(getApplicationContext(), "OMG REPORT THIS NOOB", Toast.LENGTH_SHORT).show();
            }
            simonColors.clear();
            userColors.clear();
        }
    }

    //metodo para generar un numero random el cual corresponde a un color
    public int random(){

       return (int) Math.floor(Math.random()*4);
    }
}
