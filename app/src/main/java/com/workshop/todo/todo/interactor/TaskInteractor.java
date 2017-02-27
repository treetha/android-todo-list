package com.workshop.todo.todo.interactor;

import android.util.Log;

import com.workshop.todo.todo.Task;
import com.workshop.todo.todo.TodoListActivity;
import com.workshop.todo.todo.network.TaskAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskInteractor {

    private static final String TAG = TaskInteractor.class.getName();
    public static String BASE_URL = "http://172.21.14.38:8882/";

    //Step 1
    public interface TodoListListener {
        void onSuccess(List<Task> tasks);
    }

    private Retrofit retrofit;

    //Step 2
    private TodoListListener listener;
    public void setListener(TodoListListener listener) {
        this.listener = listener;
    }

    public void listAllTask() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TaskAPI taskAPI = retrofit.create(TaskAPI.class);
        final Call<List<Task>> call = taskAPI.listAllTask();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                //TODO
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                //Step 3
                if(response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                //TODO
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }
}
