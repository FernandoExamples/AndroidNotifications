package com.example.notificationsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;

    @BindView(R.id.textViewMessage)
    TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setIntentValues();
    }

    private void setIntentValues(){
        if(getIntent() != null){
            String title = getIntent().getStringExtra("title");
            String message = getIntent().getStringExtra("message");
            textViewTitle.setText(title);
            textViewMessage.setText(message);
        }
    }
}
