package com.example.tictoktoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 0 = X
    // 1 = O
    boolean gameActive = true;
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    // State meaning
   // 0 = X
    // 1 = O
    //2 = Null

    int [][] winPositions = {{0,1,2},{3,4,5},{6,7,8},
                             {0,3,6},{1,4,7},{6,7,8},
                              {0,4,8},{2,4,6}};

    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive)
            gameReset(view);
        if(gameState[tappedImage]== 2){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0){
                img.setImageResource(R.drawable.x2);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer =0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // check if player has won!
        for(int[] winpostion: winPositions){
            if(gameState[winpostion[0]]==gameState[winpostion[1]] &&
                    gameState[winpostion[1]]==gameState[winpostion[2]] &&
                     gameState[winpostion[0]]!=2){
                // somebody has won - find who
                String winingStr;
                gameActive = false;
                if (gameState[winpostion[0]]==0) {
                    winingStr = " X has won";
                }
                else{
                    winingStr = " O has won";
                }
               // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winingStr);
            }
        }
    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i=0 ;i<gameState.length; i++)
            gameState[i]=2;
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}