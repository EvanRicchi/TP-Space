package evanricchi.com.outerspacemanager.outerspacemanager.fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.List;
import evanricchi.com.outerspacemanager.outerspacemanager.R;
import evanricchi.com.outerspacemanager.outerspacemanager.activity.BuildingActivity;
import evanricchi.com.outerspacemanager.outerspacemanager.adapter.BuildingAdapter;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiBuilding;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiService;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ListBuilding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildListFragmentActivity extends Fragment {
    private ListView listBuild;
    private String Token;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager-staging.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ApiService service = retrofit.create(ApiService.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_list_building,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listBuild = (ListView) view.findViewById(R.id.ListView_buildings);
        Token = getActivity().getIntent().getExtras().getString("TOKEN");
        loadBuild();
        listBuild.setOnItemClickListener((BuildingActivity)getActivity());
    }

    private void loadBuild() {
        final Call<ListBuilding> buildings = service.getBuilding(Token);
        buildings.enqueue(new Callback<ListBuilding>() {
            @Override
            public void onResponse(Call<ListBuilding> call, Response<ListBuilding> response) {
                ListBuilding responseBuild = response.body();
                List<ApiBuilding> builds = responseBuild.getBuilding();
                BuildingAdapter adapter = new BuildingAdapter(getActivity(), builds);
                listBuild.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ListBuilding> call, Throwable t) {

            }
        });
    }
}
