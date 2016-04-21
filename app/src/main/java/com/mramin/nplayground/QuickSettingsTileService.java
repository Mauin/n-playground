package com.mramin.nplayground;

import android.service.quicksettings.TileService;
import android.util.Log;
import android.widget.Toast;

/**
 * TODO: JAVADOC
 */
public class QuickSettingsTileService extends TileService {

    @Override
    public int onTileAdded() {
        Log.e("TEST", "Tile was addee to the quick settings");
        return super.onTileAdded();
    }

    @Override
    public void onTileRemoved() {
        Log.e("TEST", "Tile was removed from the quick settings");
        super.onTileRemoved();
    }

    @Override
    public void onStartListening() {
        Log.e("TEST", "Tile is visible to the user and should update it's content");
        super.onStartListening();
    }

    @Override
    public void onStopListening() {
        Log.e("TEST", "Tile is no longer visible, updating can stop");
        super.onStopListening();
    }

    @Override
    public void onClick() {
        super.onClick();
        Toast.makeText(QuickSettingsTileService.this, "Hello from your quick settings tile!", Toast.LENGTH_LONG).show();
    }
}
