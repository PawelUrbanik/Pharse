package com.example.pawel.pharse;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button copyButton = (Button) findViewById(R.id.copyButton);
        Button findButton = (Button) findViewById(R.id.findPhrase);
        Button resetButton = (Button) findViewById(R.id.resetButton);

        final TextView textView = (TextView) findViewById(R.id.textView);


        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String pasteData= "";
                ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);
                pasteData = (String) item.getText();
                textView.setText(pasteData);

            }
        });
    }
}
