package view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.e.pianoforkids.PianoActivity;
import com.e.pianoforkids.R;
import com.skyfishjy.library.RippleBackground;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity implements OnClickListener {

    private MediaPlayer sound_player;
    private MediaPlayer background_music;
    private ImageButton button_songs;
    private ImageButton button_instruments;
    private ImageButton button_instructions;


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
        final RippleBackground rippleBackground = findViewById(R.id.song_content);
        final RippleBackground rippleBackground1 = findViewById(R.id.instructions_content);
        final RippleBackground rippleBackground2 = findViewById(R.id.instrument_content);

        rippleBackground.startRippleAnimation();
        rippleBackground1.startRippleAnimation();
        rippleBackground2.startRippleAnimation();

        sound_player = MediaPlayer.create(this, R.raw.click_sound_effects);
        background_music = MediaPlayer.create(this, R.raw.background_music);
        playBackgroundMusic();

        button_songs = findViewById(R.id.button_songs);
        button_instruments = findViewById(R.id.button_instruments);
        button_instructions = findViewById(R.id.button_instructions);


        button_songs.setOnClickListener(this);
        button_instruments.setOnClickListener(this);
        button_instructions.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // Perform action on click
        switch (v.getId()) {
            case R.id.button_songs:

                // Intent goToSongsActivity = new Intent(this, SettingsActivity.class);
                // startActivity(goToSongsActivity);
                break;
            case R.id.button_instruments:
                if(background_music.isPlaying())
                    background_music.stop();

                Intent goToInstrumentsActivity = new Intent(this, PianoActivity.class);
                startActivity(goToInstrumentsActivity);
                break;
            case R.id.button_instructions:


                break;
            case R.id.button_settings:
                Intent goToSettingsActivity = new Intent(this, SettingsActivity.class);
                startActivity(goToSettingsActivity);
                break;
        }
    }



    public void playSound(View view) {
        if (!sound_player.isPlaying())
            sound_player.start();

        background_music.stop();
    }

    public void playBackgroundMusic() {
        background_music.setLooping(true);
        if (!background_music.isPlaying()) {
            background_music.setLooping(true);
            background_music.start();
        }
    }


    public void button_settings_onCLick(View view) {
        if (!sound_player.isPlaying())
            sound_player.start();

        background_music.stop();

        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }
}
