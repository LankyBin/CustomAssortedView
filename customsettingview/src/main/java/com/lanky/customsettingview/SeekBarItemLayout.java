package com.lanky.customsettingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * @ClassName SeekBarItemLayout
 * @Description 带有seekbar的设置项
 * @Author LankyBin
 * @Date 2021/5/13 12:00
 * @Version 1.0
 */
public class SeekBarItemLayout extends LinearLayout {
    private Context mContext;

    private TextView mTvName;
    private String mStr_name;
    private TextView mTvValue;

    private SeekBar mSeekBar;
    private int mProgressMax = 100;
    private int mProgressMin = 0;
    private int mProgress = 50;

    private boolean showValue;

    public SeekBarItemLayout(Context context) {
        super(context);
    }

    public SeekBarItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SeekbarItemLayout);

        mStr_name = typedArray.getString(R.styleable.SeekbarItemLayout_name);
        mProgressMax = typedArray.getInt(R.styleable.SeekbarItemLayout_seekbar_progress_max, mProgressMax);
        mProgressMin = typedArray.getInt(R.styleable.SeekbarItemLayout_seekbar_progress_min, mProgressMin);
        showValue = typedArray.getBoolean(R.styleable.SeekbarItemLayout_show_value,true);

        initView();

        typedArray.recycle();
    }

    private void initView() {
        //子控件绑定
        final View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_seekbar, this);
        view.setFocusable(true);

        mTvName = (TextView) view.findViewById(R.id.tv_item_name);
        mTvName.setFocusable(false);
        mTvName.setText(mStr_name);

        mTvValue = (TextView) view.findViewById(R.id.tv_progress);
        mTvValue.setFocusable(false);
        if (showValue) {
            mTvValue.setVisibility(VISIBLE);
        } else {
            mTvValue.setVisibility(INVISIBLE);
        }

        mSeekBar = (SeekBar) view.findViewById(R.id.seekbar_item);
        mSeekBar.setMax(mProgressMax);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mSeekBar.setMin(mProgressMin);
        }

        view.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                UIUtils.animateView(view, hasFocus, 1.02f, 1.02f);
                mTvName.setSelected(hasFocus);
            }
        });
    }

    public void setSeekBarChangeListener(SeekBar.OnSeekBarChangeListener seekBarChangeListener) {
        mSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
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
        mProgress = (mProgress >= mProgressMax) ? mProgressMax : mProgress + 1;
        updateProgress();
    }

    public void setSeekBarLeft() {
        mProgress = mProgress <= mProgressMin ? mProgressMin : mProgress - 1;
        updateProgress();
    }

    public void setProgress(int progress){
        this.mProgress = progress;
        updateProgress();
    }

    private void updateProgress(){
        mSeekBar.setProgress(mProgress);
        mTvValue.setText(mProgress +"");
    }
}


