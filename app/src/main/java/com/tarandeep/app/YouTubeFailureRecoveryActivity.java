package com.tarandeep.app;

import android.content.Intent;
import android.widget.Toast;

import com.tarandeep.app.Utils.DataPersistence;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

public abstract class YouTubeFailureRecoveryActivity extends YouTubeBaseActivity implements
YouTubePlayer.OnInitializedListener {

	private static final int RECOVERY_DIALOG_REQUEST = 1;

	@Override
	public void onInitializationFailure(YouTubePlayer.Provider provider,
			YouTubeInitializationResult errorReason) {
		if (errorReason.isUserRecoverableError()) {
			errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
		} else {
			String errorMessage = String.format("Error", errorReason.toString());
			Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RECOVERY_DIALOG_REQUEST) {
			getYouTubePlayerProvider().initialize(DataPersistence.getInstance().getDeveloperKey(), this);
		}
	}

	protected abstract YouTubePlayer.Provider getYouTubePlayerProvider();

}
