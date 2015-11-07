package com.alrehablife.alrehab;

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

    }

    public class UpdateServiceBinder extends Binder {
        UpdateService getService() {
            return UpdateService.this;
        }
    }
}


