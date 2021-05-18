package com.lanky.customassortedview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lanky.customassortedview.R;
import com.lanky.customassortedview.UIUtils;


/**
 * @ClassName SeekBarItemLayout
 * @Description 带有seekbar的设置项
 * @Author LankyBin
 * @Date 2021/5/13 12:00
 * @Version 1.0
 */
public class SeekBarItemLayout extends LinearLayout {
    private Context context;

    private TextView tv_Name;
    private String str_name;
    private TextView tv_progress;

    private SeekBar seekBar;
    private int progress_max = 100;
    private int progress_min = 0;
    private int progress = 50;

    private boolean showValue;

    public SeekBarItemLayout(Context context) {
        super(context);
    }

    public SeekBarItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SeekbarItemLayout);

        str_name = typedArray.getString(R.styleable.SeekbarItemLayout_name);
        progress_max = typedArray.getInt(R.styleable.SeekbarItemLayout_seekbar_progress_max, progress_max);
        progress_min = typedArray.getInt(R.styleable.SeekbarItemLayout_seekbar_progress_min, progress_min);
        showValue = typedArray.getBoolean(R.styleable.SeekbarItemLayout_show_value,true);

        initView();

        typedArray.recycle();
    }

    private void initView() {
        //子控件绑定
        final View view = LayoutInflater.from(context).inflate(R.layout.layout_item_seekbar, this);
        view.setFocusable(true);

        tv_Name = (TextView) view.findViewById(R.id.tv_item_name);
        tv_Name.setFocusable(false);
        tv_Name.setText(str_name);

        tv_progress = (TextView) view.findViewById(R.id.tv_progress);
        tv_progress.setFocusable(false);
        if (showValue) {
            tv_progress.setVisibility(VISIBLE);
        } else {
            tv_progress.setVisibility(INVISIBLE);
        }

        seekBar = (SeekBar) view.findViewById(R.id.seekbar_item);
        seekBar.setMax(progress_max);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar.setMin(progress_min);
        }

        view.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                UIUtils.animateView(view, hasFocus, 1.02f, 1.02f);
                tv_Name.setSelected(hasFocus);
                if (hasFocus) {
                    view.setBackground(context.getResources().getDrawable(R.drawable.item_focused_background));
                } else {
                    view.setBackground(context.getResources().getDrawable(R.drawable.item_unfocused_background));
                }
            }
        });
    }

    public void setSeekBarChangeListener(SeekBar.OnSeekBarChangeListener seekBarChangeListener) {
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    setSeekBarLeft();
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    setSeekBarRight();
                    break;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public void setSeekBarRight() {
        progress = (progress >= progress_max) ? progress_max : progress + 1;
        updateProgress();
    }

    public void setSeekBarLeft() {
        progress = progress <= progress_min ? progress_min : progress - 1;
        updateProgress();
    }

    public void setProgress(int progress){
        this.progress = progress;
        updateProgress();
    }

    private void updateProgress(){
        seekBar.setProgress(progress);
        tv_progress.setText(progress +"");
    }
}


