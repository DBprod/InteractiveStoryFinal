package com.example.danielbucci.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danielbucci.interactivestory.R;
import com.example.danielbucci.interactivestory.model.Page;
import com.example.danielbucci.interactivestory.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private String name;
    private Stack<Integer> pageStack = new Stack<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        //initialize the views

        storyImageView = (ImageView) findViewById(R.id.storyImageView);
        storyTextView = (TextView) findViewById(R.id.storyTextView);
        choice1Button = (Button) findViewById(R.id.choice1Button);
        choice2Button = (Button) findViewById(R.id.choice2Button);


        Intent intent = getIntent();
        name = intent.getStringExtra(getString(R.string.key_name));
        if (name == null || name.isEmpty()) {
            name = "Friend";
        }
        Log.d(TAG, name);

        story = new Story();
        loadPage(0);
    }

    private void loadPage(int pageNumber) {
        pageStack.push(pageNumber);


        final Page page = story.getPage(pageNumber);
        // set the image for this new page
        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        storyImageView.setImageDrawable(image);
        // set the text
        String pageText = getString(page.getTextId());
        //adds a name to a place holder and doesn't crash if there is no name
        pageText = String.format(pageText, name);
        storyTextView.setText(pageText);

        if (page.isFinalpage()) {
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText(R.string.play_again_button_text);
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadPage(0);
                }
            });
        } else {
            // set the button
            choice1Button.setVisibility(View.VISIBLE);
            choice1Button.setText(page.getpChoice1().getTextId());
            choice1Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int nextPage = page.getpChoice1().getNextPage();
                    loadPage(nextPage);
                }
            });

            choice2Button.setVisibility(View.VISIBLE);
            choice2Button.setText(page.getpChoice2().getTextId());
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int nextPage = page.getpChoice2().getNextPage();
                    loadPage(nextPage);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        pageStack.pop();
        if (pageStack.isEmpty()) {
            super.onBackPressed();
        } else {
            loadPage(pageStack.pop());
        }
    }
}






