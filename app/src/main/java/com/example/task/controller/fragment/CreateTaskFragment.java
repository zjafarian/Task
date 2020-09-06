package com.example.task.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.controller.activity.TaskListActivity;
import com.google.android.material.textfield.TextInputEditText;

public class CreateTaskFragment extends Fragment {
    public static final String EXTRA_SEND_NAME_TASK = "com.example.task.sendNameTask";
    public static final String EXTRA_SEND_NUMBER_TASK = "com.example.task.sendNumberTask";
    private TextInputEditText mTextNameTask;
    private TextInputEditText mTextNumber;
    private Button mButtonCreate;


    public CreateTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_task, container, false);
        setView(view);
        setListener();
        return view;
    }
    private void setView(View view) {
        mTextNameTask= view.findViewById(R.id.text_input_name);
        mTextNumber = view.findViewById(R.id.text_input_number);
        mButtonCreate=view.findViewById(R.id.btn_create);
    }

    private void setListener() {
        mButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mTextNameTask.getText().toString().trim();
                String number = mTextNumber.getText().toString().trim();
                if (name.equals("") || number.equals("") || name.equals(" ") || number.equals(" ")){
                    Toast toast = Toast.makeText(getActivity(),R.string.message_empty_field,Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM,0,0);
                    toast.show();
                } else {
                    Intent intent = new Intent(getActivity(), TaskListActivity.class);
                    intent.putExtra(EXTRA_SEND_NAME_TASK,name);
                    intent.putExtra(EXTRA_SEND_NUMBER_TASK,number);
                    startActivity(intent);
                }
            }
        });
    }




}