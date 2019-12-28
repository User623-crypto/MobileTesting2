package com.example.third;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//Surface View is similar to JFrame
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    //Main Activity calls the view which will call thread;
    private GameThread gameThread;
    private Player player;
    private Sprite sprite;
    private Map map;

    //For testing (not important)
    private Boolean moveRight=false;
    private Boolean moveLeft=false;

    //constructor takes the Main activity as a parameter since it will be shown there
    public GameView(Context context) {
        super(context);

        this.setFocusable(true);

        //Set callBack interface for this holder  https://developer.android.com/reference/android/view/SurfaceHolder
        this.getHolder().addCallback(this);
        //SurfaceHolder it is similar to JPanel it holds canvas

    }

    //update the player values
    public void update()
    {
        if(moveRight==true) {

            this.player.update();
        }
        if(moveLeft==true)
        {
            this.player.moveLeft();
        }
    }

    //draws on canvas
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.player.draw(canvas);
        this.map.draw(canvas);
    }

    //This is called after the constructor the holder sets what will be shown on screen
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //Takes the image from resources:in this case its drawable
        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),R.drawable.batman);
        Bitmap grassTile=BitmapFactory.decodeResource(this.getResources(),R.drawable.grasstile);

        //Sets image to sprite
        this.sprite=new Sprite(bitmap);
        //Sets image for the map tiles
        Sprite a=new Sprite(grassTile);

        //creates a Map with map tile image
        this.map=new Map(a,this);

        //creates the player
        this.player=new Player(this,100,this.getHeight()-this.sprite.get_height()-100,this.sprite);

        //creates the game thread
        this.gameThread=new GameThread(this,holder);
        this.gameThread.setRunnig(true);
        this.gameThread.start();
       // Log.d("Testing",String.valueOf(this.getHeight()));//Width 1794 Height 1017

    }

    //If surface is changed this function is called (if device is rotated)
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //we have set it to stay landscaped so this isn't needed;

    }

   //When interrupted this is called, ***************Must be changed*********************
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        this.gameThread.setRunnig(false);
        while(true)
        {
            try{

                this.gameThread.join();
                break;
            }catch (InterruptedException e) {e.printStackTrace();}

        }

    }

    //Calls eventListener on Touch for Testing
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //If the user Touches the screen
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {

            if((int)event.getX()>this.getWidth()/2)
            {moveRight=true;}

            if((int)event.getX()<=this.getWidth()/2)
            {moveLeft=true;}
            return true;
        }
        else {moveRight=false;moveLeft=false;
        return false;}
    }
}
