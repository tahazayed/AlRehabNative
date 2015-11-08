package com.alrehablife.alrehab.BackgroundServices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.alrehablife.alrehab.JSON.StoriesJSONHandler;
import com.alrehablife.alrehab.R;

public class UpdateService extends Service {

    private final IBinder updateServiceBinder = new UpdateServiceBinder();

    public UpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return updateServiceBinder;
    }

    public void updateDB() {

        new StoriesJSONHandler().execute(getString(R.string.NewsAPIUrl));
    }

    public class UpdateServiceBinder extends Binder {
        public UpdateService getService() {
            return UpdateService.this;
        }
    }
}


