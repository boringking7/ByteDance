package com.example.chapter3.homework;


import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlaceholderFragment extends Fragment {
    private ListView mListView;
    private TitleAdapter mTitleAdapter;
    private List<TitleData> mTitleDataList;
    private View titleView;
    private LottieAnimationView animationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        titleView = inflater.inflate(R.layout.fragment_placeholder , container, false);
        return titleView;
    }

    @Override
    //当Activity完成onCreate()时调用
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //在此加载Lottie动画，放在onCreateView程序就崩溃！！！！
        animationView = getView().findViewById(R.id.animation_view2);
        animationView.playAnimation();
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4实现动画，将 lottie 控件淡出，列表数据淡入

                // 设置内容animationView的淡出
                animationView.animate()
                        .alpha(0f)
                        .setDuration(1000)
                        .setListener(null);
                animationView.cancelAnimation();



                mTitleDataList = getTitleDataList();
                mListView = (ListView) titleView.findViewById(R.id.titleListView);
                mTitleAdapter = new TitleAdapter();
                mListView.setAdapter(mTitleAdapter);
                mListView.setVerticalFadingEdgeEnabled(true);
                mListView.setFadingEdgeLength(1000);
                // 设置内容mListView的淡入
                mListView.setAlpha(0f);
                mListView.setVisibility(View.VISIBLE);
                mListView.animate()
                        .alpha(1f)
                        .setDuration(3000)
                        .setListener(null);

            }
        }, 5000);
    }
    public List<TitleData> getTitleDataList() {
        List<TitleData> listItem = new ArrayList<TitleData>();
        for(int i = 1; i < 112; i++) {
            TitleData data = new TitleData("盆友" + i, getActivity().getResources().getDrawable(R.drawable.abc_btn_check_material));
            listItem.add(data);
        }
        return listItem;
    }

    class TitleData {
        private String mTitle;
        private Drawable mIcon;

        public TitleData(String title, Drawable icon) {
            this.mTitle = title;
            this.mIcon = icon;
        }

        public String getmTitle() {
            return mTitle;
        }

        public Drawable getmIcon() {
            return mIcon;
        }
    }

    class TitleAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mTitleDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return mTitleDataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            View view = convertView;

            if(view == null) {
                view = getActivity().getLayoutInflater().inflate(R.layout.listitem_title, null);
                viewHolder = new ViewHolder();
                viewHolder.mTitleImageView = (ImageView) view.findViewById(R.id.titleImageView);
                viewHolder.mTitleTextView = (TextView) view.findViewById(R.id.titleTextView);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            final TitleData titleData = (TitleData) getItem(position);
            viewHolder.mTitleTextView.setText(titleData.getmTitle());
            viewHolder.mTitleImageView.setImageDrawable(titleData.getmIcon());

            return view;
        }

        class ViewHolder {
            protected ImageView mTitleImageView;
            protected TextView mTitleTextView;
        }
    }




}
