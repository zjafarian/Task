package com.example.task.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.task.R;
import com.example.task.controller.fragment.ListTaskFragment;

public class TaskListActivity extends SingleFragmentActivity {


    @Override
    public Fragment createFragment() {
        return new ListTaskFragment();
    }
}