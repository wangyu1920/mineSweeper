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

//高度耦合的类，继承BaseAdapter,实现了两个监听器接口，用于在GridView中显示整个地图，并完成对手势操作的监听和响应，联动相应View做出反应。
public class MyAdapter extends BaseAdapter implements AdapterView.OnItemClickListener ,AdapterView.OnItemLongClickListener{

    /**构造方法：
     * 基于Units类设置的Adapter,加入了size属性来控制图标大小（size既是长度也是宽度），加入了TheView_Face
     * 来为Listener设置效果，mineNum来判断是否扫完了雷。
     * @param context 上下文
     * @param units 全部格子数据
     * @param size 格子边长
     * @param view_face 根据游戏进度会变的笑脸
     * @param mineLastedNum 显示剩下雷数的TextView
     * @param mineNum 雷的数量
     */
     public MyAdapter(Context context, Units units,int size,TitleView_Face view_face,TextView mineLastedNum,int mineNum) {
        this.context=context;
        this.units=units;
        this.size=size;
        this.view_face=view_face;
        this.mineLastedNum = mineLastedNum;
        this.mineNum = mineNum;
        mineLastedNum.setText(String.valueOf(mineNum-units.markedUnits()));
    }

    //实例变量列表
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

    //点击监听器的实现
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //游戏状态为胜利或失败状态则无法点击
        if (view_face.gameState != 0) {
            return;
        }
        //调用Units类的方法，判断是否触雷，并作出相应反应
        Object i = units.click(position / units.width, position % units.width);
        if (i instanceof Unit) {//触雷，标记触碰到的雷（将显示为红色），改变游戏状态为输，展示所有的雷
            ((Unit) i).isTheFirstMine = true;
            view_face.gameState = 2;
            units.showAllMines();
        }
        //没有触雷，游戏继续进行，剩余雷数刷新
        int mineLasted = mineNum - units.markedUnits();
        mineLastedNum.setText(String.valueOf(mineLasted));
        //判断是否已经找到所有雷，找到则游戏状态设置为胜利
        if (mineLasted == 0 && units.correctMarkedUnitsAndNotShowUnits()[0] == mineNum && units.correctMarkedUnitsAndNotShowUnits()[1] == mineNum) {
            view_face.gameState = 1;
        }
        System.out.println("ItemClick" + position);
    }

    //长按监听器的实现，与点击监听器类似，不再注释。
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
        if (mineLasted == 0 && units.correctMarkedUnitsAndNotShowUnits()[0] == mineNum&& units.correctMarkedUnitsAndNotShowUnits()[1] == mineNum) {
            view_face.gameState=1;
        }
        System.out.println("ItemClick"+position);
        return true;
    }
}
