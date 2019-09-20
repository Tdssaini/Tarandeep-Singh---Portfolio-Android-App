package com.tarandeep.app.Utils;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.tarandeep.app.Models.WordpressPostModel;
import com.tarandeep.app.R;
import com.tarandeep.app.ReadBlogPostActivity;

import java.util.ArrayList;

/**
 * Created by zhang on 2016.08.07.
 */
public class WPPostViewAdapter extends ArrayAdapter<WordpressPostModel>{

    private Context context;
    private ArrayList<WordpressPostModel> mItems;
    private int color = 0;
    private LayoutInflater mInflater;

    public WPPostViewAdapter(Context context, ArrayList<WordpressPostModel> arrayList) {
        super(context,R.layout.item_wp_post,0,arrayList);
        mInflater = LayoutInflater.from(context);
        this.context = context;
        mItems = new ArrayList();
        mItems = arrayList;
    }

    private class ViewHolder{
        private ImageView rela_round;
        private Button btn_card_main1_action1;
        private TextView title;
        private TextView subTitle;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos=position;
        final WordpressPostModel item = getItem(position);
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_wp_post, parent, false);
            holder = new ViewHolder();
            holder.rela_round = (ImageView) convertView.findViewById(R.id.img_main_card_1);
            holder.btn_card_main1_action1 = (Button) convertView.findViewById(R.id.btn_card_main1_action1);
            holder.title = (TextView) convertView.findViewById(R.id.tv_card_main_1_title);
            holder.subTitle = (TextView) convertView.findViewById(R.id.tv_card_main1_subtitle);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(context, ReadBlogPostActivity.class);
                intent.putExtra("URL","http://blog.ertarandeep.com/wp-content/uploads/2017/02/download.jpg");
                intent.putExtra("OBJECT",item);
                ActivityOptions options =
                        ActivityOptions.makeCustomAnimation(context, R.anim.slide_in_up, R.anim.slide_out_down);
                context.startActivity(intent,options.toBundle());

            }
        });
        holder.btn_card_main1_action1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickShare(item.getTitle().getRendered()+" Read now at "+item.getLink());
            }
        });
        holder.title.setText(mItems.get(position).getTitle().getRendered());
        holder.subTitle.setText(Html.fromHtml(mItems.get(position).getContent().getRendered()));
        holder.subTitle.setMaxLines(4);
        new DownloadImageTask(holder.rela_round).execute("http://blog.ertarandeep.com/wp-content/uploads/2017/02/download.jpg");
        return convertView;
    }

    public void onClickShare(String message){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("text/plain");
        context.startActivity(intent);
    }


}
