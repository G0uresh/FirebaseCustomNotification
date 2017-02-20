package gaureshghadi.com.FirebaseCustomNotification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


/**
 * Created by Gouresh on 22/11/16
 *
 */

public class FCMTokenGeneratorService extends FirebaseInstanceIdService {

    private static final String TAG = FCMTokenGeneratorService.class.getName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        // Get FCM token
        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "FCM Token: " + token);
    }

}
