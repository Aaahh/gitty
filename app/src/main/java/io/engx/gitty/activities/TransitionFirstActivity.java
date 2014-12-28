package io.engx.gitty.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import io.engx.gitty.R;
import io.engx.gitty.utils.GUIUtils;


public class TransitionFirstActivity extends Activity {

    private View fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_first);

        // Set explode animation when enter and exit the activity
        GUIUtils.configureWindowEnterExitExplodeTransition(getWindow());

        // Fab Button
        fabButton = findViewById(R.id.fab_button);
        fabButton.setOnClickListener(fabClickListener);
        GUIUtils.configureFab(fabButton);
    }


    View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        Intent i  = new Intent (TransitionFirstActivity.this, TransitionSecondActivity.class);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(TransitionFirstActivity.this,
                Pair.create(fabButton, "fab"));

        startActivity(i, transitionActivityOptions.toBundle());
        }
    };
}
