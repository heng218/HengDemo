package com.example.heng.hengdemo.view.timeline;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heng.hengdemo.R;

import org.w3c.dom.Text;

public class TimelineListViewAdapter extends BaseExpandableListAdapter  {
	private List<List<String>> groupList;
	private Context context;
	private LayoutInflater inflater = null;

	public TimelineListViewAdapter(Context context, List<List<String>> groupList) {
		this.context = context;
		this.groupList = groupList;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final ChildViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ChildViewHolder();
			convertView = inflater.inflate(R.layout.item_listview_child, parent, false);
			viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ChildViewHolder) convertView.getTag();
		}

		viewHolder.tv_content.setText(groupList.get(groupPosition).get(childPosition + 1));
		convertView.setTag(R.id.tv_content, viewHolder.tv_content.getText().toString());
		return convertView;
	}


	@Override
	public int getChildrenCount(int groupPosition) {
		return groupList.get(groupPosition).size() -1 ;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groupList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return groupList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		final ViewHolderHeader viewHolderHeader;
		if (convertView == null) {
			viewHolderHeader = new ViewHolderHeader();
			convertView = View.inflate(context, R.layout.item_listview_head, null);
			viewHolderHeader.tv_head = (TextView) convertView.findViewById(R.id.tv_head);
			convertView.setTag(viewHolderHeader);
		} else {
			viewHolderHeader = (ViewHolderHeader) convertView.getTag();
		}

		//
		viewHolderHeader.tv_head.setText(groupList.get(groupPosition).get(0));

		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}


	class ChildViewHolder {
		public TextView tv_content;
	}

	static class ViewHolderHeader {
		TextView tv_head;
	}

//	@Override
//	public boolean onLongClick(View v) {
//		return true;
//		// return false;
//	}

//	@Override
//	public void onClick(View v) {
//
//	}

}
