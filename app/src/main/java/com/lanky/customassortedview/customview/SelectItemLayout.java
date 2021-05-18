package com.lanky.customassortedview.customview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanky.customassortedview.R;
import com.lanky.customassortedview.UIUtils;


/**
 * @ClassName SelectItemLayout
 * @Description TODO
 * @Author LankyBin
 * @Date 2021/5/13 10:03
 * @Version 1.0
 */
public class SelectItemLayout extends LinearLayout{
    private Context mContext;

    private String str_name;
    private TextView tv_name;
    private TextView tv_item_value;

    private OnOptionChangeListener mOnOptionChange;

    private int selected_position = 0;
    private String[] mOptions = {""};

    public SelectItemLayout(Context context) {
        super(context);
    }

    public SelectItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SelectItemLayout);

        str_name = typedArray.getString(R.styleable.SelectItemLayout_name);

        initView();

        typedArray.recycle();
    }

    private void initView() {
        //子控件绑定
        final View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_select, this);
        view.setFocusable(true);

        tv_name = (TextView) view.findViewById(R.id.tv_item_name);
        tv_name.setFocusable(false);
        tv_name.setText(str_name);

        tv_item_value = (TextView) view.findViewById(R.id.tv_item_value);
        tv_item_value.setFocusable(false);

        view.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                UIUtils.animateView(view, hasFocus, 1.02f, 1.02f);
                tv_name.setSelected(hasFocus);
                tv_item_value.setSelected(hasFocus);
                if (hasFocus) {
                    view.setBackground(mContext.getResources().getDrawable(R.drawable.item_focused_background));
                } else {
                    view.setBackground(mContext.getResources().getDrawable(R.drawable.item_unfocused_background));
                }
            }
        });
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionSelector();
            }
        });
    }

    public void initOptions(int array_res){
        mOptions = getResources().getStringArray(array_res);
    }

    public void setOptions(int position){
        tv_item_value.setText(mOptions[position]);
    }

    private void showOptionSelector() {
        final AlertDialog dialog;
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(mContext).setIcon(R.mipmap.app_icon).setTitle(str_name)
                .setSingleChoiceItems(mOptions, selected_position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selected_position = i;
                        tv_item_value.setText(mOptions[i]);
                        if (mOnOptionChange != null) {
                            mOnOptionChange.onOptionChanged(i);
                        }
                        dialogInterface.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
        dialog.show();
    }

    public void setOnOptionChangeListener(OnOptionChangeListener onOptionChangeListener) {
        this.mOnOptionChange = onOptionChangeListener;
    }

    public interface OnOptionChangeListener{
        void onOptionChanged(int position);
    }
}
