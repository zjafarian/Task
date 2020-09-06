package com.example.task.controller.fragment;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;

import java.util.List;


public class ListTaskFragment extends Fragment {
    public static final String SAVE_NAME = "save_name";
    public static final String SAVE_NUMBER = "save_number";
    public static final String SAVE_REPOSITORY = "save_repository";
    public static final String SAVE_CHECK_ORIENTATION = "save_check_orientation";
    private RecyclerView mRecyclerView;
    private String mName;
    private int mNumber = 0;
    private TaskRepository mTaskRepository;
    private boolean checkOrientation = false;
    private ImageButton mImgButtonAddTask;
    private List<Task> tasks;


    public ListTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mName = getActivity().getIntent().getStringExtra(CreateTaskFragment.EXTRA_SEND_NAME_TASK);
        mNumber = Integer.valueOf(getActivity().getIntent().getStringExtra(CreateTaskFragment.EXTRA_SEND_NUMBER_TASK));
        TaskRepository.setSizeTaskAndNameTask(mNumber, mName);
        mTaskRepository = TaskRepository.getInstance();
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE)
            checkOrientation = true;
        else checkOrientation = false;
        if (savedInstanceState!=null){
            mName=savedInstanceState.getString(SAVE_NAME);
            mNumber=savedInstanceState.getInt(SAVE_NUMBER);
            mTaskRepository= (TaskRepository) savedInstanceState.getSerializable(SAVE_REPOSITORY);
            checkOrientation=savedInstanceState.getBoolean(SAVE_CHECK_ORIENTATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_task, container, false);
        findView(view);
        if (checkOrientation)
            initViewsLandscape();
        else
            initViewsPortrait();
        return view;
    }

    private void setListener() {
        mImgButtonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task(mName,mTaskRepository.generateRandomState());
                mTaskRepository.insertTask(task);
            }
        });
    }


    private void findView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_task_list);
        mImgButtonAddTask = view.findViewById(R.id.image_btn_add_task);

    }

    private void initViewsLandscape() {
        setListener();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        setTaskAdaptar();
    }

    private void setTaskAdaptar() {
        tasks = mTaskRepository.getTaskList();
        TaskAdapter taskAdapter = new TaskAdapter(tasks);
        mRecyclerView.setAdapter(taskAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_NAME,mName);
        outState.putInt(SAVE_NUMBER,mNumber);
        outState.putSerializable(SAVE_REPOSITORY,mTaskRepository);
        outState.putBoolean(SAVE_CHECK_ORIENTATION,checkOrientation);
    }

    private void initViewsPortrait() {
        setListener();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setTaskAdaptar();
    }

    private class TaskHolder extends RecyclerView.ViewHolder {
        private ImageView mImageTodo;
        private ImageView mImageDoing;
        private ImageView mImageDone;
        private TextView mTextVieWTaskName;
        private TextView mTextViewState;
        private Task mTask;
        private CardView mCardView;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            setViewById(itemView);
        }


        private void setViewById(@NonNull View itemView) {
            mImageDoing = itemView.findViewById(R.id.image_doing);
            mImageTodo = itemView.findViewById(R.id.image_todo);
            mImageDone = itemView.findViewById(R.id.image_done);
            mTextVieWTaskName = itemView.findViewById(R.id.text_name);
            mTextViewState = itemView.findViewById(R.id.text_state);
            mCardView = itemView.findViewById(R.id.card_view);


        }

        public void bindTask(Task task, int position) {
            mTask = task;
            mTextVieWTaskName.setText(mTask.getStringNameTask());
            switch (mTask.getStateTask()) {
                case Done:
                    mImageDone.setVisibility(View.VISIBLE);
                    mImageTodo.setVisibility(View.GONE);
                    mImageDoing.setVisibility(View.GONE);
                    mTextViewState.setText("DONE");
                    break;
                case Doing:
                    mImageDoing.setVisibility(View.VISIBLE);
                    mImageDone.setVisibility(View.GONE);
                    mImageTodo.setVisibility(View.GONE);
                    mTextViewState.setText("DOING");
                    break;
                case Todo:
                    mImageTodo.setVisibility(View.VISIBLE);
                    mImageDone.setVisibility(View.GONE);
                    mImageDoing.setVisibility(View.GONE);
                    mTextViewState.setText("TODO");
                    break;
            }
            if (position % 2 == 0) {
                mCardView.setBackgroundResource(R.color.colorTealLight);
            } else mCardView.setBackgroundResource(R.color.colorTealDark);


        }


    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> mListTask;


        public TaskAdapter(List<Task> listTask) {
            mListTask = listTask;
        }

        public List<Task> getListTask() {
            return mListTask;
        }

        public void setListTask(List<Task> listTask) {
            mListTask = listTask;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.task_row_list, parent, false);
            TaskHolder taskHolder = new TaskHolder(view);
            return taskHolder;
        }


        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = mListTask.get(position);
            holder.bindTask(task, position);
        }

        @Override
        public int getItemCount() {
            return mListTask.size();
        }


    }

}