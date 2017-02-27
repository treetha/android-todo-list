package com.workshop.todo.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.workshop.todo.todo.interactor.LoginInteractor;

public class MainActivity extends AppCompatActivity implements LoginInteractor.LoginListener {

    private Button btnLogin;
    private EditText edtUsername;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = (EditText) findViewById(R.id.edt_user_name);
        edtPassword = (EditText) findViewById(R.id.edt_password);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLogin();

            }
        });
    }

    private void callLogin() {
        LoginInteractor loginInteractor = new LoginInteractor();
        loginInteractor.setListener(this);
        loginInteractor.login(edtUsername.getText().toString(),
                edtPassword.getText().toString());
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        if( "pass".equals(loginResult.getResult())) {
            Intent intent = new Intent(getApplicationContext(), TodoListActivity.class);
            startActivity(intent);
        }
    }
}
