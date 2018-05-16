package evanricchi.com.outerspacemanager.outerspacemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiService;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiToken;
import evanricchi.com.outerspacemanager.outerspacemanager.R;
import evanricchi.com.outerspacemanager.outerspacemanager.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignUpActivity extends AppCompatActivity {
    private EditText username;
    private EditText mail;
    private EditText password;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.editText_username);
        mail = (EditText) findViewById(R.id.editText_mail);
        password = (EditText) findViewById(R.id.editText_password);
        signup = (Button) findViewById(R.id.button_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connect(v);
                Intent myIntent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void Connect(View v) {
        User user = new User(mail.getText().toString(),username.getText().toString(),password.getText().toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager-staging.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService Create_User = retrofit.create(ApiService.class);
        Call<ApiToken> repo = Create_User.createUser(user);
        repo.enqueue(new Callback<ApiToken>() {
            @Override
            public void onResponse(Call<ApiToken> call, Response<ApiToken> response) {
                if (response.isSuccessful()) {
                    Log.e("token",response.body().getToken());
                    Log.e("OK", "onResponse: ok");
                } else {
                    Log.e("erreur",response.toString());
                }
            }

            @Override
            public void onFailure(Call<ApiToken> call, Throwable t) {
            }
        });
    }
}
