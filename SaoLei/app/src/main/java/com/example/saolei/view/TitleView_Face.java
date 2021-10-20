package com.example.saolei.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.saolei.R;

public class TitleView_Face extends androidx.appcompat.widget.AppCompatImageView {
    public TitleView_Face(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public int gameState;

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        this.setImageResource(getImage());
        super.onDraw(canvas);

    }

    private int getImage() {
        if (gameState == 0) {
            return R.drawable.go;
        }
        if (gameState == 1) {
            return R.drawable.gw;
        }
        if (gameState == 2) {
            return R.drawable.gf;
        }
        return R.drawable.go;
    }
}
