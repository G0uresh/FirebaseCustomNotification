# FirebaseCustomNotification
The Firebase Cloud Messaging Android Quickstart app demonstrates registering an Android app for notifications and handling the receipt of a message. InstanceID allows easy registration while FirebaseMessagingService and FirebaseInstanceIDService enable token refreshes and message handling on the client.

# Getting Start
- [Add Firebase to your Android Project](https://firebase.google.com/docs/android/setup).
[Firebase]: https://firebase.google.com/docs/android/setup
Run the sample

# Sending Notifications
When the app is in the background and if you send notification using Firebase console then default notification will be    generated which can be different from your custom notification. This happens because onMessageReceived method is not getting call.
If you want custom notification (onMessageReceived) method call you should change the payload.
# Send to a single device
You can test using REST client 

SERVER KEY
1. Go to Firebase console
2. click the settings icon/cog wheel next to your project name at the top of the new Firebase Console
3. Click Project settings
4. Click on the Cloud Messaging tab
5. The key is right under Server Key

FCM Token
The FirebaseInstanceId.getInstance().getToken() method will give a FCM token.
When you install a application a FCM token is printed in logs.

Header
{
Content-Type: application/json
Authorization:key= SERVER KEY
}
payload
{
  "to": "FCM Token",
  "priority": "high",
  "data" : {
      "body" : "From two days I am trying to get like this. But I failed. Please any one suggest me how to get image in push                  notification. Thank you in advance.",
      "title": "Portugal vs. Denmark",
      "icon" : "myicon",
      "click_action": "NotificationRecieverService",
      "imageurl":"http://www.planwallpaper.com/static/images/Child-Girl-with-Sunflowers-Images.jpg"
    }
}

# Send to a topic
Header
{
Content-Type: application/json
Authorization:key= SERVER KEY
}
payload
{
  "to": "/topics/[your topic name]",
  "priority": "high",
  "data" : {
      "body" : "From two days I am trying to get like this. But I failed. Please any one suggest me how to get image in push                  notification. Thank you in advance.",
      "title": "Portugal vs. Denmark",
      "icon" : "myicon",
      "click_action": "NotificationRecieverService",
      "imageurl":"http://www.planwallpaper.com/static/images/Child-Girl-with-Sunflowers-Images.jpg"
    }
}
 Topic name example:
 "to": "/topics/games".
 
# Support
- [Firebase Support](https://firebase.google.com/support/)
