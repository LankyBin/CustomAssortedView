package com.lanky.tv.settingview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
 * @ClassName SelectItemLayout
 * @Description 多选设置项
 * @Author LankyBin
 * @Date 2021/5/13 10:03
 * @Version 1.0
 */
public class SelectItemLayout extends LinearLayout {
    private Context mContext;

    private ImageView mImageView;
    private int mIconResID;

    private TextView mTvName;
    private int mNameColorID;
    private String mStr_name;

    private TextView mTvValue;

    private OnOptionChangeListener mOnOptionChange;

    private int mSelectedPosition = 0;
    private String[] mOptions = {""};

    public SelectItemLayout(Context context) {
        super(context);
    }

    public SelectItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SelectItemLayout);

        mIconResID = typedArray.getResourceId(R.styleable.SelectItemLayout_icon, R.mipmap.icon_default);
        mNameColorID = typedArray.getResourceId(R.styleable.DefaultItemLayout_name_color, R.color.item_name_text_color);
        mStr_name = typedArray.getString(R.styleable.SelectItemLayout_name);
        mOptions = mContext.getResources().getStringArray(typedArray.getResourceId(R.styleable.SelectItemLayout_options, R.array.select_options_default));

        initView();

        typedArray.recycle();
    }

    private void initView() {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_select, this);
        view.setFocusable(true);

        mImageView = view.findViewById(R.id.iv_icon);
        mImageView.setImageResource(mIconResID);

        mTvName = (TextView) view.findViewById(R.id.tv_item_name);
        mTvName.setFocusable(false);
        mTvName.setTextColor(mContext.getColor(mNameColorID));
        mTvName.setText(mStr_name);

        mTvValue = (TextView) view.findViewById(R.id.tv_item_value);
        mTvValue.setFocusable(false);

        view.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                UIUtils.animateView(view, hasFocus, 1.02f, 1.02f);
                mTvName.setSelected(hasFocus);
                mTvValue.setSelected(hasFocus);
            }
        });
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionSelector();
            }
        });
    }

    public void initOptions(int array_res) {
        mOptions = getResources().getStringArray(array_res);
    }

    public void setOptions(int position) {
        mTvValue.setText(mOptions[position]);
    }

    private void showOptionSelector() {
        final AlertDialog dialog;
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(mContext).setIcon(R.mipmap.app_icon).setTitle(mStr_name)
                .setSingleChoiceItems(mOptions, mSelectedPosition, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mSelectedPosition = i;
                        mTvValue.setText(mOptions[i]);
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

    public interface OnOptionChangeListener {
        void onOptionChanged(int position);
    }
}
