package com.workshop.todo.todo.interactor;

import com.workshop.todo.todo.Task;
import com.workshop.todo.todo.network.TaskAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskInteractor {

    private Retrofit retrofit;

    public void listAllTask() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8882/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TaskAPI taskAPI = retrofit.create(TaskAPI.class);
        final Call<List<Task>> call = taskAPI.listAllTask();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                //TODO
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                //TODO
            }
        });
    }

}
