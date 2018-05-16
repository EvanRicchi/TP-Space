package evanricchi.com.outerspacemanager.outerspacemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import evanricchi.com.outerspacemanager.outerspacemanager.R;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiBuilding;

public class BuildingAdapter extends ArrayAdapter<ApiBuilding> {

    public BuildingAdapter(Context context, List<ApiBuilding> buildings) {
        super(context, 0, buildings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_building,parent,false);
        }

        BuildingViewHolder viewHolder = (BuildingViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new BuildingViewHolder();
            viewHolder.information = (TextView) convertView.findViewById(R.id.textView_information);
            viewHolder.level = (TextView) convertView.findViewById(R.id.textView_level);
            viewHolder.effect = (TextView) convertView.findViewById(R.id.textView_effect);
            viewHolder.logo = (ImageView) convertView.findViewById(R.id.imageView_logo);
            viewHolder.name = (TextView) convertView.findViewById(R.id.textView_name);
            convertView.setTag(viewHolder);
        }

        ApiBuilding building = getItem(position);

        viewHolder.information.setText("Id : "+ String.valueOf(building.getBuildingId()));
        if(building.getLevel() == null){
            viewHolder.level.setText("Niveau supérieur !");
        } else {
            viewHolder.level.setText("Niveau :  "+ String.valueOf(building.getLevel()));
        }
        viewHolder.effect.setText("Effet : "+ building.getEffect());
        viewHolder.name.setText(building.getName());
        Picasso.get().load(building.getImageUrl()).into(viewHolder.logo);
        return convertView;
    }

    private class BuildingViewHolder{
        public TextView information;
        public TextView level;
        public TextView effect;
        public ImageView logo;
        public TextView name;
    }
}