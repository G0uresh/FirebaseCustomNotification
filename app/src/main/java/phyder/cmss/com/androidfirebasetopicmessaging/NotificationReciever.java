package phyder.cmss.com.androidfirebasetopicmessaging;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by Gouresh on 25/1/17
 *
 */


public class NotificationReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        String title = intent.getStringExtra("title");
        String body = intent.getStringExtra("body");
        String imageurl = intent.getStringExtra("imageurl");

          // Using RemoteViews to bind custom layouts into Notification
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.customnotification);

        // Open NotificationView Class on Notification Click
        Intent intent2 = new Intent(context, PostLoginActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent2,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // Locate and set the Image into customnotificationtext.xml ImageViews
        remoteViews.setImageViewResource(R.id.leftimageicon,R.drawable.fteam);
        // Locate and set the Text into customnotificationtext.xml TextViews
        remoteViews.setTextViewText(R.id.title ,title);
        remoteViews.setTextViewText(R.id.body ,body);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setPriority(android.app.Notification.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setTicker(title)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViews)
                .setContentIntent(pIntent)
                .setDefaults(android.app.Notification.DEFAULT_SOUND)
                .setSmallIcon(R.mipmap.ic_launcher);

        int notificationId = (int) android.os.SystemClock.currentThreadTimeMillis();

        //set Image Using picasso
        Picasso.with(context)
                .load(imageurl)
                .into(remoteViews, R.id.centerimageicon, notificationId, builder.build());


        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(notificationId , builder.build());


    }

}
