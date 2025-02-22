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
    @Setter View.OnClickListener onClickListener;

    public KeyboardAdapter(List<Keyboard.Key> keys) {
        this.keys = keys;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 13) return 1;
        if (position < 25) return 2;
        if (position < 33) return 3;
        return 0;
    }

    @NonNull
    @Override
    public KeyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.key_item, parent, false);
        ViewGroup.LayoutParams params = itemView.getLayoutParams();

        switch (viewType) {
            case 1:
            case 3:
                params.width = parent.getMeasuredWidth()/12;
                break;
            case 2:
                params.width = parent.getMeasuredWidth()/11;
                break;
        }

        return new KeyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KeyViewHolder holder, int position) {
        Keyboard.Key key = keys.get(position);

        holder.bind(key);

        holder.keyBtn.setOnClickListener(view -> {
            if(onClickListener != null) onClickListener.onClick(view);
        });
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

    public static class KeyViewHolder extends RecyclerView.ViewHolder {
        Button keyBtn;

        public KeyViewHolder(@NonNull View itemView) {
            super(itemView);
            keyBtn = itemView.findViewById(R.id.key);
        }

        public void bind(Keyboard.Key key) {
            keyBtn.setText(key.getKeyText());
        }
    }
}
