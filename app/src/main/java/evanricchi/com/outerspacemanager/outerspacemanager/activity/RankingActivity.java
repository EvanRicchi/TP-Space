package evanricchi.com.outerspacemanager.outerspacemanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.List;

import evanricchi.com.outerspacemanager.outerspacemanager.R;
import evanricchi.com.outerspacemanager.outerspacemanager.adapter.RankingAdapter;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiService;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiUser;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ListUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RankingActivity extends AppCompatActivity {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager-staging.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ApiService service = retrofit.create(ApiService.class);
    private String Token;
    private ListView ListRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        ListRank = (ListView) findViewById(R.id.listRank);
        Token = getIntent().getStringExtra("TOKEN");
        loadRank();
    }

    private void loadRank() {
        Call<ListUser> rank = service.getRank(Token, 0, 20);
        rank.enqueue(new Callback<ListUser>() {
            @Override
            public void onResponse(Call<ListUser> call, Response<ListUser> response) {
                ListUser responseRank = response.body();
                List<ApiUser> users = responseRank.getRank();
                RankingAdapter adapter = new RankingAdapter(RankingActivity.this, users);
                ListRank.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ListUser> call, Throwable t) {

            }
        });
    }
}
