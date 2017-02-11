package com.gmail.senokt16.stirhack2017;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.ViewHolder> {

    private List<Language> list;

    public LanguagesAdapter() {
        list = new ArrayList<>();
    }

    public LanguagesAdapter(List<Language> languages) {
        list = languages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(0, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
