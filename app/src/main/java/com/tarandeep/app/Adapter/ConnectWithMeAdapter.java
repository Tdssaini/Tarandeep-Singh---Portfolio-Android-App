package com.tarandeep.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tarandeep.app.Fragments.FragWebView;
import com.tarandeep.app.MainActivity;
import com.tarandeep.app.Models.ConnectWithMeModel;
import com.tarandeep.app.Models.WebViewModel;
import com.tarandeep.app.R;
import com.tarandeep.app.Utils.DataPersistence;

import java.util.ArrayList;



public class ConnectWithMeAdapter extends ArrayAdapter<ConnectWithMeModel>{
	private Context context;
	private int resource;
	private ArrayList<ConnectWithMeModel> arrayList;

	public ConnectWithMeAdapter(Context context, int resource, ArrayList<ConnectWithMeModel> arrayList) {
		super(context, resource,arrayList);
		this.context = context;
		this.resource = resource;
		this.arrayList = arrayList;
	}

	class ViewHolder{
		TextView title;
		LinearLayout parentPanel;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        final MainActivity mainActivity = DataPersistence.getInstance().getApplicationContext();
		ViewHolder holder = null;
		final ConnectWithMeModel obj = getItem(position);
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(resource, parent, false);
			holder.title = (TextView) convertView.findViewById(R.id.tvTitle);

			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText(obj.getLabel());
        holder.title.setTypeface(Typeface.createFromAsset(mainActivity.getAssets(), "pacifico.ttf"));
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
               if(obj.getUrl().toLowerCase().startsWith("tel:")){
                    if(ContextCompat.checkSelfPermission(mainActivity, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(obj.getUrl()));
                        mainActivity.startActivity(intent);
                    }
                }else if(obj.getUrl().toLowerCase().startsWith("mailto:")){
                    String emailid = obj.getUrl().toLowerCase().replace("mailto:","");
                    Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                    emailIntent.setType("text/html");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {emailid});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contact Me");
                    mainActivity.startActivity(Intent.createChooser(emailIntent, "Pick an Email provider"));
                }else {
                    Bundle bundle = new Bundle();
                    WebViewModel webViewModel = new WebViewModel();
                    webViewModel.setUrl(obj.getUrl());
                    bundle.putSerializable("OBJECT", webViewModel);
                    mainActivity.isEditPage = true;
                    mainActivity.replaceFragment(new FragWebView(), bundle);
                }
			}
		});
		return convertView;
	}
}