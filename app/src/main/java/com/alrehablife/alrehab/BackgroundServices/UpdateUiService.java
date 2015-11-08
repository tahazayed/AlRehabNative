package com.alrehablife.alrehab.BackgroundServices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class UpdateUiService extends Service
{
    public UpdateUiService()
    {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
