package com.e.pianoforkids.data.model;

import android.graphics.RectF;

public class Key {
    public RectF rect;
    public boolean down;
    public int sound;


    public Key(int sound, RectF rect) {
        this.sound = sound;
        this.rect = rect;
    }


}
