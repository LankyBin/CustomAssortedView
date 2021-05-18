package com.lanky.customassortedview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.lanky.customassortedview.R;
import com.lanky.customassortedview.UIUtils;

/**
 * @ClassName SwitchItemLayout
 * @Description TODO
 * @Author LankyBin
 * @Date 2021/5/13 15:48
 * @Version 1.0
 */
public class SwitchItemLayout extends LinearLayout {
    private Context context;

    private TextView tv_Name;
    private String str_name;

    private Switch mSwitch;

    public SwitchItemLayout(Context context) {
        super(context);
    }

    public SwitchItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchItemLayout);

        str_name = typedArray.getString(R.styleable.SwitchItemLayout_name);

        initView();

        typedArray.recycle();
    }

    private void initView() {
        //子控件绑定
        final View view = LayoutInflater.from(context).inflate(R.layout.layout_item_switch, this);
        view.setFocusable(true);

        tv_Name = (TextView) view.findViewById(R.id.tv_item_name);
        tv_Name.setFocusable(false);
        tv_Name.setText(str_name);
//        tv_Name.setText("asdadf");

        mSwitch = (Switch) view.findViewById(R.id.switch_item);

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
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(!mSwitch.isChecked());
            }
        });
    }

    public void setOnCheckedCHangeListener(CompoundButton.OnCheckedChangeListener l){
        mSwitch.setOnCheckedChangeListener(l);

    }

    public void setChecked(boolean checked) {
        mSwitch.setChecked(checked);
    }

    public boolean isCheched(){
        return mSwitch.isChecked();
    }
}
