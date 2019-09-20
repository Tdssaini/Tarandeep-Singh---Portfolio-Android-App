package com.tarandeep.app.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.tarandeep.app.Models.ChannelDetail;
import com.tarandeep.app.R;
import com.tarandeep.app.YoutubeVideoActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class YoutubeListAdapter extends ArrayAdapter<ChannelDetail>{
	private Context context;
	private int resource;
	private ArrayList<ChannelDetail> arrayList;

	public YoutubeListAdapter(Context context, int resource, ArrayList<ChannelDetail> arrayList) {
		super(context, resource,arrayList);
		this.context = context;
		this.resource = resource;
		this.arrayList = arrayList;
	}

	class ViewHolder{
		ImageView youtube_view1;
		TextView title;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		final ChannelDetail obj = getItem(position);
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(resource, parent, false);
			holder.youtube_view1 = (ImageView)convertView.findViewById(R.id.youtube_view1);
			holder.title = (TextView)convertView.findViewById(R.id.title);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText(obj.getSnippet().getTitle());
		HashMap<String, String> urlObj = obj.getSnippet().getThumbnails().get("default");
		new DownloadImageTask(holder.youtube_view1).execute(urlObj.get("url"));
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,YoutubeVideoActivity.class);
				intent.putExtra("OBJECT", obj.getSnippet());
				context.startActivity(intent);
				((Activity)context).overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
			}
		});
		return convertView;
	}

	@Override
	public int getCount() {
		return arrayList.size();
	}

	@Override
	public ChannelDetail getItem(int position) {
		return arrayList.get(position);
	}
}