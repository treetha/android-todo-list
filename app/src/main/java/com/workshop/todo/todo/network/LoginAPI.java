package com.workshop.todo.todo.network;

import com.workshop.todo.todo.LoginResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoginAPI {

    @GET("login/{username}/{password}")
    Call<LoginResult> login(@Path("username") String username,
                            @Path("password") String password);

}
