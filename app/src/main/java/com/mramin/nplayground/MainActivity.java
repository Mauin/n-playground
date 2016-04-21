package com.mramin.nplayground;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import static com.mramin.nplayground.NotificationManager.KEY_TEXT_REPLY;
import static com.mramin.nplayground.NotificationManager.makeNotification;
import static com.mramin.nplayground.NotificationManager.makeSummaryNotification;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.notification_group).setOnClickListener(view -> showGroupedNotifications());
        findViewById(R.id.notification_reply).setOnClickListener(view -> showReplyNotification());

        handleIntent(getIntent());
        createShortcuts();
    }

    private void createShortcuts() {
        ShortcutManager shortcutManager = (ShortcutManager) getSystemService(SHORTCUT_SERVICE);
        ShortcutInfo info = new ShortcutInfo.Builder(this)
                .setIcon(Icon.createWithResource(this, R.drawable.wave))
                .setText("Hey N!")
                .setTitle("Shortcut")
                .setId("1")
                .setWeight(1)
                .setActivityComponent(new ComponentName(this, MainActivity.class.getName()))
                .setIntent(new Intent(this, MainActivity.class))
                .build();

        shortcutManager.addDynamicShortcut(info);
    }

    private void handleIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        Bundle bundle = RemoteInput.getResultsFromIntent(intent);
        if (bundle == null) {
            return;
        }

        String replyText = bundle.getString(KEY_TEXT_REPLY);
        ((TextView) findViewById(R.id.reply_text)).setText(String.format("Text entered: %s", replyText));

        // You have to remove the notification once you're finished processing it
        NotificationManager.removeReplyNotification(this);
    }

    private void showReplyNotification() {
        NotificationManager.makeReplyNotification(this);
    }

    private void showGroupedNotifications() {
        for (int i = 0; i < 3; i++) {
            makeNotification(this, i);
        }
        makeSummaryNotification(this);
    }
}
