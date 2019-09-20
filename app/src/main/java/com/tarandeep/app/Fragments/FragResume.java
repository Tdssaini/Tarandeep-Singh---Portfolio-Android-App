package com.tarandeep.app.Fragments;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.tarandeep.app.R;
import com.tarandeep.app.Utils.DownloadPdfTask;
import com.tarandeep.app.Utils.TDSProgressBar;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.util.List;


/**
 * Created by Tarandeep Singh on 5/25/2017.
 */

public class FragResume extends BaseFragment {

    PDFView pdfView;
    Integer pageNumber = 0;
    TDSProgressBar progressBar;
    String url;

    @Override
    public int getLayoutId() {
        return R.layout.activity_readpdf;
    }

    @Override
    void getExtras() {
        url = getArguments().getString("URL");
    }

    @Override
    void addListeners() {
        view.findViewById(R.id.sharePdf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickShare(v);
            }
        });
        view.findViewById(R.id.downloadPdf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDownload(v);
            }
        });
    }

    @Override
    void setupBindings() {
        progressBar = new TDSProgressBar(getParentActivity()) {
            @Override
            public void onDismiss() {
            }
        };
        DownloadPdfTask downloadImageTask = new DownloadPdfTask(progressBar){
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(!result.equals("ERROR")) {
                    pdfView.fromFile(new File(result))
                            .defaultPage(pageNumber)
                            .enableSwipe(true)
                            .swipeHorizontal(false)
                            .onPageChange(getParentActivity())
                            .enableAnnotationRendering(true)
                            .onLoad(getParentActivity())
                            .scrollHandle(new DefaultScrollHandle(getParentActivity()))
                            .load();
                }else
                    if(progressBar !=null && progressBar.isShowing())
                        progressBar.dismiss();
            }
        };
        downloadImageTask.execute(url);
    }

    @Override
    void initializeViews() {
        pdfView= (PDFView)view.findViewById(R.id.pdfView);
    }

    @Override
    public void onPdfLoad(int nPages) {
        super.onPdfLoad(nPages);
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");
        if (progressBar!=null && progressBar.isShowing()){
            progressBar.dismiss();
        }
    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    public void onClickShare(View v){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Checkout Tarandeep Singh's resume at : "+url);
        intent.setType("text/plain");
        startActivity(intent);
    }

    public void onClickDownload(View v){
        DownloadPdfTask downloadImageTask = new DownloadPdfTask(progressBar,true){
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(progressBar !=null && progressBar.isShowing())
                    progressBar.dismiss();
                sendAlert();
            }
        };
        downloadImageTask.execute(url);
    }

    public void sendAlert() {
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getParentActivity())
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Resume Downloaded")
                .setContentText("Go to SDCard / Er Tarandeep / Resume.pdf")
                .setAutoCancel(true)
                .setSound(defaultSoundUri);

        NotificationManager notificationManager =
                (NotificationManager) getParentActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

    public static File getParentDirectory(){
        File f= new File(new File("/mnt/sdcard"), "Er Tarandeep");
        if(!f.exists()){
            try {
                f.mkdir();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return f;
    }

}
