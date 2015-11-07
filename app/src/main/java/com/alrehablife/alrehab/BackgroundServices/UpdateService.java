package com.alrehablife.alrehab.BackgroundServices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class UpdateService extends Service {

    private final IBinder updateServiceBinder = new UpdateServiceBinder();

    public UpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return updateServiceBinder;
    }

    public void updateDB() {
        new StoryUpdateJSON().execute("http://test.alrehablife.com/api/stories/?count=3");
    }

    public class UpdateServiceBinder extends Binder {
        public UpdateService getService() {
            return UpdateService.this;
        }
    }
}


