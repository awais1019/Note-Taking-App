package com.example.notetakingappone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RvAdaptor extends RecyclerView.Adapter<RvAdaptor.ViewHolder> {
    Context context;
    ArrayList<Note>notes;

    public RvAdaptor(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.display_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(notes.get(position).getTitle());
        holder.content.setText(notes.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,content;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title=itemView.findViewById(R.id.note_title);
            content=itemView.findViewById(R.id.note_content);
        }
    }
}

