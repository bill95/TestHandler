/**
*
*/
package com.inisgmacc.yulindemo.testhandler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PopListAdapter
 * @Description: TODO
 * @author Administrator
 * @date 2016年12月14日
 *
 */
public class PopListAdapter extends BaseAdapter {
	private List<String> list;
	private Context context;

	public PopListAdapter(Context context) {
		super();
		this.list = new ArrayList<String>();
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_popup_recharge, parent, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.pop_rc_text.setText(list.get(position));
		return convertView;
	}

	class ViewHolder {
		public TextView pop_rc_text;

		ViewHolder(View view) {
			pop_rc_text = (TextView) view.findViewById(R.id.pop_rc_text);
		}
	}

	public void addData(List<String> data) {
		list.addAll(data);
		notifyDataSetChanged();
	}


}
