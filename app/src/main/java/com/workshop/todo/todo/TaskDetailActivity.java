package com.workshop.todo.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent intent = getIntent();
        TextView tvTaskDescription = (TextView)findViewById(R.id.task_description);
        tvTaskDescription.setText(intent.getStringExtra("task_description"));
    }
}
