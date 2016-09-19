package com.cs175.chenyulong.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        Intent intent = getIntent();

        TextView ruleTitle = (TextView)findViewById(R.id.rule_title);
        TextView ruleContent = (TextView)findViewById(R.id.rule_content);

        int titleId = R.string.rule_title;
        int contentId = R.string.rules;

        ruleTitle.setText(titleId);
        ruleContent.setText(contentId);
    }
}
