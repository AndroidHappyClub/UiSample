package com.water.uisample;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.water.uisample.databinding.ActivityMenuBinding;

import java.lang.reflect.Method;

public class MenuActivity extends AppCompatActivity {

    private final int[] _colors ={
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.GRAY,
            Color.CYAN,
            Color.BLACK
    };
    private final String[] _titles ={"红色","绿色","蓝色","黄色","灰色","蓝绿色","黑色"};

    private ActivityMenuBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_menu);

        // 注册ContextMenu
        registerForContextMenu(binding.tvContextMenu);

        // 创建PopupMenu
        binding.btnShowMenu.setOnClickListener(v -> {
            PopupMenu pm = new PopupMenu(MenuActivity.this,binding.btnShowMenu);
            pm.getMenuInflater().inflate(R.menu.menu_context, pm.getMenu());
            pm.setOnMenuItemClickListener(item -> {
                Toast.makeText(MenuActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                return true;
            });
            pm.show();
        });
    }

    /* 创建选项菜单和子菜单 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setIconEnable(menu);

        int groupId = 0;

        // 添加颜色设置项
        SubMenu sm = menu.addSubMenu(groupId, -1, Menu.NONE, "颜色设置");
        for (int index =0; index < _colors.length; index++) {
            // 菜单组ID：group ID，菜单ID：item ID，菜单显示顺序：order ID，菜单标题：title
            sm.add(groupId, index, index, _titles[index]).setIcon(R.mipmap.ic_menu_share);
        }

        groupId++;

        // 添加基础操作设置项
        sm = menu.addSubMenu(groupId, -1, Menu.NONE, "基础操作");
        sm.add(groupId, 0, 0, "重命名");
        sm.add(groupId, 1, 1,  "分享");
        sm.add(groupId, 2, 2,  "删除");

        // 显示菜单
        return true;
    }

    // enable为true时，菜单添加图标有效，enable为false时无效。4.0系统默认无效
    private void setIconEnable(Menu menu){
        try{
            // Class<?> mb = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Class<?> mb = Class.forName("android.support.v7.view.menu.MenuBuilder");
            Method oiv = mb.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            oiv.setAccessible(true);

            // MenuBuilder实现Menu接口，创建菜单时，传进来的menu其实就是MenuBuilder对象(java的多态特征)
            oiv.invoke(menu, true);
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
            binding.tvOptionsMenu.setTextColor(_colors[id]);
        }

        @SuppressLint("DefaultLocale") String msg = String.format("菜单组ID：%d 菜单ID：%d 菜单显示顺序：%d 菜单标题：%s",
                gid , id, item.getOrder(), item.getTitle());
        Toast.makeText(MenuActivity.this, msg, Toast.LENGTH_LONG).show();

        return super.onOptionsItemSelected(item);
    }

    /* 基于XML创建ContextMenu */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        //子菜单部分：
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_context, menu);
        setIconEnable(menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    /* 响应上下文菜单 */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(MenuActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
        return true;
    }
}
