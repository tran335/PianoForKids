package com.e.pianoforkids;

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
