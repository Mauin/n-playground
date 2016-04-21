package com.mramin.nplayground;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;

import java.util.UUID;

/**
 * TODO: JAVADOC
 */
public class NotificationManager {

    public static final String KEY_GROUP = "group";
    public static final String KEY_TEXT_REPLY = "key_text_reply";
    private static final int ID_QUICK_REPLY = 2001;

    private static NotificationCompat.Builder baseNotification(Context context, String title, String message) {
        return new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher);
    }

    public static void makeSummaryNotification(Context context) {
        NotificationCompat.Builder builder = baseNotification(context, "Why hello there!", UUID.randomUUID().toString())
                .setGroup(KEY_GROUP)
                .setGroupSummary(true);
        getNotificationManager(context).notify(1001, builder.build());

    }

    public static void makeNotification(Context context, int i) {
        NotificationCompat.Builder builder = baseNotification(context, "Why hello there!", UUID.randomUUID().toString())
                .setGroup(KEY_GROUP);
        getNotificationManager(context).notify(i, builder.build());
    }

    private static NotificationManagerCompat getNotificationManager(Context context) {
        return NotificationManagerCompat.from(context);
    }

    public static void makeReplyNotification(Context context) {
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel("Reply")
                .build();

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);

        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "Reply", pendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        NotificationCompat.Builder builder = baseNotification(context, "Quick reply", "You can reply to this...")
                .addAction(action);

        getNotificationManager(context).notify(ID_QUICK_REPLY, builder.build());
    }

    public static void removeReplyNotification(Context context) {
        getNotificationManager(context).cancel(ID_QUICK_REPLY);
    }
}
