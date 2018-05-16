package evanricchi.com.outerspacemanager.outerspacemanager.fragment;

import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import evanricchi.com.outerspacemanager.outerspacemanager.R;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiBuilding;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildDetailFragmentActivity extends Fragment {
    private int buildId;
    private String Token;
    private TextView information;
    private TextView level;
    private TextView effect;
    private ImageView logo;
    private TextView name;
    private TextView gasCostByLevel;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager-staging.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ApiService service = retrofit.create(ApiService.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        buildId = this.getArguments().getInt("ID");
        Token = this.getArguments().getString("TOKEN");
        View view = inflater.inflate(R.layout.activity_detail_building,container,false);

        name = (TextView) view.findViewById(R.id.textView_name);
        information = (TextView) view.findViewById(R.id.textView_information);
        level = (TextView) view.findViewById(R.id.textView_level);
        effect = (TextView) view.findViewById(R.id.textView_effect);
        logo = (ImageView) view.findViewById(R.id.imageView_logo);

        loadBuild();

        return view;
    }

    private void loadBuild() {
        final Call<ApiBuilding> build = service.getBuildingById(Token,buildId);
        build.enqueue(new Callback<ApiBuilding>() {
            @Override
            public void onResponse(Call<ApiBuilding> call, Response<ApiBuilding> response) {

                name.setText("Nom : " + response.body().getName());
                information.setText("Information : " + String.valueOf(response.body().getBuildingId()));
                level.setText("Niveau : " + String.valueOf(response.body().getLevel()));
                effect.setText("Effet : " + String.valueOf(response.body().getEffect()));
            }

            @Override
            public void onFailure(Call<ApiBuilding> call, Throwable t) {

            }
        });

    }
}
