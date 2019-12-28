package com.example.third;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends  Thread implements Runnable{

    private boolean runnig;
    private GameView gameView;


    private SurfaceHolder surfaceHolder;

    public GameThread(GameView gameView,SurfaceHolder surfaceHolder)
    {

        this.gameView=gameView;


        this.surfaceHolder=surfaceHolder;

    }

    @Override
    public void run()
    {
        /* Menaxhimi i frames*/
        long lastTime=System.nanoTime();
        double nanoSecondConversion=1000000000.0/30;//30 frames per second
        double changeInSeconds=0;

        while(runnig){
            //Request focus for listening to events
            this.gameView.requestFocus();
            //Sets the canvas to null //MUST ME CHANGED
            Canvas canvas=null;


            long now=System.nanoTime();
            changeInSeconds +=(now-lastTime)/nanoSecondConversion;
            while(changeInSeconds>=1)
            {
                //it update all values of the game
                this.gameView.update();
                changeInSeconds=0;
            }
            try{
                //Locks the canvas so it cant be used by other operations
                canvas=this.surfaceHolder.lockCanvas();

                //only one thread can use canvas at a time
                synchronized (canvas){
                    //draws the updated values on canvas(but it is still locked and wont be shown till it gets unlocked)
                    this.gameView.draw(canvas);
                    lastTime=now;

                    }
                }catch (Exception e){}
                finally {
                if(canvas!=null)
                //unlocks the canvas and post the result to the surface(on screen)
                {this.surfaceHolder.unlockCanvasAndPost(canvas);}

                }
            }

        }


        //Controlling function to start or to stop the game loop
        public  void setRunnig(boolean a)
        {
            this.runnig=a;
        }

    }


