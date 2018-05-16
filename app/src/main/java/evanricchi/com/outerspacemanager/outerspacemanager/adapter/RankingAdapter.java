package evanricchi.com.outerspacemanager.outerspacemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

import evanricchi.com.outerspacemanager.outerspacemanager.R;
import evanricchi.com.outerspacemanager.outerspacemanager.models.ApiUser;

public class RankingAdapter extends ArrayAdapter<ApiUser> {
    public RankingAdapter(Context context, List<ApiUser> rank) {
        super(context, 0, rank);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_rank,parent,false);
        }

        RankingAdapter.RankViewHolder viewHolder = (RankingAdapter.RankViewHolder) convertView.getTag();

        if(viewHolder == null){
            viewHolder = new RankViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.points = (TextView) convertView.findViewById(R.id.points);
            viewHolder.rank = (TextView) convertView.findViewById(R.id.rank);
            convertView.setTag(viewHolder);
        }

        ApiUser user = getItem(position);

        viewHolder.name.setText("Nom : " + user.getUser());
        viewHolder.points.setText("Points : " + user.getPoints());
        viewHolder.rank.setText("Rang : " + Integer.toString(position+1));

        return convertView;
    }

    private class RankViewHolder {
        public TextView name;
        public TextView points;
        public TextView rank;
    }
}