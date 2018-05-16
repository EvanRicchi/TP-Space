package evanricchi.com.outerspacemanager.outerspacemanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

import evanricchi.com.outerspacemanager.outerspacemanager.R;
import evanricchi.com.outerspacemanager.outerspacemanager.adapter.FleetAdapter;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiFleet;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiService;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ListFleet;

public class FleetActivity extends AppCompatActivity {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager-staging.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ApiService service = retrofit.create(ApiService.class);
    private String Token;
    private ListView ListFleet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet);
        ListFleet = (ListView) findViewById(R.id.listFleet);
        Token = getIntent().getStringExtra("TOKEN");
        loadFleet();
    }

    private void loadFleet() {
        final Call<ListFleet> fleet = service.getFleet(Token);
        fleet.enqueue(new Callback<ListFleet>() {
            @Override
            public void onResponse(Call<ListFleet> call, Response<ListFleet> response) {
                ListFleet listFleet = response.body();
                List<ApiFleet> fleets = listFleet.getFleets();
                FleetAdapter adapter = new FleetAdapter(FleetActivity.this, fleets);
                ListFleet.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ListFleet> call, Throwable t) {

            }
        });
    }
}
