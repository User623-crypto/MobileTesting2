package com.example.third;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player {
    private Sprite sprite;





    private GameView gameView;

    //Pozicioni
    private int posx;
    private int posy;

    //For Testing
    private int _jumppower=5;
    private int holding;
    private boolean finishjumping=false;




    Player(GameView gameView,int posx,int posy,Sprite sprite)
    {

        //Sets image
        this.sprite=sprite;
        this.posx=posx;
        this.posy=posy;

        //Sets where will it draw
        this.gameView=gameView;

        //For testing
        this.holding=posy;


    }

    /*FOR TESTING*/
    public void jump()
    {

        if(_jumppower<13 && !finishjumping)
        {
            posy-=5; _jumppower++;
        }else if ((_jumppower>=13 )&&posy<=holding)
        {posy+=3;
        finishjumping=true;
        }else {finishjumping=false;_jumppower=5;}
    }

    //updates the player values
    public void update()
    {

        if(posx<300)
            this.posx++;

        jump();

    }

    /*For Testing*/
    public void moveLeft()
    {


            this.posx--;

        jump();
    }

    //Draws the player at given position
    public void draw(Canvas canvas){
        //Funksioni pain perfaqson menyren si do vizatohet, i panevojshem ne kete rast;
        canvas.drawBitmap(this.sprite.getImage(),this.posx,this.posy,null);

    }




}
