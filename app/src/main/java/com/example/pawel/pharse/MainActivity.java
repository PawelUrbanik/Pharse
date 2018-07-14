package com.example.pawel.pharse;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String initialText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Views */
        Button copyButton = (Button) findViewById(R.id.copyButton);
        final Button findButton = (Button) findViewById(R.id.findPhrase);
        Button resetButton = (Button) findViewById(R.id.resetButton);
        textView = (TextView) findViewById(R.id.textView);
        final TextView messageTextView = (TextView) findViewById(R.id.message);

        /*Variables */
        String phrase;



        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                String pasteData= "";
                ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);
                pasteData = item.getText().toString();
                textView.setText(pasteData);
                messageTextView.setText("Click find pfrase");
                initialText= textView.getText().toString();

            }
        });

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("What find?");
                final EditText findPhrase = new EditText(MainActivity.this);
                builder.setView(findPhrase);
                builder.setPositiveButton("Find", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    String phrase  = findPhrase.getText().toString();
                    if (!phrase.equals(""))
                    {
                        messageTextView.setText(findPhrase(phrase));
                    }
                    else {
                        textView.setText(initialText);
                        messageTextView.setText("No phrase selected");
                    }

                    }
                });
                builder.show();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageTextView.setText("Copy text from clipboard");
                textView.setHint("Copy text from clipboard");
            }
        });
    }

    private String findPhrase(String phrase) {

        String result;

        // Initial content
        SpannableString content = new SpannableString(initialText);

        //First phrase in text
        int index = initialText.indexOf(phrase);

        //counter of phrase
        int phraseCounter = 0;


        while (index >= 0)
        {
            phraseCounter++;

            content.setSpan(new BackgroundColorSpan(Color.YELLOW), index, index + phrase.length(), 0 );
            index = initialText.indexOf(phrase, index+1);
        }

        textView.setText(content);
        if (phraseCounter >= 1)
        {
            return String.format("Found %d phrases", phraseCounter);
        }
        else
        {
            return "Phrase not found";
        }
    }
}
