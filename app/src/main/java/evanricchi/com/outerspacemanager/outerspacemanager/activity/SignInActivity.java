package evanricchi.com.outerspacemanager.outerspacemanager.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Context;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiService;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiToken;
import evanricchi.com.outerspacemanager.outerspacemanager.R;
import evanricchi.com.outerspacemanager.outerspacemanager.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {
    private EditText iden;
    private EditText mdp;
    private Button connect;
    private Button createAccount;
    SharedPreferences save;
    private String Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        save = getSharedPreferences("PREFS", MODE_PRIVATE);
        Token = loadtoken();

        iden = (EditText) findViewById(R.id.editText_iden);
        mdp = (EditText) findViewById(R.id.editText_mdp);
        connect = (Button) findViewById(R.id.button_connect);
        createAccount = (Button) findViewById(R.id.button_createAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(myIntent);
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connect(v);
            }
        });
    }

    private void Connect(View v) {
        User user = new User(iden.getText().toString(),mdp.getText().toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager-staging.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<ApiToken> repo = service.login(user);
        repo.enqueue(new Callback<ApiToken>() {
            @Override
            public void onResponse(Call<ApiToken> call, Response<ApiToken> response) {
                if (response.isSuccessful()) {
                    Log.e("token",response.body().getToken());
                    savetoken(response.body().getToken());
                    Token = response.body().getToken();
                    Log.e("OK", "onResponse: ok");
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("TOKEN", Token);
                    startActivity(intent);

                } else {
                    Log.e("erreur",response.toString());
                    toast();
                }
            }

            @Override
            public void onFailure(Call<ApiToken> call, Throwable t) {
            }
        });
    }

    private void toast() {
        Context context = getApplicationContext();
        CharSequence text = "Username ou mot de passe faux !";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private String loadtoken() {
        String token = save.getString("token", null);
        return token;
    }

    private void savetoken(String token) {
        SharedPreferences.Editor editor = save.edit();
        editor.putString("token", token);
        editor.commit();
    }
}