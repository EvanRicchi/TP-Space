package evanricchi.com.outerspacemanager.outerspacemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

import evanricchi.com.outerspacemanager.outerspacemanager.R;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiFleet;

public class FleetAdapter extends ArrayAdapter<ApiFleet> {
    public FleetAdapter(Context context, List<ApiFleet> fleets) {
        super(context,0, fleets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_fleet,parent,false);
        }

        FleetAdapter.FleetViewHolder viewHolder = (FleetAdapter.FleetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new FleetAdapter.FleetViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.life = (TextView) convertView.findViewById(R.id.life);
            viewHolder.speed = (TextView) convertView.findViewById(R.id.speed);
            viewHolder.amount = (TextView) convertView.findViewById(R.id.amount);
            convertView.setTag(viewHolder);
        }

        ApiFleet fleet = getItem(position);
        viewHolder.name.setText("Nom : " + String.valueOf(fleet.getName()));
        viewHolder.life.setText("Vie : " + String.valueOf(fleet.getLife()));
        viewHolder.speed.setText("Vitesse : " + String.valueOf(fleet.getSpeed()));
        viewHolder.amount.setText("Quantité : " + String.valueOf(fleet.getAmount()));

        return convertView;
    }

    private class FleetViewHolder {
        public TextView name;
        public TextView life;
        public TextView speed;
        public TextView amount;
    }
}

