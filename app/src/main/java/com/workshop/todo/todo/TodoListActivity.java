package com.workshop.todo.todo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    private ListView taskListView;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 01"));
        tasks.add(new Task("Task 02"));
        tasks.add(new Task("Task 03"));

        taskListView = (ListView) findViewById(R.id.list_todo);
        taskAdapter = new TaskAdapter(this, tasks);
        taskListView.setAdapter(taskAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildAndShowInputDialog();
            }
        });

    }

    private void buildAndShowInputDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create a new Task");

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View dialogView = layoutInflater.inflate(R.layout.todo_create_view, null);
        final EditText input = (EditText) dialogView.findViewById(R.id.input);

        builder.setView(dialogView);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addNewTask(input.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final AlertDialog dialog = builder.show();

    }

    private void addNewTask(String taskDescription) {
        if (taskDescription == null || taskDescription.length() == 0) {
            Toast
                    .makeText(this, "Empty task!", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        taskAdapter.addAll(new Task(taskDescription));
        taskAdapter.notifyDataSetChanged();
    }
}
