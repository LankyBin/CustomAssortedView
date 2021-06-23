package com.lanky.customsettingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @ClassName DefaultItem
 * @Description 默认的设置项
 * @Author LankyBin
 * @Date 2021/5/13 15:07
 * @Version 1.0
 */
public class DefaultItemLayout extends LinearLayout {
    private Context mContext;

    private TextView mTvName;
    private String mStr_name;

    public DefaultItemLayout(Context context) {
        super(context);
    }

    public DefaultItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DefaultItemLayout);

        mStr_name = typedArray.getString(R.styleable.DefaultItemLayout_name);

        initView();

        typedArray.recycle();
    }

    private void initView() {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_default, this);
        view.setFocusable(true);

        mTvName = (TextView) view.findViewById(R.id.tv_item_name);
        mTvName.setFocusable(false);
        mTvName.setText(mStr_name);

        view.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                UIUtils.animateView(view,hasFocus,1.02f,1.02f);
                mTvName.setSelected(hasFocus);
            }
        });
    }
}
