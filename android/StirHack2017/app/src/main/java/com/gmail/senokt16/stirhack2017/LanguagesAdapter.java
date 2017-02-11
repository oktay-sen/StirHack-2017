package com.gmail.senokt16.stirhack2017;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.ViewHolder> {

    private List<Language> list;
    private Context context;

    public LanguagesAdapter(List<Language> languages, Context context) {
        list = languages;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_language, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        List<String> langs = Arrays.asList(context.getResources().getStringArray(R.array.languages));
        Language l;
        if (list.get(position) == null) {
            l = new Language();
            l.name = langs.get(0);
            l.level = 0;
            list.set(position, l);
        } else {
            l = list.get(position);
        }


        holder.language.setSelection(langs.indexOf(l.name));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.spinner_item_2, context.getResources().getStringArray(R.array.languages));
        holder.language.setAdapter(adapter);
        holder.level.setMax(2);
        holder.level.setProgress(l.level);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(holder.getAdapterPosition());
                LanguagesAdapter.this.notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        SeekBar level;
        Spinner language;
        ImageButton delete;
        public ViewHolder(View itemView) {
            super(itemView);
            language = (Spinner) itemView.findViewById(R.id.language);
            level = (SeekBar) itemView.findViewById(R.id.level);
            delete = (ImageButton) itemView.findViewById(R.id.delete);
        }
    }
}
