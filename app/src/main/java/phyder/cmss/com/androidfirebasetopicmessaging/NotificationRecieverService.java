package phyder.cmss.com.androidfirebasetopicmessaging;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;

import static android.support.v7.appcompat.R.attr.icon;

/**
 * Created by Gouresh on 22/11/16
 *
 */

public class NotificationRecieverService extends FirebaseMessagingService {

    private static final String TAG = NotificationRecieverService.class.getName();
    private Bitmap remote_picture;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Handle data payload of FCM messages.
        Log.d(TAG, "FCM Message Id: " + remoteMessage.getMessageId());
        Log.d(TAG, "FCM Notification Message: " +
                remoteMessage.getNotification());
        Log.d(TAG, "FCM Data Message: " + remoteMessage.getData());

         String imageurl = remoteMessage.getData().get("imageurl");
         String title = remoteMessage.getData().get("title");
         String body = remoteMessage.getData().get("body");

        //displayBigTextNotification(title,body,imageurl);

        //displayBigPictureNotification(title,body,imageurl);

//        displayCustomNotification(title,body,imageurl);

        // Custom notification Using picasso for image upload
        //Broadcast Receiver
        broadcastIntent(title,body,imageurl);
    }


    public void broadcastIntent(String title,String body,String imageurl)
    {
        Intent intent = new Intent();
        intent.putExtra("title",title);
        intent.putExtra("body",body);
        intent.putExtra("imageurl",imageurl);
        intent.setAction("com.example.SendBroadcast");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }

   // Without Using piacasso for image upload
    private void displayCustomNotification(String title,String body,String imageurl) {

        // Using RemoteViews to bind custom layouts into Notification
        RemoteViews remoteViews = new RemoteViews(getPackageName(),
                R.layout.customnotification);

        // Open NotificationView Class on Notification Click
        Intent intent = new Intent(this, PostLoginActivity.class);
        // Send data to NotificationView Class
         intent.putExtra("title", title);
         intent.putExtra("text", body);
        // Open NotificationView.java Activity
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        try {
            remote_picture = BitmapFactory.decodeStream((InputStream) new URL(imageurl).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Locate and set the Image into customnotificationtext.xml ImageViews
        remoteViews.setImageViewResource(R.id.leftimageicon,R.drawable.fteam);
        remoteViews.setImageViewBitmap(R.id.centerimageicon,remote_picture);
        // Locate and set the Text into customnotificationtext.xml TextViews
        remoteViews.setTextViewText(R.id.title ,title);
        remoteViews.setTextViewText(R.id.body ,body);

        Builder builder = new Builder(this)
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setTicker(title)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViews)
                .setContentIntent(pIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setSmallIcon(R.mipmap.ic_launcher);

        //Notification Id
        int notificationId = (int) SystemClock.currentThreadTimeMillis();

        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(notificationId , builder.build());
    }

    public void displayBigPictureNotification(String title,String body,String imageurl)
        {

            NotificationCompat.BigPictureStyle notiStyle = new NotificationCompat.BigPictureStyle();
            notiStyle.setSummaryText(body);

            try {
                remote_picture = BitmapFactory.decodeStream((InputStream) new URL(imageurl).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
            notiStyle.bigPicture(remote_picture);
            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent contentIntent = null;

            //Start activity when user taps on notification.
            Intent gotoIntent = new Intent(this, MainActivity.class);
            contentIntent = PendingIntent.getActivity(this,
                    (int) (Math.random() * 100), gotoIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            Builder mBuilder = new Builder(
                    this);
            Notification notification = mBuilder.setSmallIcon(icon).setTicker(title).setWhen(0)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                    .setContentIntent(contentIntent)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText(body)
                    .setStyle(notiStyle).build();

            /* NotificationCompat.BigPictureStyle bigPicStyle = new NotificationCompat.BigPictureStyle();
        bigPicStyle.bigPicture(bitmap);
        bigPicStyle.setBigContentTitle(title);
        notificationBuilder.setStyle(bigPicStyle);*/

            //This will generate seperate notification each time server sends.
            int notificationId = (int) SystemClock.currentThreadTimeMillis();

            notificationManager.notify(notificationId, notification);
           // startForeground(notificationId, notification);
        }



    private void displayBigTextNotification(String title,String body,String imageurl) {

    PendingIntent pendingIntent = null;


                Intent intent = new Intent(this, PostLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        int notificationId = (int) Calendar.getInstance().getTimeInMillis();

        Builder notificationBuilder =  new Builder(this);

        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setColor(Color.parseColor("#0054A5"));
        notificationBuilder.setContentText(body);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setNumber(notificationId);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(body));




        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notificationId, notificationBuilder.build());

}


    }



