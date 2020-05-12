package com.e.pianoforkids.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.e.pianoforkids.R;
import com.skyfishjy.library.RippleBackground;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    private MediaPlayer sound_player;
    private MediaPlayer background_music;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //request full screen for this activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //set view layout for this activity
        setContentView(R.layout.activity_main_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //create animation for button
        final RippleBackground rippleBackground= findViewById(R.id.song_content);
        final RippleBackground rippleBackground1= findViewById(R.id.instructions_content);
        final RippleBackground rippleBackground2= findViewById(R.id.instrument_content);
        rippleBackground.startRippleAnimation();
        rippleBackground1.startRippleAnimation();
        rippleBackground2.startRippleAnimation();

        sound_player = MediaPlayer.create(this, R.raw.click_sound_effects);
        background_music = MediaPlayer.create(this,R.raw.background_music);
        playBackgroundMusic();
    }

    public void playSound(View view) {
        if (!sound_player.isPlaying())
            sound_player.start();

        background_music.stop();

        Intent pianoIntent = new Intent(this, MainMenuActivity.class);
        startActivity(pianoIntent);
    }

    public void playBackgroundMusic() {
        background_music.setLooping(true);
        if (!background_music.isPlaying()) {
            background_music.setLooping(true);
            background_music.start();
        }
    }
}
