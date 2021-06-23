package com.lanky.tv.settingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanky.tv.R;
import com.lanky.tv.UIUtils;

/**
 * @ClassName DefaultItem
 * @Description 默认的设置项
 * @Author LankyBin
 * @Date 2021/5/13 15:07
 * @Version 1.0
 */
public class DefaultItemLayout extends LinearLayout {
    private Context mContext;

    private ImageView mImageView;
    private int mIconResID;

    private TextView mTvName;
    private String mStr_name;

    public DefaultItemLayout(Context context) {
        super(context);
    }

    public DefaultItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DefaultItemLayout);

        mIconResID = typedArray.getResourceId(R.styleable.DefaultItemLayout_icon,R.mipmap.icon_default);
        mStr_name = typedArray.getString(R.styleable.DefaultItemLayout_name);

        initView();

        typedArray.recycle();
    }

    private void initView() {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_default, this);
        view.setFocusable(true);

        mImageView = view.findViewById(R.id.iv_icon);
        mImageView.setImageResource(mIconResID);

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
