package com.code.open.httpdemo.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.code.open.httpdemo.R;
import com.code.open.httpdemo.databinding.ActivityMainBinding;
import com.code.open.httpdemo.holder.RecycleHolder;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        RecycleHolderFace holder = RecycleHolder.getInstance();
        binding.setHolder(holder);
        binding.recycle.setAdapter(holder.getAdapter());
        binding.recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public interface RecycleHolderFace {

        void onSearchClick(View v, String name);

        RecyclerView.Adapter getAdapter();
    }
}
