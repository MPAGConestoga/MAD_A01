package com.github.mpagconestoga.mad_a01;


import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mpagconestoga.mad_a01.objects.SwipeButton;
import com.github.mpagconestoga.mad_a01.viewmodel.SettingsViewModel;

public class SettingsActivity extends AppCompatActivity {
    private WebView webView;
    private Button deleteDataButton;
    private SwipeButton swipeButton;
    private TextView confirmationText;
    private SettingsViewModel settingsViewModel;
    private boolean deleted = false;
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        swipeButton = findViewById(R.id.swipeButton);
        confirmationText = findViewById(R.id.delete_data_textview);
        confirmationText.setVisibility(View.GONE);
        swipeButton.setVisibility(View.GONE);
        deleteDataButton = findViewById(R.id.deleteDataButton);
        deleteDataButton.setOnClickListener(new DeleteDataClickListener());
        settingsViewModel = new SettingsViewModel(this.getApplication());
        /*WebView myWebView = (WebView) findViewById(R.id.web);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("http://www.example.com");*/



    }

    public void deleteAllData(){
        settingsViewModel.deleteAllData();
    }

    public void hideSwipeBar(){
        swipeButton.setVisibility(View.GONE);
    }

    private class DeleteDataClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            if(!deleted) {
                swipeButton.setVisibility(View.VISIBLE);
                confirmationText.setVisibility(View.VISIBLE);
            }
            else {
                swipeButton.collapseButton();
            }
            deleted = true;



        }
    }
}



