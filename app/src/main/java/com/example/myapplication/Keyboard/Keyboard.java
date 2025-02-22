package com.example.myapplication.Keyboard;

import android.content.Context;
import android.util.Size;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.KeyboardAdapter;
import com.example.myapplication.R;

import java.util.List;

import lombok.Data;

public class Keyboard {

    List<Key> keys;

    public Keyboard(List<Key> keys) {
        this.keys = keys;
    }

    public void create(Context context, View view) {
        RecyclerView keyboard = view.findViewById(R.id.keyboard);
        KeyboardAdapter adapter = new KeyboardAdapter(keys);

        adapter.setOnClickListener(view1 ->{

        });

        keyboard.setAdapter(adapter);
        keyboard.setLayoutManager(new GridLayoutManager(context, 12));
    }

    @Data
    public static class Key {
        String keyText;
        Size size;

        public Key(String keyText, Size size){
            this.keyText = keyText;
            this.size = size;
        }
    }
}
