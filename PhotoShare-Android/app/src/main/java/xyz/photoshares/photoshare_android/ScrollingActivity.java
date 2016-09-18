package xyz.photoshares.photoshare_android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.emailbtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getSendEmailIntent("tony@sharephoto.me", "", ""));
            }
        });

        FloatingActionButton webbtn = (FloatingActionButton) findViewById(R.id.webbtn);
        webbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.photoshares.xyz"));
                startActivity(browserIntent);
            }
        });
    }

    public Intent getSendEmailIntent(String email,
                                     String subject, String body) {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        try {

            // Explicitly only use Gmail to send
            emailIntent.setClassName("com.google.android.gm",
                    "com.google.android.gm.ComposeActivityGmail");

            emailIntent.setType("text/html");

            // Add the recipients
            if (email != null)
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[] { email });

            if (subject != null)
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        subject);

            if (body != null)
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(body));

            // Add the attachment by specifying a reference to our custom
            // ContentProvider
            // and the specific file of interest
            // emailIntent.putExtra(
            // Intent.EXTRA_STREAM,
            // Uri.parse("content://" + CachedFileProvider.AUTHORITY + "/"
            // + fileName));

            return emailIntent;
            //          myContext.startActivity(emailIntent);
        } catch (Exception e) {
            emailIntent.setType("text/html");

            // Add the recipients
            if (email != null)
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[] { email });

            if (subject != null)
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        subject);

            if (body != null)
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(body));

            //          myContext.startActivity(Intent.createChooser(emailIntent,
            //                  "Share Via"));
            return emailIntent;
        }
    }
}


