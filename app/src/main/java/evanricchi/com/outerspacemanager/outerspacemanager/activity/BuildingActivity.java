package evanricchi.com.outerspacemanager.outerspacemanager.activity;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import evanricchi.com.outerspacemanager.outerspacemanager.R;
import evanricchi.com.outerspacemanager.outerspacemanager.fragment.BuildDetailFragmentActivity;
import evanricchi.com.outerspacemanager.outerspacemanager.fragment.BuildListFragmentActivity;

public class BuildingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        BuildListFragmentActivity nextFrag = new BuildListFragmentActivity();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, nextFrag);
        ft.commit();

        Token = getIntent().getStringExtra("TOKEN");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putInt("ID", position);
        bundle.putString("TOKEN",Token);

        BuildDetailFragmentActivity nextFrag = new BuildDetailFragmentActivity();
        nextFrag.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, nextFrag);
        ft.commit();

    }
}

