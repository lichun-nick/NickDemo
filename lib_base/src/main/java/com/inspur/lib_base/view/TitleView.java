package com.inspur.lib_base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.inspur.lib_base.R;

/**
 * @author lichun
 * 导航栏
 */
public class TitleView extends RelativeLayout {

    private FrameLayout mFlLeft;
    private FrameLayout mFlRight;
    private AppCompatImageView mLeftView;
    private AppCompatImageView mRightView;
    private TextView mTitleView;
    private Delegate mDelegate;

    public TitleView(Context context) {
        this(context,null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_title_view,this,true);
        initView();
        setListener();
        initAttrs(context,attrs);
    }


    private void setListener() {

    }

    private void initView() {
        mFlLeft = findViewById(R.id.fl_title_left);
        mFlRight = findViewById(R.id.fl_title_right);
        mLeftView = findViewById(R.id.iv_title_left);
        mTitleView = findViewById(R.id.tv_title_middle);
        mRightView = findViewById(R.id.iv_title_right);
        mTitleView.setTextColor(Color.WHITE);
        setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TitleView);
        final int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            initAttr(typedArray.getIndex(i),typedArray);
        }
        typedArray.recycle();
    }

    private void initAttr(int attr, TypedArray typedArray) {
        if(attr == R.styleable.TitleView_ttLeftDrawable){
            setLeftDrawable(typedArray.getDrawable(attr));
            mFlLeft.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mDelegate != null){
                        mDelegate.onClickLeft(v);
                    }
                }
            });
        }else if(attr == R.styleable.TitleView_ttRightDrawable){
            setRightDrawable(typedArray.getDrawable(attr));
            mFlRight.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mDelegate != null){
                        mDelegate.onClickRight(v);
                    }
                }
            });
        }else if(attr == R.styleable.TitleView_ttTitleText){
            setTitleText(typedArray.getText(attr));
        }else if(attr == R.styleable.TitleView_ttTitleSize){
            setTitleSize(typedArray.getDimension(attr,16.f));
        }else if(attr == R.styleable.TitleView_ttTitleColor){
            setTitleColor(typedArray.getColor(attr, Color.parseColor("#ffffff")));
        }else if(attr == R.styleable.TitleView_ttTitleBackgroundColor){
            setBackgroundColor(typedArray.getColor(attr, Color.parseColor("#319DFD")));
        }
    }



    private void setTitleColor(int color) {
        mTitleView.setTextColor(color);
    }

    private void setTitleSize(float dimension) {
        mTitleView.setTextSize(dimension);
    }

    private TitleView setTitleText(CharSequence text) {
        mTitleView.setText(text);
        return this;
    }

    private TitleView setRightDrawable(Drawable drawable) {
        mRightView.setImageDrawable(drawable);
        return this;
    }

    private TitleView setLeftDrawable(Drawable drawable) {
        mLeftView.setImageDrawable(drawable);
        return this;
    }

    public TitleView setDelegate(Delegate delegate){
        this.mDelegate = delegate;
        return this;
    }

    public interface Delegate {
        void onClickLeft(View v);
        void onClickRight(View v);
    }
}
