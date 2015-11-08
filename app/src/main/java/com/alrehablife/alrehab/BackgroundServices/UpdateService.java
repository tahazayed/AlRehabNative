package com.alrehablife.alrehab.BackgroundServices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.alrehablife.alrehab.BusinessEntities.Story;
import com.alrehablife.alrehab.DB.StoriesDBHandler;
import com.alrehablife.alrehab.JSON.StoriesJSONHandler;
import com.alrehablife.alrehab.R;

import java.util.List;

public class UpdateService extends Service implements StoriesJSONHandler.StoriesJSONHandlerClient {

    private final IBinder updateServiceBinder = new UpdateServiceBinder();

    public UpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return updateServiceBinder;
    }

    public void updateDB() {

        new StoriesJSONHandler(this).execute(getString(R.string.NewsAPIUrl));
    }

    @Override
    public void onStoriesJSONHandlerClientResult(List<Story> list) {
        Story[] lstStories = list.toArray(new Story[list.size()]);
        int size = lstStories.length;
        StoriesDBHandler db = new StoriesDBHandler(getApplicationContext());
        for (Story story : lstStories) {
            Story oldStory = db.getStory(story.get_id());

            if (oldStory != null) {
                if (!oldStory.get_timestamp().equals(story.get_timestamp())) {
                    story.set_isbookmarked(oldStory.get_isbookmarked());
                    db.updateStory(story);
                }
                continue;
            }

            db.addStory(story);
        }


    }


    public class UpdateServiceBinder extends Binder {
        public UpdateService getService() {
            return UpdateService.this;
        }
    }
}


