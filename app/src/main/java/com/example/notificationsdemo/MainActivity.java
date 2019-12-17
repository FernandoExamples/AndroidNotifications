package com.example.notificationsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editTextTitle)
    EditText etTitle;

    @BindView(R.id.editTextMessage)
    EditText etMessage;

    @BindView(R.id.buttonNotif)
    Button btnNotif;

    @BindView(R.id.switchImportance)
    Switch swImportance;

    NotificationHandler notificationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        notificationHandler = new NotificationHandler(this);

        swImportance.setTextOn("High Importance");
        swImportance.setTextOff("Low Importance");
    }

    @OnClick(R.id.buttonNotif)
    public void click(){
        sendNotification();
    }

    @OnCheckedChanged(R.id.switchImportance)
    public void onChekedchange(CompoundButton buttonView, boolean isChecked){
        if(isChecked)
            swImportance.setText(swImportance.getTextOn());
        else
            swImportance.setText(swImportance.getTextOff());
    }

    private void sendNotification(){
        boolean highImportance = swImportance.isChecked();
        String title = etTitle.getText().toString();
        String message = etMessage.getText().toString();

        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)){
            Notification.Builder builder = notificationHandler.createNotification(title, message, highImportance);
            notificationHandler.getManeger().notify(1, builder.build());
        }
    }
}
