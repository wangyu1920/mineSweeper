package com.example.saolei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.saolei.CreatMap.Units;
import com.example.saolei.view.MyAdapter;
import com.example.saolei.view.TitleView_Face;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    //控件列表
    TitleView_Face titleView_face;
    EditText editText_height;
    EditText editText_width;
    EditText editText_mineNum;
    EditText editText_mapSize;
    TextView textView_mineLastNum;
    GridView gridView;
    //地图参数
    int height;
    int width;
    int mineNum;
    int mapSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //控件的初始化
        Objects.requireNonNull(getSupportActionBar()).hide();
        gridView = findViewById(R.id.gridView);
        textView_mineLastNum = findViewById(R.id.mineLastedNum);
        editText_height = findViewById(R.id.editText_height);
        editText_mineNum = findViewById(R.id.editText_MineNum);
        editText_width = findViewById(R.id.editText_width);
        editText_mapSize = findViewById(R.id.editText_MapSize);
        titleView_face = findViewById(R.id.face);
        titleView_face.gameState=0;
        //对笑脸View视图设置的Click监听器，实现开始游戏功能
        titleView_face.setOnClickListener(v -> {
            //游戏状态设置为游戏中
            ((TitleView_Face) v).gameState = 0;
            //获取输入框中的数据，得到地图参数
            mapSize = getIntFromEditText(editText_mapSize)==0?800:getIntFromEditText(editText_mapSize);
            mineNum = getIntFromEditText(editText_mineNum)==0?8:getIntFromEditText(editText_mineNum);
            width = getIntFromEditText(editText_width)==0?6:getIntFromEditText(editText_width);
            height = getIntFromEditText(editText_height)==0?6:getIntFromEditText(editText_height);
            //定义GridView大小（地图大小）和列数
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(mapSize, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            gridView.setLayoutParams(params);
            gridView.setNumColumns(width);
            //创建地图数据，展示一个num=0的格子
            Units units = new Units(mineNum, width , height );
            units.showSomeUnits();
            //为GridView配置适配器
            MyAdapter myAdapter=new MyAdapter(getApplicationContext(),units,mapSize/width,titleView_face,textView_mineLastNum,mineNum);
            gridView.setAdapter(myAdapter);
            gridView.setOnItemClickListener(myAdapter);
            gridView.setOnItemLongClickListener(myAdapter);
        });
        //对反馈BUG文字做的监听器，跳转到我的QQ资料页
        findViewById(R.id.intent).setOnClickListener(v -> {
            Intent i = new Intent();
            i.setPackage("com.tencent.mobileqq");
            i.setData(Uri.parse("mqqapi://card/show_pslcard?src_type=internal&version=1&nim=3095598652"));
            startActivity(i);
        });


    }

    //工具方法，将输入框中的文字转为int类型并返回
    public Integer getIntFromEditText(EditText editText) {
        if (editText.getText().toString().equals("")) {
            return 0;
        }
        return Integer.parseInt(editText.getText().toString());
    }
}