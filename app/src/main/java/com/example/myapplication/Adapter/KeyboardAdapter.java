package com.example.myapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Keyboard.Keyboard;
import com.example.myapplication.R;

import java.util.List;

import lombok.Setter;

public class KeyboardAdapter extends RecyclerView.Adapter<KeyboardAdapter.KeyViewHolder> {

    List<Keyboard.Key> keys;
    @Setter
    View.OnClickListener onClickListener;

    public KeyboardAdapter(List<Keyboard.Key> keys) {
        this.keys = keys;
    }

    @NonNull
    @Override
    public KeyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.key_item, parent, false);
        return new KeyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KeyViewHolder holder, int position) {
        Keyboard.Key key = keys.get(position);

        holder.keyBtn.setText(key.getKeyText());
        holder.keyBtn.setWidth(key.getSize().getWidth());
        holder.keyBtn.setHeight(key.getSize().getHeight());

        holder.keyBtn.setOnClickListener(view -> {
            if(onClickListener != null) onClickListener.onClick(view);
        });
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

    static class KeyViewHolder extends RecyclerView.ViewHolder {
        Button keyBtn;

        public KeyViewHolder(@NonNull View itemView) {
            super(itemView);
            keyBtn = itemView.findViewById(R.id.button);
        }
    }
}
