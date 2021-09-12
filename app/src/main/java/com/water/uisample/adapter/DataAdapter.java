package com.water.uisample.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;

public abstract class DataAdapter<T> extends BaseAdapter {
    private ArrayList<T> _data;
    private final int _layout; //布局id

    public DataAdapter(ArrayList<T> data, int layout) {
        this._data = data;
        this._layout = layout;
    }

    @Override
    public int getCount() {
        return _data != null ? _data.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return _data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.bind(parent.getContext(), convertView, parent, _layout, position);
        bindViews(holder, getItem(position));
        return holder.getItemView();
    }

    public abstract void bindViews(ViewHolder holder, T obj);

    //添加一个元素
    public void add(T data) {
        if (_data == null) {
            _data = new ArrayList<>();
        }
        _data.add(data);
        notifyDataSetChanged();
    }

    //往特定位置，添加一个元素
    public void add(int position, T data) {
        if (_data == null) {
            _data = new ArrayList<>();
        }
        _data.add(position, data);
        notifyDataSetChanged();
    }

    public void remove(T data) {
        if (_data != null) {
            _data.remove(data);
        }
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (_data != null) {
            _data.remove(position);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        if (_data != null) {
            _data.clear();
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        private final SparseArray<View> _views;   //存储ListView 的 item中的View
        private View _item;                  //存放convertView
        private int _position;               //游标

        //构造方法，完成相关初始化
        private ViewHolder(Context context, ViewGroup parent, int layoutRes) {
            _views = new SparseArray<>();
            //Context上下文
            View convertView = LayoutInflater.from(context).inflate(layoutRes, parent, false);
            convertView.setTag(this);
            _item = convertView;
        }

        //绑定ViewHolder与item
        public static ViewHolder bind(Context context, View convertView, ViewGroup parent,
                                      int layoutRes, int position) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder(context, parent, layoutRes);
            } else {
                holder = (ViewHolder) convertView.getTag();
                holder._item = convertView;
            }
            holder._position = position;
            return holder;
        }

        @SuppressWarnings("unchecked")
        public <T extends View> T getView(int id) {
            T t = (T) _views.get(id);
            if (t == null) {
                t = (T) _item.findViewById(id);
                _views.put(id, t);
            }
            return t;
        }

        /**
         * 获取当前条目
         */
        public View getItemView() {
            return _item;
        }

        /**
         * 获取条目位置
         */
        public int getItemPosition() {
            return _position;
        }

        /**
         * 设置文字
         */
        public ViewHolder setText(int id, CharSequence text) {
            View view = getView(id);
            if (view instanceof TextView) {
                ((TextView) view).setText(text);
            }
            return this;
        }

        /**
         * 设置图片
         */
        public void setImageResource(int id, @DrawableRes int drawableRes) {
            View view = getView(id);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(drawableRes);
            } else {
                view.setBackgroundResource(drawableRes);
            }
        }

        /**
         * 设置点击监听
         */
        public ViewHolder setOnClickListener(int id, View.OnClickListener listener) {
            getView(id).setOnClickListener(listener);
            return this;
        }

        /**
         * 设置可见
         */
        public ViewHolder setVisibility(int id, int visible) {
            getView(id).setVisibility(visible);
            return this;
        }

        /**
         * 设置标签
         */
        public ViewHolder setTag(int id, Object obj) {
            getView(id).setTag(obj);
            return this;
        }
    }

}

