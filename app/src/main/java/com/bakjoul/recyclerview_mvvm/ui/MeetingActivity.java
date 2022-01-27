package com.bakjoul.recyclerview_mvvm.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.bakjoul.recyclerview_mvvm.R;
import com.bakjoul.recyclerview_mvvm.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MeetingActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding b;

    private MeetingViewModel viewModel;
    private ListAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        viewModel = new ViewModelProvider(this).get(MeetingViewModel.class);

        adapter = new ListAdapter();
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Avec le RecyclerViewAdapter
        /*Observer<List<Item>> itemListUpdateObserver = items -> adapter.updateItemList(items);
        viewModel.getItemLiveData().observe(this, itemListUpdateObserver);*/

        viewModel.getMeetingsLiveData().observe(this, meetingList -> {
            adapter.submitList(meetingList);
        });

        b.fabAdd.setOnClickListener(view -> {
            viewModel.addMeeting();
        });
    }

}