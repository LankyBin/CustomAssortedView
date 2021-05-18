package com.lanky.customassortedview;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lanky.customassortedview.customview.DefaultItemLayout;
import com.lanky.customassortedview.customview.SeekBarItemLayout;
import com.lanky.customassortedview.customview.SelectItemLayout;
import com.lanky.customassortedview.customview.SwitchItemLayout;

public class MainActivity extends AppCompatActivity{

    private DefaultItemLayout default_item_layout1;
    private DefaultItemLayout default_item_layout2;
    private SelectItemLayout select_item_layout1;
    private SelectItemLayout select_item_layout2;
    private SeekBarItemLayout seekbar_item_layout1;
    private SeekBarItemLayout seekbar_item_layout2;
    private SwitchItemLayout switch_item_layout1;
    private SwitchItemLayout switch_item_layout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        default_item_layout1 = (DefaultItemLayout) findViewById(R.id.default_item_layout1);
        default_item_layout2 = (DefaultItemLayout) findViewById(R.id.default_item_layout2);
        select_item_layout1 = (SelectItemLayout) findViewById(R.id.select_item_layout1);
        select_item_layout2 = (SelectItemLayout) findViewById(R.id.select_item_layout2);
        seekbar_item_layout1 = (SeekBarItemLayout) findViewById(R.id.seekbar_item_layout1);
        seekbar_item_layout2 = (SeekBarItemLayout) findViewById(R.id.seekbar_item_layout2);
        switch_item_layout1 = (SwitchItemLayout) findViewById(R.id.switch_item_layout1);
        switch_item_layout2 = (SwitchItemLayout) findViewById(R.id.switch_item_layout2);

        select_item_layout1.initOptions(R.array.projector_mode_options);
        select_item_layout1.setOnOptionChangeListener(new SelectItemLayout.OnOptionChangeListener() {
            @Override
            public void onOptionChanged(int position) {
                Toast.makeText(MainActivity.this, "main : select_item_layout1:" + position, Toast.LENGTH_SHORT).show();
            }
        });

        select_item_layout2.initOptions(R.array.system_language_options);
        select_item_layout2.setOnOptionChangeListener(new SelectItemLayout.OnOptionChangeListener() {
            @Override
            public void onOptionChanged(int position) {
                Toast.makeText(MainActivity.this, "main : select_item_layout2:" + position, Toast.LENGTH_SHORT).show();
            }
        });

        seekbar_item_layout1.setProgress(0);
        seekbar_item_layout1.setSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbar_item_layout1.setProgress(progress);
                Toast.makeText(MainActivity.this, "seekbar_item_layout1 : " + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbar_item_layout2.setProgress(0);
        seekbar_item_layout2.setSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbar_item_layout2.setProgress(progress);
                Toast.makeText(MainActivity.this, "seekbar_item_layout2 : " + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        switch_item_layout1.setOnCheckedCHangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "switch_item_layout1: " + switch_item_layout1.isCheched(), Toast.LENGTH_SHORT).show();
            }
        });
        switch_item_layout2.setOnCheckedCHangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "switch_item_layout2: " + switch_item_layout2.isCheched(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
