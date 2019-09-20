package com.tarandeep.app.Fragments;


import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tarandeep.app.Models.Channel;
import com.tarandeep.app.Models.PlayList;
import com.tarandeep.app.R;
import com.tarandeep.app.Utils.DataAsyncTask;
import com.tarandeep.app.Utils.DataPersistence;
import com.tarandeep.app.Utils.DataUtil;
import com.tarandeep.app.Utils.YoutubeListAdapter;


/**
 * Created by Tarandeep on 12/19/2015.
 */
public class FragVideos extends BaseFragment implements View.OnClickListener {

    private ListView listView;
    private LinearLayout searchLayout;
    private ImageView next,previous;
    private View btn_view;

    @Override
    void getExtras() {}

    @Override
    int getLayoutId() {
        return R.layout.frag_videos;
    }

    @Override
    void addListeners() {}

    @Override
    void setupBindings() {
        next.setOnClickListener(this);
        previous.setOnClickListener(this);
        btn_view = (View)view.findViewById(R.id.btn_view);
        sendRequestToServer("https://www.googleapis.com/youtube/v3/channels?part=contentDetails&id=UCVFzF5QnAQXU5a5ZIgAQ0nA&key="+ DataPersistence.getInstance().getDeveloperKey());
    }



    @Override
    void initializeViews() {
        listView = (ListView)view.findViewById(R.id.listView);
        searchLayout = (LinearLayout)view.findViewById(R.id.searchLayout);
        next = (ImageView)view.findViewById(R.id.nextBtn);
        previous = (ImageView)view.findViewById(R.id.previousBtn);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
        }
    }

    private void setBtnLayouts(){
        if(DataPersistence.getInstance().getChannel().getNextPageToken()==null){
            previous.setVisibility(View.VISIBLE);
            btn_view.setVisibility(View.GONE);
            next.setVisibility(View.GONE);
        }
        else if(DataPersistence.getInstance().getChannel().getPrevPageToken()==null){
            previous.setVisibility(View.GONE);
            btn_view.setVisibility(View.GONE);
            next.setVisibility(View.VISIBLE);
        }
        else if(DataPersistence.getInstance().getChannel().getNextPageToken()!=null && DataPersistence.getInstance().getChannel().getPrevPageToken()!=null){
            previous.setVisibility(View.VISIBLE);
            btn_view.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }
    }

    private void sendRequestToServer(String url){
        new DataAsyncTask(getActivity()){
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                PlayList playList = (PlayList) DataUtil.convertResponseIntoObject(result,PlayList.class);
                DataPersistence.getInstance().setPlayListData(playList);
                sendRequestForPlayList(null);
            };
        }.execute(url);
    }

    private void sendRequestForPlayList(String nextToken){
        String playListId = DataPersistence.getInstance().getPlayListData().getItems().get(0).getContentDetails().getRelatedPlaylists().getUploads();
        String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId="+playListId+"&key="+DataPersistence.getInstance().getDeveloperKey();
        if(nextToken!=null)
            url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId="+playListId+"&key="+DataPersistence.getInstance().getDeveloperKey()+"&pageToken="+nextToken;
        System.out.println(url);
        new DataAsyncTask(getActivity()){
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Channel channel = (Channel) DataUtil.convertResponseIntoObject(result, Channel.class);
                DataPersistence.getInstance().setChannel(channel);
                listView.setAdapter(new YoutubeListAdapter(getParentActivity(),R.layout.youtube_list_view,channel.getItems()));
                setBtnLayouts();
            };
        }.execute(url);

    }

    public void onClick(View v) {
        if(v.getId() == R.id.nextBtn){
            sendRequestForPlayList(DataPersistence.getInstance().getChannel().getNextPageToken());
        }else if(v.getId() == R.id.previousBtn){
            sendRequestForPlayList(DataPersistence.getInstance().getChannel().getPrevPageToken());
        }
    }

}
