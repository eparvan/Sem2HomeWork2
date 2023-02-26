package com.cihcahul.sem2homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private ScrollView scrollView;
    private Button scrollButton;
    private Handler handler;
    private boolean scrollEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.text_view);
        scrollView = findViewById(R.id.scroll_view);
        scrollButton = findViewById(R.id.button_scroll);

        handler = new Handler();
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        textView.setText(text);

        scrollEnabled = true;

        scrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollEnabled = !scrollEnabled;
                if (scrollEnabled) {
                    scrollButton.setText("Opriți scroll-ul automat");
                    scrollText();
                } else {
                    scrollButton.setText("Porniți scroll-ul automat");
                    handler.removeCallbacksAndMessages(null);
                }
            }
        });
    }

    private void scrollText() {
        int scrollAmount = 1;
        int delayMillis = 10;

        int textHeight = textView.getHeight();
        int scrollHeight = scrollView.getHeight();

        int scrollDirection = (textView.getScrollY() >= (textHeight - scrollHeight)) ? -1 : 1;

        int newScrollY = textView.getScrollY() + (scrollAmount * scrollDirection);

        if (newScrollY >= textHeight - scrollHeight) {
            newScrollY = textHeight - scrollHeight;
            scrollDirection = -1;
        } else if (newScrollY <= 0) {
            newScrollY = 0;
            scrollDirection = 1;
        }

        textView.scrollTo(0, newScrollY);

           if (scrollEnabled) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scrollText();
                }
            }, delayMillis);
        }
    }
}