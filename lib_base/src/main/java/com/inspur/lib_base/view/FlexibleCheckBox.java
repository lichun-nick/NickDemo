package com.inspur.lib_base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.inspur.lib_base.R;

/**
 * @author lichun
 */
public class FlexibleCheckBox extends LinearLayout {

    private TextView tvTitle;
    private ImageView ivCheck;

    private Context context;
    private int boxType;
    private boolean isChecked;
    private String mTitle;

    private OnCheckListener onCheckListener;

    public FlexibleCheckBox(Context context) {
        this(context,null);
    }

    public FlexibleCheckBox(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlexibleCheckBox(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.layout_flexible_checkbox,this,true);
        tvTitle = findViewById(R.id.tv_flexible_checkbox);
        ivCheck = findViewById(R.id.iv_flexible_checkbox);
        tvTitle.setText(mTitle);
        setRightDrawable(boxType,isChecked);
        setClickListener();

    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.FlexibleCheckBox);
        final int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            initAttr(typedArray.getIndex(i),typedArray);
        }
        typedArray.recycle();
    }

    private void initAttr(int attr, TypedArray typedArray) {
        if(attr == R.styleable.FlexibleCheckBox_fcbBoxType){
            boxType = typedArray.getInt(attr,0);
        }else if(attr == R.styleable.FlexibleCheckBox_fcbTitleText){
            mTitle = (String) typedArray.getText(attr);
        }else if(attr == R.styleable.FlexibleCheckBox_fcbChecked){
            isChecked = typedArray.getBoolean(attr,false);
        }
    }

    private void setClickListener() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                 setRightDrawable(boxType,!isChecked);
                 isChecked = !isChecked;
                if(onCheckListener != null){
                    onCheckListener.onCheckChanged(ivCheck,isChecked);
                }
            }
        });
    }

    private void setRightDrawable(int type,boolean isChecked){
        if(type == 0){
            if (isChecked) {
                ivCheck.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.icon_checked));
            } else {
                ivCheck.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.icon_unchecked));
            }
        }else if(type == 1){
            if (isChecked) {
                ivCheck.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.icon_check_box));
            } else {
                ivCheck.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.icon_uncheck_box));
            }
        }
    }

    public void setText(String title){
        this.mTitle = title;
        tvTitle.setText(title);
    }

    public String getText(){
        return mTitle;
    }

    public void setChecked(boolean checked){
        this.isChecked = checked;
        setRightDrawable(boxType,checked);
    }

    public boolean isChecked(){
        return isChecked;
    }

    public void setOnCheckListener(OnCheckListener onCheckListener){
        this.onCheckListener = onCheckListener;
    }

    public interface OnCheckListener{
        /**
         *check 状态监听
         * @param view
         * @param isChecked
         */
        void onCheckChanged(View view,boolean isChecked);
    }
}
