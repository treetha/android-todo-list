package com.workshop.todo.todo;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private static class ViewHolder {
        TextView taskDescription;
    }

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, R.layout.item_task, tasks);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Task currentTask = getItem(position);
        final ViewHolder viewHolder;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_task, parent, false);
            viewHolder.taskDescription = (TextView) convertView.findViewById(R.id.task_description);
            convertView.setTag(viewHolder);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), TaskDetailActivity.class);
                    intent.putExtra("task_description", viewHolder.taskDescription.getText().toString());
                    getContext().startActivity(intent);
                }
            });
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.taskDescription.setText(currentTask.getTaskDescription());
        return convertView;
    }
}
