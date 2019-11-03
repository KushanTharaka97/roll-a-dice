package com.example.roll_a_dice;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView result;

    //Button click;

    //initializing random class
    Random rand;

    //Roll dice
    int die1;
    int die2;
    int die3;

    //field to hold score
    int score;

    //field to hold score text
    TextView scoreText;


    //array to hold all three dices
     ArrayList<Integer> dice;

     //store Images
    ArrayList<ImageView> diceImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rolling(view);
            }
        });

            result=(TextView) findViewById(R.id.RollDice);
            //click=(Button)findViewById(R.id.click);
            scoreText=(TextView) findViewById(R.id.scoreText);

            rand=new Random();

            //create a ArraList container for dice value
            dice=new ArrayList<Integer>();

            ImageView die1Image=(ImageView) findViewById(R.id.die1Image);
            ImageView die2Image=(ImageView) findViewById(R.id.die2Image);
            ImageView die3Image=(ImageView) findViewById(R.id.die3Image);


            diceImageView=new ArrayList<ImageView>();
            diceImageView.add(die1Image);
            diceImageView.add(die2Image);
            diceImageView.add(die3Image);

    }

        //onClick method
        public void rolling(View v){
           // result.setText("Clicked!");

          int die1=rand.nextInt(6)+1;
            int die2=rand.nextInt(6)+1;
            int die3=rand.nextInt(6)+1;

         //set Values into the array list
            dice.clear();

            dice.add(die1);
            dice.add(die2);
            dice.add(die3);



            for(int dieOfSet=0; dieOfSet<3 ;dieOfSet++) {
                    String imageName ="die_"+dice.get(dieOfSet)+".png";
                    try{

                     InputStream stream=getAssets().open(imageName);
                     Drawable d=Drawable.createFromStream(stream,null);
                     diceImageView.get(dieOfSet).setImageDrawable(d);

                    }catch(IOException e){

                        e.printStackTrace();

                    }

            }

            String msg;
            if (die1 == die2 && die2 == die3) {
                //triplets
                int scoreDelta=die1*100;
                msg="You rolled a triplet"+die1+"! You score "+scoreDelta+"! points";
                score+=scoreDelta;

            }else if(die1 == die2 || die2==die3 ||  die1==die3){
                //double
                msg="You rolled doubles for 50 points";
                score+=50;
            }else{
                msg="You didn't score ,try again!";
            }

            result.setText(msg);
            scoreText.setText("score : "+score);

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
}
