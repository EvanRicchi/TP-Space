package evanricchi.com.outerspacemanager.outerspacemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiService;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiUser;
import evanricchi.com.outerspacemanager.outerspacemanager.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String Token;
    private TextView textView_username;
    private TextView textView_points_value;
    private TextView button_view;
    private TextView button_buildings;
    private TextView button_ranking;
    private TextView button_shipping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager-staging.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);

        Token = getIntent().getStringExtra("TOKEN");
        textView_username = (TextView) findViewById(R.id.textView_username);
        textView_points_value = (TextView) findViewById(R.id.textView_points_value);
        button_view = (TextView) findViewById(R.id.button_view);
        button_buildings = (TextView) findViewById(R.id.button_buildings);
        button_ranking = (TextView) findViewById(R.id.button_ranking);
        button_shipping = (TextView) findViewById(R.id.button_shipping);

        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), ViewActivity.class);
                myIntent.putExtra("TOKEN", Token);
                startActivity(myIntent);
            }
        });

        button_buildings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), BuildingActivity.class);
                myIntent.putExtra("TOKEN", Token);
                startActivity(myIntent);
            }
        });

        button_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), RankingActivity.class);
                myIntent.putExtra("TOKEN", Token);
                startActivity(myIntent);
            }
        });

        button_shipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), FleetActivity.class);
                myIntent.putExtra("TOKEN", Token);
                startActivity(myIntent);
            }
        });

        setup(Token,service);
    }

    public void setup(String token, ApiService api) {
        Call<ApiUser> user = api.currentuser(token);
        user.enqueue(new Callback<ApiUser>() {
            @Override
            public void onResponse(Call<ApiUser> call, Response<ApiUser> response) {
                textView_username.setText(response.body().getUser().toString());
                textView_points_value.setText(Integer.toString(response.body().getPoints()));
            }

            @Override
            public void onFailure(Call<ApiUser> call, Throwable t) {

            }
        });

    }
}
