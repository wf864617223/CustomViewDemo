package com.hb.rimi.customviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hb.rimi.customviewdemo.widget.FlowLayoutView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> names = new ArrayList<String>();
    FlowLayoutView flowLayoutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
        flowLayoutView = (FlowLayoutView) findViewById(R.id.flv);
        for (int i = 0; i < names.size(); i++) {
            addTextView(names.get(i));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            // 动态添加 view
            addTextView(names.get((int) (Math.random() * names.size())));

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 为每个view 添加点击事件
     *
     * @param tv
     */
    private void initEvents(final TextView tv) {
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, tv.getText().toString(), 0).show();
            }
        });
    }

    private void setData() {
        names.add("搞笑");
        names.add("娱乐");
        names.add("文化");
        names.add("社会");
        names.add("交流");
        names.add("职场");
        names.add("生活");
        names.add("军事");
        names.add("科技");
        names.add("时尚");
        names.add("直播");
        names.add("图十三");
        names.add("娃娃音");
        names.add("海州");
        names.add("军绿");
    }

    /**
     * 动态添加布局
     *
     * @param str
     */
    private void addTextView(String str) {
        TextView child = new TextView(this);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        params.setMargins(15, 15, 15, 15);

        child.setLayoutParams(params);
        child.setBackgroundResource(R.drawable.shape_corner);
        child.setText(str);
        child.setTextColor(Color.WHITE);
        initEvents(child);
        flowLayoutView.addView(child);
        // 务必要加这句
        flowLayoutView.requestLayout();
    }
}
