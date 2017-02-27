package com.workshop.todo.todo.network;

import com.workshop.todo.todo.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TaskAPI {

    @GET("tasks")
    Call<List<Task>> listAllTask();

}
