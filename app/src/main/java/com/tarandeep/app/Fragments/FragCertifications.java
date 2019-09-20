package com.tarandeep.app.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tarandeep.app.R;
import com.tarandeep.app.Utils.DataPersistence;

/**
 * Created by Tarandeep Singh on 5/25/2017.
 */

public class FragCertifications extends BaseFragment {

    private WebView webView;

    @Override
    public int getLayoutId() {
        return R.layout.frag_certifications;
    }

    @Override
    void getExtras() {

    }

    @Override
    void addListeners() {

    }

    @Override
    void setupBindings() {
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(DataPersistence.getInstance().getHomePageModel().getCertification_url());
        webView.requestFocus();
    }

    @Override
    void initializeViews() {
        webView = (WebView)view.findViewById(R.id.webView);

    }

    public class MyWebViewClient extends WebViewClient {

        /** The web view previous state. */
        private int webViewPreviousState;

        /** The page started. */
        private final int PAGE_STARTED = 0x1;

        /** The page redirected. */
        private final int PAGE_REDIRECTED = 0x2;

        /** The dialog. */
        Dialog dialog = new Dialog(getParentActivity());

        /* (non-Javadoc)
         * @see android.webkit.WebViewClient#shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String)
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        /* (non-Javadoc)
         * @see android.webkit.WebViewClient#onPageStarted(android.webkit.WebView, java.lang.String, android.graphics.Bitmap)
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            webViewPreviousState = PAGE_STARTED;

            if (dialog == null || !dialog.isShowing())
                dialog = ProgressDialog.show(getParentActivity(), "",
                        "Loading Please Wait", true, true,
                        new DialogInterface.OnCancelListener() {

                            @Override
                            public void onCancel(DialogInterface dialog) {
                                // do something
                            }
                        });
        }

        /* (non-Javadoc)
         * @see android.webkit.WebViewClient#onPageFinished(android.webkit.WebView, java.lang.String)
         */
        @Override
        public void onPageFinished(WebView view, String url) {

            if (webViewPreviousState == PAGE_STARTED) {
                if(dialog!=null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                dialog = null;

            }

        }
    }
}
