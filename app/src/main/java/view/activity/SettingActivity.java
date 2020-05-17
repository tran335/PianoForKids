package view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.e.pianoforkids.R;
import com.e.pianoforkids.viewmodel.SettingViewModel;

public class SettingActivity extends AppCompatActivity {
    private SettingViewModel settingViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        settingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);
        final Button btnLanguage = findViewById(R.id.btnLanguage);
        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingViewModel.updateSetting("language");
            }
        });
        final Button btnSound = findViewById(R.id.btnSound);
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingViewModel.updateSetting("isSound");

            }
        });
        final Button btnBackgroundMusic = findViewById(R.id.btnBackgroundMusic);
        btnBackgroundMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingViewModel.updateSetting("isBackgroundMusic");

            }
        });
        settingViewModel.getLanguage().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean value) {
                Log.d("getLanguage", String.valueOf(value));
                btnLanguage.setText(String.valueOf(value));
            }
        });
        settingViewModel.getIsSound().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean value) {
                Log.d("getIsBackgroundMusic", String.valueOf(value));
                btnSound.setText(String.valueOf(value));
            }
        });
        settingViewModel.getIsBackgroundMusic().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean value) {
                Log.d("getIsBackgroundMusic", String.valueOf(value));
                btnBackgroundMusic.setText(String.valueOf(value));

            }
        });
    }
}
