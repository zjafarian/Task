package com.example.task.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task.R;
import com.example.task.model.ColorTask;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;

import java.util.List;

import static com.example.task.model.ColorTask.TealDark;
import static com.example.task.model.ColorTask.TealLight;


public class ListTaskFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private String mName;
    private int mNumber;
    private TaskRepository mTaskRepository;


    public ListTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TaskRepository.setSizeTaskAndNameTask(mNumber, mName);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_task, container, false);
        findView(view);
        initViews();
        return view;
    }


    private void findView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_task_list);
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<Task> tasks = mTaskRepository.getTaskList();
        TaskAdapter taskAdapter = new TaskAdapter(tasks);
        mRecyclerView.setAdapter(taskAdapter);
    }

    private class TaskHolder extends RecyclerView.ViewHolder {
        private ImageView mImageTodo;
        private ImageView mImageDoing;
        private ImageView mImageDone;
        private TextView mTextVieWTaskName;
        private TextView mTextViewState;
        private Task mTask;

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
        }

        public void bindTask(Task task) {
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
        }


    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> mListTask;
        private CardView mCardView;
        private ImageButton mImgButtonAddTask;

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
            setViewById(view);
            setListener();
            TaskHolder taskHolder = new TaskHolder(view);
            return taskHolder;
        }

        private void setListener() {
            mImgButtonAddTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Task task = new Task(mName, mTaskRepository.generateRandomState());
                    mTaskRepository.insertTask(task);
                    mListTask = mTaskRepository.getTaskList();
                }
            });
        }

        private void setViewById(View view) {
            mCardView = view.findViewById(R.id.card_view);
            mImgButtonAddTask = view.findViewById(R.id.image_btn_add_task);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = mListTask.get(position);
            holder.bindTask(task);
            if (position % 2 == 0) {
                mCardView.setBackgroundResource(R.color.colorTealLight);
            } else  mCardView.setBackgroundResource(R.color.colorTealDark);
        }

        @Override
        public int getItemCount() {
            return mListTask.size();
        }


    }

}