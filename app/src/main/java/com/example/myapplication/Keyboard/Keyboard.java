package com.example.myapplication.Keyboard;

import static com.google.android.flexbox.JustifyContent.CENTER;

import android.content.Context;
import android.util.Size;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.Adapter.KeyboardAdapter;
import com.example.myapplication.R;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class Keyboard {

    private final RecyclerView keyboard;
    private final List<Key> keys;
    @Getter private KeyboardAdapter keyboardAdapter;
    @Setter private View.OnClickListener onKeyClickListener;

    public Keyboard(RecyclerView keyboard, List<Key> keys) {
        this.keys = keys;
        this.keyboard = keyboard;
    }

    public void create(Context context, View view) {
        keyboardAdapter = new KeyboardAdapter(keys);
        if (onKeyClickListener != null) keyboardAdapter.setOnClickListener(onKeyClickListener);

        keyboard.setAdapter(keyboardAdapter);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(context);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setJustifyContent(CENTER);
        keyboard.setLayoutManager(layoutManager);
    }

    @Data
    public static class Key {
        String keyText;
        Size size;

        public Key(String keyText, Size size){
            this.keyText = keyText;
            this.size = size;
        }

        public Key(String keyText, int width, int height) {
            this.keyText = keyText;
            this.size = new Size(width, height);
        }
    }
}
