package com.water.uisample;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MenuActivity extends AppCompatActivity {
    private TextView tvOptionsMenu;
    private TextView tvContextMenu;
    private Button btnShowMenu;

    private int[] _colors ={
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.GRAY,
            Color.CYAN,
            Color.BLACK
    };
    private String[] _titles ={"红色","绿色","蓝色","黄色","灰色","蓝绿色","黑色"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // 注册ContextMenu
        tvContextMenu = (TextView) findViewById(R.id.tvContextMenu);
        registerForContextMenu(tvContextMenu);

        // 创建PopupMenu
        btnShowMenu = (Button) findViewById(R.id.btnShowMenu);
        btnShowMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pm = new PopupMenu(MenuActivity.this,btnShowMenu);
                pm.getMenuInflater().inflate(R.menu.menu_context, pm.getMenu());
                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.miShowSettings:
                                Toast.makeText(MenuActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.miNetworkSettings:
                                Toast.makeText(MenuActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.miOtherSettings:
                                Toast.makeText(MenuActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                pm.show();
            }
        });

        tvOptionsMenu = (TextView) findViewById(R.id.tvOptionsMenu);
    }

    /* 创建选项菜单和子菜单 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setIconEnable(menu, true);

        int groupid = 0;

        // 添加子菜单
        SubMenu sm = menu.addSubMenu(groupid, -1, 0, "颜色设置").
                setIcon(R.mipmap.ic_menu_share);
        // 添加4个菜单项，分成2组（index % 2）：0,1
        for (int index =0; index < _colors.length; index++) {
            // 菜单组ID：group ID，菜单ID：item ID，菜单显示顺序：order ID，菜单标题：title
            sm.add(groupid, index, index, _titles[index]).setIcon(R.mipmap.ic_menu_share);
        }

        groupid++;

        sm = menu.addSubMenu(groupid, -1, Menu.NONE, "基础操作").
                setIcon(R.mipmap.ic_menu_share);
        sm.add(groupid, 0, 0, "重命名");
        sm.add(groupid, 1, 1,  "分享");
        sm.add(groupid, 2, 2,  "删除");

        // 显示菜单
        return true;
    }

    // enable为true时，菜单添加图标有效，enable为false时无效。4.0系统默认无效
    private void setIconEnable(Menu menu, boolean enable){
        try{
//             Class<?> mb = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Class<?> mb = Class.forName("android.support.v7.view.menu.MenuBuilder");
            Method oiv = mb.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            oiv.setAccessible(true);

            //MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            oiv.invoke(menu, enable);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /* 响应选项菜单 */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        int gid = item.getGroupId();

        if(gid == 0 && id>-1) {
            tvOptionsMenu.setTextColor(_colors[id]);
        }

        String msg = String.format("菜单组ID：%d 菜单ID：%d 菜单显示顺序：%d 菜单标题：%s",
                gid , id, item.getOrder(), item.getTitle());
        Toast.makeText(MenuActivity.this, msg, Toast.LENGTH_LONG).show();

        return super.onOptionsItemSelected(item);
    }

    /* 基于XML创建ContextMenu */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        //子菜单部分：
        MenuInflater inflator = new MenuInflater(this);
        inflator.inflate(R.menu.menu_context, menu);
        setIconEnable(menu,true);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    /* 响应上下文菜单 */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miShowSettings:
                Toast.makeText(MenuActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.miNetworkSettings:
                item.setCheckable(true);
                Toast.makeText(MenuActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.miOtherSettings:
                Toast.makeText(MenuActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                item.setCheckable(true);
                break;
        }
        return true;
    }
}