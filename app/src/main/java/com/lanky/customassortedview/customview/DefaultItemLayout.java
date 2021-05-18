package com.lanky.customassortedview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanky.customassortedview.R;
import com.lanky.customassortedview.UIUtils;

/**
 * @ClassName DefaultItem
 * @Description 默认的设置项
 * @Author LankyBin
 * @Date 2021/5/13 15:07
 * @Version 1.0
 */
public class DefaultItemLayout extends LinearLayout {
    private Context context;

    TextView tvItemName;
    private String str_name;

    public DefaultItemLayout(Context context) {
        super(context);
    }

    public DefaultItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DefaultItemLayout);

        str_name = typedArray.getString(R.styleable.DefaultItemLayout_name);

        initView();

        typedArray.recycle();
    }

    private void initView() {
        //子控件绑定
        final View view = LayoutInflater.from(context).inflate(R.layout.layout_item_default, this);
        view.setFocusable(true);

        tvItemName = (TextView) view.findViewById(R.id.tv_item_name);
        tvItemName.setFocusable(false);
        tvItemName.setText(str_name);

        view.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                UIUtils.animateView(view,hasFocus,1.02f,1.02f);
                tvItemName.setSelected(hasFocus);
                if (hasFocus) {
                    view.setBackground(context.getResources().getDrawable(R.drawable.item_focused_background));
                } else {
                    view.setBackground(context.getResources().getDrawable(R.drawable.item_unfocused_background));
                }
            }
        });
    }
}
