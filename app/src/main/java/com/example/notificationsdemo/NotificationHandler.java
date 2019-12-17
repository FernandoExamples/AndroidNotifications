package com.example.notificationsdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

public class NotificationHandler extends ContextWrapper {

    private NotificationManager manager;

    public static final String CHANNEL_HIGH_ID = "1";
    private final String CHANNEL_HAIGH_NAME = "HIGH CHANNEL";

    public static final String CHANNEL_LOW_ID = "2";
    private final String CHANNEL_LOW_NAME = "LOW CHANNEL";

    public NotificationHandler(Context context) {
        super(context);
        createChannels();
    }

    public NotificationManager getManeger() {
        if (manager == null)
            manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        return manager;
    }

    private void createChannels() {
        if (Build.VERSION.SDK_INT >= 26) {

            NotificationChannel highChannel = new NotificationChannel(CHANNEL_HIGH_ID, CHANNEL_HAIGH_NAME, NotificationManager.IMPORTANCE_HIGH);

            //Extra configurations
            highChannel.enableLights(true);
            highChannel.setLightColor(Color.YELLOW);
            highChannel.setShowBadge(true); //El puntito en el icono de la aplicacion
            highChannel.enableVibration(true);
            highChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

//            highChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 100});
//            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            highChannel.setSound(soundUri, null);


            NotificationChannel lowChannel = new NotificationChannel(CHANNEL_LOW_ID, CHANNEL_LOW_NAME, NotificationManager.IMPORTANCE_LOW);
            lowChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            getManeger().createNotificationChannel(highChannel);
            getManeger().createNotificationChannel(lowChannel);
        }
    }

    public Notification.Builder createNotification(String title, String message, boolean isHighImportance, PendingIntent contentIntent) {
        if (Build.VERSION.SDK_INT >= 26) { //API OREO DESDE DONDE SE IMPLEMENTARON LOS CANALES DE NOTIFICACION
            if (isHighImportance)
                return createNotificationWithChannels(title, message, CHANNEL_HIGH_ID, contentIntent);
            else
                return createNotificationWithChannels(title, message, CHANNEL_LOW_ID, contentIntent);
        } else
            return createNotificationWithouthChannels(title, message, contentIntent);
    }

    private Notification.Builder createNotificationWithChannels(String title, String message, String channelID,
                                                                PendingIntent contentIntent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Notification.Action action = new Notification.Action.Builder(
                    Icon.createWithResource(this, android.R.drawable.ic_menu_send), "See details", contentIntent).build();


            return new Notification.Builder(getApplicationContext(), channelID)
                    .setContentTitle(title)
                    .setContentText(message)
                    .addAction(action)
                    .setSmallIcon(android.R.drawable.stat_notify_chat)
                    .setAutoCancel(true);
        }

        return null;
    }

    private Notification.Builder createNotificationWithouthChannels(String title, String message, PendingIntent contentIntent) {

        Notification.Action action = new Notification.Action.Builder(
                android.R.drawable.ic_menu_send, "See details", contentIntent).build();

        return new Notification.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(message)
                .addAction(action)
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setAutoCancel(true);
    }
}
