package com.example.saolei.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.saolei.CreatMap.Units;

public class MyAdapter extends BaseAdapter implements AdapterView.OnItemClickListener ,AdapterView.OnItemLongClickListener{

    public MyAdapter(Context context, Units units,int size) {
        this.context=context;
        this.units=units;
        this.size=size;
    }
    int size;
    Units units;
    Context context;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UnitView unitView = new UnitView(context);
        unitView.setScaleType(ImageView.ScaleType.FIT_XY);
        unitView.setLayoutParams(new ViewGroup.LayoutParams(size,size));
        unitView.setUnit(units.getUnit(position / units.width, position % units.width));
        return unitView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        units.click(position / units.width, position % units.width);
        System.out.println("ItemClick"+position);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        units.show(position / units.width, position % units.width);
        System.out.println("ItemClick"+position);
        return true;
    }
}
