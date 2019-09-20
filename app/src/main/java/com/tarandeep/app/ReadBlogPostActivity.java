package com.tarandeep.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tarandeep.app.Models.WordpressPostModel;

public class ReadBlogPostActivity extends AppCompatActivity {

    private ImageView image_scrolling_top;
    private WordpressPostModel wordpressPostModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readpost);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        String imageBckgrnd = getIntent().getExtras().getString("URL");
        wordpressPostModel = (WordpressPostModel)getIntent().getExtras().getSerializable("OBJECT");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_scrolling);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, wordpressPostModel.getTitle().getRendered()+" Read now at "+wordpressPostModel.getLink());
                intent.setType("text/plain");
                startActivity(intent);
            }
        });

        image_scrolling_top = (ImageView) findViewById(R.id.image_scrolling_top);
        Glide.with(this).load(Uri.parse(imageBckgrnd)).fitCenter().into(image_scrolling_top);
        setTitle("Blog Post");
        ((TextView)findViewById(R.id.tv_title)).setText(wordpressPostModel.getTitle().getRendered());
        String contentHtml = "";
        contentHtml += "<html><head><link href=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.9.0/styles/default.min.css\" type=\"text/css\" rel=\"stylesheet\" /><script type=\"text/javascript\" src=\"https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js\"></script></head><body>";
        contentHtml += wordpressPostModel.getContent().getRendered();
        contentHtml += "</body></html>";
        contentHtml.replaceAll("srcset=\"\\\"","srcset=\"");
        WebView webView = ((WebView)findViewById(R.id.tv_description));
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkLoads(false);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.loadData(contentHtml, "text/html", "UTF-8");
    }



    @Override
    protected void onResume() {
        super.onResume();
        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}
