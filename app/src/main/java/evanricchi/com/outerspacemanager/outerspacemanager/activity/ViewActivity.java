package evanricchi.com.outerspacemanager.outerspacemanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiService;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiUser;
import evanricchi.com.outerspacemanager.outerspacemanager.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewActivity extends AppCompatActivity {
    private String Token;
    private TextView textView_gas;
    private TextView textView_gasModifier;
    private TextView textView_minerals;
    private TextView textView_mineralsModifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager-staging.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);

        Token = getIntent().getStringExtra("TOKEN");
        textView_gas = (TextView) findViewById(R.id.textView_gas);
        textView_gasModifier = (TextView) findViewById(R.id.textView_gasModifier);
        textView_minerals = (TextView) findViewById(R.id.textView_minerals);
        textView_mineralsModifier = (TextView) findViewById(R.id.textView_mineralsModifier);

        setup(Token,service);
    }

    public void setup(String token, ApiService api) {
        Call<ApiUser> user = api.currentuser(token);
        user.enqueue(new Callback<ApiUser>() {
            @Override
            public void onResponse(Call<ApiUser> call, Response<ApiUser> response) {
                textView_gas.setText("Gaz : ".concat(Integer.toString(response.body().getGas())));
                textView_gasModifier.setText("Gaz modificateur : ".concat(response.body().getGasModifier().toString()));
                textView_minerals.setText("Minéraux : ".concat(Integer.toString(response.body().getMinerals())));
                textView_mineralsModifier.setText("Minéraux modificateur : ".concat(response.body().getMineralsModifier().toString()));
            }

            @Override
            public void onFailure(Call<ApiUser> call, Throwable t) {

            }
        });
    }
}
