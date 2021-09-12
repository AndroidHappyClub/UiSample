package com.water.uisample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.water.uisample.R;
import com.water.uisample.model.BaseItem;

import java.util.ArrayList;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private final ArrayList<BaseItem> _gdata;
    private final ArrayList<ArrayList<BaseItem>> _data;
    private final Context _ctx;

    public ExpandableListAdapter(
            ArrayList<BaseItem> gdata,
            ArrayList<ArrayList<BaseItem>> data,
            Context ctx
    ) {
        this._gdata = gdata;
        this._data = data;
        this._ctx = ctx;
    }

    @Override
    public int getGroupCount() {
        return _gdata.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return _data.get(groupPosition).size();
    }

    @Override
    public BaseItem getGroup(int groupPosition) {
        return _gdata.get(groupPosition);
    }

    @Override
    public BaseItem getChild(int groupPosition, int childPosition) {
        return _data.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        ViewHolderGroup groupHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(_ctx).inflate(
                    R.layout.list_view_item_group, parent, false);
            groupHolder = new ViewHolderGroup();
            groupHolder.tvName = convertView.findViewById(R.id.tvName);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tvName.setText(_gdata.get(groupPosition).getName());
        return convertView;
    }

    //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(_ctx).inflate(
                    R.layout.list_view_item, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.ivThumb = convertView.findViewById(R.id.ivThumb);
            itemHolder.tvName = convertView.findViewById(R.id.tvName);
            convertView.findViewById(R.id.tvAuthor).setVisibility(View.GONE);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.ivThumb.setImageResource(_data.get(groupPosition).get(childPosition).getThumb());
        itemHolder.tvName.setText(_data.get(groupPosition).get(childPosition).getName());
        return convertView;
    }

    //设置子列表是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class ViewHolderGroup {
        private TextView tvName;
    }

    private static class ViewHolderItem {
        private ImageView ivThumb;
        private TextView tvName;
    }
}
