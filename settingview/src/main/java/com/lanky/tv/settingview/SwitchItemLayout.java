package com.lanky.tv.settingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.lanky.tv.R;
import com.lanky.tv.UIUtils;

/**
 * @ClassName SwitchItemLayout
 * @Description TODO
 * @Author LankyBin
 * @Date 2021/5/13 15:48
 * @Version 1.0
 */
public class SwitchItemLayout extends LinearLayout {
    private Context mContext;

    private ImageView mImageView;
    private int mIconResID;

    private TextView mTvName;
    private int mNameColorID;
    private String mStr_name;

    private Switch mSwitch;

    public SwitchItemLayout(Context context) {
        super(context);
    }

    public SwitchItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchItemLayout);

        mIconResID = typedArray.getResourceId(R.styleable.SwitchItemLayout_icon, R.mipmap.icon_default);
        mNameColorID = typedArray.getResourceId(R.styleable.DefaultItemLayout_name_color, R.color.item_name_text_color);
        mStr_name = typedArray.getString(R.styleable.SwitchItemLayout_name);

        initView();

        typedArray.recycle();
    }

    private void initView() {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_switch, this);
        view.setFocusable(true);

        mImageView = view.findViewById(R.id.iv_icon);
        mImageView.setImageResource(mIconResID);

        mTvName = (TextView) view.findViewById(R.id.tv_item_name);
        mTvName.setFocusable(false);
        mTvName.setTextColor(mContext.getColor(mNameColorID));
        mTvName.setText(mStr_name);

        mSwitch = (Switch) view.findViewById(R.id.switch_item);

        view.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                UIUtils.animateView(view, hasFocus, 1.02f, 1.02f);
                mTvName.setSelected(hasFocus);
            }
        });
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(!mSwitch.isChecked());
            }
        });
    }

    public void setOnCheckedCHangeListener(CompoundButton.OnCheckedChangeListener l) {
        mSwitch.setOnCheckedChangeListener(l);

    }

    public void setChecked(boolean checked) {
        mSwitch.setChecked(checked);
    }

    public boolean isCheched() {
        return mSwitch.isChecked();
    }
}
