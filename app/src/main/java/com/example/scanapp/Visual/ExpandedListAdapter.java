package com.example.scanapp.Visual;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.scanapp.R;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

public class ExpandedListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> dishCategory;
    Map<String, List<String>> dish;

    public ExpandedListAdapter(Context context, List<String> dishCategory, Map<String, List<String>> dish) {
        this.context = context;
        this.dishCategory = dishCategory;
        this.dish = dish;
    }

    @Override
    public int getGroupCount() {
        return dishCategory.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dish.get(dishCategory.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dishCategory.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dish.get(dishCategory.get(groupPosition)).get(childPosition);
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

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String dishCategoryTitle = (String) getGroup(groupPosition);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView txtGroup = (TextView) convertView.findViewById(R.id.groupText);
        txtGroup.setText(dishCategoryTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String dishTitle = (String) getChild(groupPosition, childPosition);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_element, null);
        }

        TextView txtElement = (TextView) convertView.findViewById(R.id.listText);
        txtElement.setText(dishTitle);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
