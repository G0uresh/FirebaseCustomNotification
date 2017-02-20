package phyder.cmss.com.androidfirebasetopicmessaging;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class PostLoginActivity extends AppCompatActivity {

    private static final String TAG = PostLoginActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);

        Button nsubscribe = (Button) findViewById(R.id.newssubscribe);
        Button unsubscribe = (Button) findViewById(R.id.newsunsubscribe);
        Button logout = (Button) findViewById(R.id.logout);
        Button subgames = (Button) findViewById(R.id.sgames);
        Button unsubgames = (Button) findViewById(R.id.ungames);

        // Subscribe To NEWS Topic
        nsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().subscribeToTopic("news");

                Log.d(TAG, "Suscribe to News");
                Toast.makeText(PostLoginActivity.this,"Suscribe to News",Toast.LENGTH_SHORT).show();
            }
        });

        // UnSubscribe To NEWS Topic
        unsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("news");

                Log.d(TAG, "Unsuscribe to News");
                Toast.makeText(PostLoginActivity.this,"Unsuscribe to News",Toast.LENGTH_SHORT).show();
            }
        });


        // Suscribe To Games Topic
        subgames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().subscribeToTopic("games");

                Log.d(TAG, "Suscribe to Games");
                Toast.makeText(PostLoginActivity.this,"Suscribe to Games",Toast.LENGTH_SHORT).show();
            }
        });

        // UnSubscribe To Games Topic
        unsubgames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("games");

                Log.d(TAG, "UnSuscribe to Games");
                Toast.makeText(PostLoginActivity.this,"UnSuscribe to Games",Toast.LENGTH_SHORT).show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "logout");
                Toast.makeText(PostLoginActivity.this,"Logout",Toast.LENGTH_SHORT).show();
                Intent reg = new Intent(PostLoginActivity.this, MainActivity.class);
                startActivity(reg);
            }
        });

    }

    /**
     * Header
     * Content-Type: application/json
     Authorization:key=AIzaSyDgqJ_CxoswS-u5-CeLi5fJG803I3inNGE
     *
     *
     * Payload
     * {
     "to": "/topics/news",
     "priority": "high",
     "data" : {
     "body" : "From two days I am trying to get like this. But I failed. Please any one suggest me how to get image in push notification. Thank you in advance.",
     "title" : "Portugal vs. Denmark",
     "icon" : "myicon",
     "click_action": "NotificationRecieverService",
     "imageurl":"http://www.planwallpaper.com/static/images/Child-Girl-with-Sunflowers-Images.jpg"
     }
     }
     */
}
