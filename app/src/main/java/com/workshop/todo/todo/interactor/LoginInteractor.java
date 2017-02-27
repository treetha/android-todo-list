package com.workshop.todo.todo.interactor;

import android.util.Log;

import com.workshop.todo.todo.LoginResult;
import com.workshop.todo.todo.Task;
import com.workshop.todo.todo.network.LoginAPI;
import com.workshop.todo.todo.network.TaskAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginInteractor extends BaseInteractor {

    private static final String TAG = LoginInteractor.class.getName();

    //Step 1
    public interface LoginListener {
        void onSuccess(LoginResult loginResult);
    }

    private Retrofit retrofit;

    //Step 2
    private LoginListener listener;
    public void setListener(LoginListener listener) {
        this.listener = listener;
    }

    public void login(String username, String password) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginAPI loginAPI = retrofit.create(LoginAPI.class);
        final Call<LoginResult> call = loginAPI.login(username, password);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {

            }
        });
    }
}
