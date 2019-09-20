
package com.tarandeep.app;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import com.tarandeep.app.Models.ChannelItems;
import com.tarandeep.app.Utils.DataPersistence;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class YoutubeVideoActivity extends YouTubeFailureRecoveryActivity implements YouTubePlayer.OnFullscreenListener{

	private YouTubePlayerView playerView;
	private YouTubePlayer player;
	private TextView title, description;
	private ChannelItems channelItems;
	private boolean isFullScreen = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_video);
		channelItems = (ChannelItems)getIntent().getSerializableExtra("OBJECT");
		playerView =  findViewById(R.id.player);
		playerView.initialize(DataPersistence.getInstance().getDeveloperKey(), this);
		title = findViewById(R.id.title);
		description = findViewById(R.id.description);
		title.setText(channelItems.getTitle());
		description.setText(channelItems.getDescription());
	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
		this.player = player;
		player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
		player.setOnFullscreenListener(this);
		if (!wasRestored) {
			player.cueVideo(channelItems.getResourceId().get("videoId"));
		}
	}

	@Override
	protected YouTubePlayer.Provider getYouTubePlayerProvider() {
		return playerView;
	}

	@Override
	public void onFullscreen(boolean isFullscreen) {
		this.isFullScreen = isFullscreen;
		if(isFullscreen){
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}else{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onBackPressed() {
		if(!isFullScreen)
			super.onBackPressed();
	}

}
