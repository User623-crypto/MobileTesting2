package com.example.third;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Map {


    private Sprite sprite;
    private GameView gameView;
    private int width;
    private int height;
    Map(Sprite sprite,GameView gameView)
    {
        //Sets the image that will draw
        this.sprite=sprite;

        //Sets where it will draw
        this.gameView=gameView;
        this.width=gameView.getWidth();
        height=sprite.get_height();
    }

    public void draw(Canvas canvas){
        //it will draw the image repeating itself till it reaches the end of screen
        for(int i=0;i<=width;i=i+this.sprite.get_width()) {

            //Paint parameter affect the style of drawing, not important
            canvas.drawBitmap(this.sprite.getImage(), i, this.gameView.getHeight() - height, null);
            //Log.d("Testing",String.valueOf(this.gameView.getHeight()));//Width 1794 Height 1017
        }

    }
}
