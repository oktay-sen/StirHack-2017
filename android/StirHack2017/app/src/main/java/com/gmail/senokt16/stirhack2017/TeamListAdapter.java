package com.gmail.senokt16.stirhack2017;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.ViewHolder>{

    List<Team> list;

    public TeamListAdapter(List<Team> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_team_list, parent, false);
        return new TeamListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(list.get(position).name);
        if (list.get(position).description != null && list.get(position).description.length() > 0)
            holder.description.setText(list.get(position).description);
        else
            holder.description.setVisibility(View.GONE);
        if (list.get(position).languages.length > 0) {
            String language = list.get(position).languages[0];
            for (int i = 1; i < list.get(position).languages.length; i++) {
                language += ", " + list.get(position).languages[i];
            }
            holder.languages.setText(language);
        } else {
            holder.languages.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, languages, description;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            languages = (TextView) itemView.findViewById(R.id.languages);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
