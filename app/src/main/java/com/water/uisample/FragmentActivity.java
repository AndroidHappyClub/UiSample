package com.water.uisample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.water.uisample.fragment.FragmentSample;

import java.util.HashMap;

public class FragmentActivity extends AppCompatActivity implements
        View.OnClickListener, FragmentSample.OnItemSelectedListener {
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private FragmentManager _fm;
    private HashMap<String, Fragment> _dict;
    private Fragment _currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        _fm = getSupportFragmentManager();
        _dict = new HashMap<String, Fragment>();

        btnOne =(Button)findViewById(R.id.btnOne);
        btnTwo =(Button)findViewById(R.id.btnTwo);
        btnThree =(Button)findViewById(R.id.btnThree);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnOne:
            case R.id.btnTwo:
            case R.id.btnThree:
                replaceFragment((String)view.getTag());

                //switchFragment((String)view.getTag());
                break;
        }
    }

    private void replaceFragment(String tag){
        if(tag != null){
            try {
                if(!_dict.containsKey(tag)) {
                    Class<?> cfra = Class.forName("com.water.uisample.fragment.Fragment" + tag);
                    Fragment fra = (Fragment) cfra.newInstance();
                    _dict.put(tag, fra);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        replaceFragment(_dict.get(tag));
    }

    private void replaceFragment(Fragment to) {
        if (!to.isAdded() || to.isHidden()) {
            FragmentTransaction ft = _fm.beginTransaction();
            ft.replace(R.id.flContainer, to).commit();
        }
    }

    private void switchFragment(String tag){
        if(tag != null){
            try {
                if(!_dict.containsKey(tag)) {
                    Class<?> cfra = Class.forName("com.water.uisample.fragment.Fragment" + tag);
                    Fragment fra = (Fragment) cfra.newInstance();
                    _dict.put(tag, fra);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        switchFragment(_currentFragment, _dict.get(tag));

        _currentFragment = _dict.get(tag);
    }

    private void switchFragment(Fragment from, Fragment to) {
        FragmentTransaction ft = _fm.beginTransaction();

        if (from !=null)
            ft.hide(from);

        // 先判断to是否被add过
        if (!to.isAdded()) {
            // 隐藏当前的fragment，add下一个到Activity中
            ft.add(R.id.flContainer, to).commit();
        } else {
            // 隐藏当前的fragment，显示下一个
            ft.show(to).commit();
        }
    }

    private void addFragment(Fragment fra, String tag) {
        FragmentTransaction ft = _fm.beginTransaction();
        ft.add(R.id.flContainer,fra, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }

    private void hideFragment(String tag)
    {
        Fragment fra = _fm.findFragmentByTag("tag");
        FragmentTransaction ft = _fm.beginTransaction();
        ft.hide(fra);
        ft.addToBackStack("hide " + tag);
        ft.commit();
    }

    private void showFragment(String tag)
    {
        Fragment fra = _fm.findFragmentByTag(tag);
        FragmentTransaction ft = _fm.beginTransaction();
        ft.show(fra);
        ft.addToBackStack("show " + tag);
        ft.commit();
    }

    // 将view与fragment分离且将fragment从Activity的ADD队列中移除
    private void detachFragment(String tag)
    {
        Fragment fra = _fm.findFragmentByTag(tag);
        FragmentTransaction ft = _fm.beginTransaction();
        ft.detach(fra);
        ft.addToBackStack("detach " + tag);
        ft.commit();
    }

    // 利用fragment的onCreateView()来重建视图且将此fragment添加到ADD队列中
    private void attachFragment(String tag)
    {
        Fragment fragment = _fm.findFragmentByTag(tag);
        FragmentTransaction ft = _fm.beginTransaction();
        ft.attach(fragment);
        ft.addToBackStack("attach " + tag);
        ft.commit();
    }

    @Override
    public void onItemSelected(View view) {
        if (view != null)
            btnOne.setTextColor(((TextView)view).getCurrentTextColor());
    }
}