package com.example.saolei;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.saolei.CreatMap.Units;
import com.example.saolei.view.MyAdapter;
import com.example.saolei.view.TitleView_Face;

public class MainActivity extends AppCompatActivity {
    TitleView_Face titleView_face;
    EditText editText_height;
    EditText editText_width;
//    EditText editText_unitSize;
    EditText editText_mineNum;
    TextView textView_mineLastNum;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        gridView = findViewById(R.id.gridView);
        textView_mineLastNum = findViewById(R.id.mineLastedNum);
        editText_height = findViewById(R.id.editText_height);
        editText_mineNum = findViewById(R.id.editText_MineNum);
//        editText_unitSize = findViewById(R.id.editText_unitSize);
        editText_width = findViewById(R.id.editText_width);
        titleView_face = findViewById(R.id.face);
        titleView_face.gameState=0;
        titleView_face.setOnClickListener(v -> {
            int mineNum = getIntFromEditText(editText_mineNum)==0?8:getIntFromEditText(editText_mineNum);
            int width = getIntFromEditText(editText_width)==0?6:getIntFromEditText(editText_width);
            int height = getIntFromEditText(editText_height)==0?6:getIntFromEditText(editText_height);
            Units units = new Units(mineNum, width , height );
            units.crateMap();
            MyAdapter myAdapter=new MyAdapter(this,units,800/width);
            gridView.setNumColumns(width);
            gridView.setAdapter(myAdapter);
            gridView.setOnItemClickListener(myAdapter);
            gridView.setOnItemLongClickListener(myAdapter);
        });


    }
    public Integer getIntFromEditText(EditText editText) {
        if (editText.getText().toString().equals("")) {
            return 0;
        }
        return Integer.parseInt(editText.getText().toString());
    }
}