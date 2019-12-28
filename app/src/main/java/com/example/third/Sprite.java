package com.example.third;

import android.graphics.Bitmap;

public class Sprite {
    private Bitmap image;
    private int _width;
    private int _height;



    Sprite(Bitmap bitmap){
        this.image=bitmap;
        this._width=bitmap.getWidth();
        this._height=bitmap.getHeight();
    }



    public Bitmap getImage() {
        return image;
    }

    public int get_width() {
        return _width;
    }

    public int get_height() {
        return _height;
    }


}
