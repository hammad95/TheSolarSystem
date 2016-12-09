package com.hammad.solarsystem.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView ratingLink = (TextView) findViewById(R.id.tv_ratingLink);
        Spanned text = Html.fromHtml(
                "<a href='https://play.google.com/store/apps/details?id=com.hammad.solarsystem.app&hl=en'>give it a 5-star rating on Google Play</a>");
        if(ratingLink != null) {
            ratingLink.setMovementMethod(LinkMovementMethod.getInstance());
            ratingLink.setLinkTextColor(getResources().getColor(android.R.color.holo_blue_light));
            ratingLink.setText(text);
        }
    }
}
