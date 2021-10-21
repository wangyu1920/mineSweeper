package com.example.saolei.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saolei.CreatMap.Unit;
import com.example.saolei.CreatMap.Units;

//高度耦合的类
public class MyAdapter extends BaseAdapter implements AdapterView.OnItemClickListener ,AdapterView.OnItemLongClickListener{

    //基于Units类设置的Adapter,加入了size属性来控制图标大小（size既是长度也是宽度），加入了TheView_Face
    //来为Listener设置效果
     public MyAdapter(Context context, Units units,int size,TitleView_Face view_face,TextView mineLastedNum,int mineNum) {
        this.context=context;
        this.units=units;
        this.size=size;
        this.view_face=view_face;
        this.mineLastedNum = mineLastedNum;
        this.mineNum = mineNum;
        mineLastedNum.setText(String.valueOf(mineNum-units.markedUnits()));
    }
    int size;
    Units units;
    Context context;
    TitleView_Face view_face;
    TextView mineLastedNum;
    int mineNum;
    @Override
    public int getCount() {
        return units.height*units.width;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //自动根据position生成相应的unitView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UnitView unitView = new UnitView(context);
        //设置图片伸缩类型
        unitView.setScaleType(ImageView.ScaleType.FIT_XY);
        //设置大小
        unitView.setLayoutParams(new ViewGroup.LayoutParams(size,size));
        unitView.setUnit(units.getUnit(position / units.width, position % units.width));
        return unitView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (view_face.gameState != 0) {
            return;
        }
        Object i = units.click(position / units.width, position % units.width);
        if (i instanceof Unit) {
            ((Unit) i).isTheFirstMine=true;
            view_face.gameState=2;
            units.showAllMines();
        }
        int mineLasted=mineNum - units.markedUnits();
        mineLastedNum.setText(String.valueOf(mineLasted));
        if (mineLasted == 0 && units.correctMarkedUnits() == mineNum) {
            view_face.gameState=1;
        }
        System.out.println("ItemClick"+position);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if (view_face.gameState != 0) {
            return true;
        }
        if (!units.show(position / units.width, position % units.width)) {
            Unit unit = units.getUnit(position / units.width, position % units.width);
            unit.isTheFirstMine = true;
            view_face.gameState = 2;
            units.showAllMines();
        }
        int mineLasted=mineNum - units.markedUnits();
        mineLastedNum.setText(String.valueOf(mineLasted));
        if (mineLasted == 0 && units.correctMarkedUnits() == mineNum) {
            view_face.gameState=1;
        }
        System.out.println("ItemClick"+position);
        return true;
    }
}
