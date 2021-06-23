package com.lanky.customassortedview;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lanky.tv.settingview.DefaultItemLayout;
import com.lanky.tv.settingview.SeekBarItemLayout;
import com.lanky.tv.settingview.SelectItemLayout;
import com.lanky.tv.settingview.SwitchItemLayout;

public class MainActivity extends AppCompatActivity{

    private DefaultItemLayout mDefault_app_manager;
    private SelectItemLayout mSelect_language;
    private SeekBarItemLayout mSeekbar_brightness;
    private SwitchItemLayout mSwitch_keypad_sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mDefault_app_manager = (DefaultItemLayout) findViewById(R.id.default_app_manager);
        mSelect_language = (SelectItemLayout) findViewById(R.id.select_language);
        mSeekbar_brightness = (SeekBarItemLayout) findViewById(R.id.seekbar_brightness);
        mSwitch_keypad_sound = (SwitchItemLayout) findViewById(R.id.switch_keypad_sound);

//        mSelect_language.initOptions(R.array.system_language_options);
        mSelect_language.setOnOptionChangeListener(new SelectItemLayout.OnOptionChangeListener() {
            @Override
            public void onOptionChanged(int position) {
                Toast.makeText(MainActivity.this, "language:" + position, Toast.LENGTH_SHORT).show();
            }
        });

        mSeekbar_brightness.setProgress(0);
        mSeekbar_brightness.setSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSeekbar_brightness.setProgress(progress);
                Toast.makeText(MainActivity.this, "brightness : " + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSwitch_keypad_sound.setOnCheckedCHangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "keypad sound : " + mSwitch_keypad_sound.isCheched(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
