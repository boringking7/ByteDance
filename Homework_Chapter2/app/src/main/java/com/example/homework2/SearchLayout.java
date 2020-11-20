package com.example.homework2;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class SearchLayout extends LinearLayout {
    private EditText mEditView;

    private OnSearchTextChangedListener mListener;


    public SearchLayout(Context context) {
        super(context);
        init();//构造函数调用初始化函数
    }

    public SearchLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();//构造函数调用初始化函数
    }

    public SearchLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();//构造函数调用初始化函数
    }


    public void init() {
        inflate(getContext(),R.layout.layout_search,this);//将layout_search.xml绑定在SearchLayout对象上
        mEditView = findViewById(R.id.input);
        mEditView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mListener != null) {
                    mListener.afterChanged(s.toString());
                }
            }
        });

    }
    public void setOnSearchTextChangedListener(OnSearchTextChangedListener listener){
        mListener = listener;
    }

    public interface OnSearchTextChangedListener {
        public void afterChanged(String text);
    }

}
