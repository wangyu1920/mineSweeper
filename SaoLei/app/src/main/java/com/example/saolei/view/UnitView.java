package com.example.saolei.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;

import androidx.appcompat.widget.AppCompatImageView;

import com.example.saolei.CreatMap.Unit;
import com.example.saolei.R;

public class UnitView extends AppCompatImageView {
    public UnitView(Context context) {
        super(context);
    }
    Unit unit;

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {

        if (unit.isTheFirstMine) {
            setImageResource(R.drawable.u9f);
            super.onDraw(canvas);
            return;
        }
        if (!unit.isShow()) {//还没有展现的格子,根据有没有被标记设置图形
            if (unit.isMark()) {
                setImageResource(R.drawable.um);
            } else {
                setImageResource(R.drawable.unm);
            }
        } else {//已经展现的格子,根据数字设置图形
            switch (unit.getNum()) {
                case 0:setImageResource(R.drawable.u0);break;
                case 1:setImageResource(R.drawable.u1);break;
                case 2:setImageResource(R.drawable.u2);break;
                case 3:setImageResource(R.drawable.u3);break;
                case 4:setImageResource(R.drawable.u4);break;
                case 5:setImageResource(R.drawable.u5);break;
                case 6:setImageResource(R.drawable.u6);break;
                case 7:setImageResource(R.drawable.u7);break;
                case 8:setImageResource(R.drawable.u8);break;
                default:setImageResource(R.drawable.u9);break;
            }
        }
        super.onDraw(canvas);
    }
}
