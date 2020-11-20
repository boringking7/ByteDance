package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SearchLayout searchLayout;
    private RecyclerView mRecyclerView;
    private SearchAdapter mSearchAdapter = new SearchAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//LinearLayoutManager默认垂直，LinearLayout默认水平
        mRecyclerView.setAdapter(mSearchAdapter);
        List<String> items = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            items.add("该睡了，已经是第 " + i + " 只羊了");
        }
        mSearchAdapter.notifyItems(items);

        searchLayout = findViewById(R.id.search);
        searchLayout.setOnSearchTextChangedListener(new SearchLayout.OnSearchTextChangedListener() {
            @Override
            public void afterChanged(String text) {

                List<String> filters = new ArrayList<>();
                for (String item : items) {
                    if (item.contains(text)) {
                        filters.add(item);
                    }
                }
                mSearchAdapter.notifyItems(filters);
                Log.d("TAG", "onChanged: " + text);
            }
        });
    }
}