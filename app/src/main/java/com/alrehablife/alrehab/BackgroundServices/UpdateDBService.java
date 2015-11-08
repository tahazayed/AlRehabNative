package com.alrehablife.alrehab.BackgroundServices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.alrehablife.alrehab.BusinessEntities.Event;
import com.alrehablife.alrehab.BusinessEntities.Story;
import com.alrehablife.alrehab.DB.EventsDBHandler;
import com.alrehablife.alrehab.DB.StoriesDBHandler;
import com.alrehablife.alrehab.JSON.EventsJSONHandler;
import com.alrehablife.alrehab.JSON.StoriesJSONHandler;
import com.alrehablife.alrehab.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class UpdateDBService extends Service implements StoriesJSONHandler.StoriesJSONHandlerClient, EventsJSONHandler.EventsJSONHandlerClient {

    private static final String TAG = "UpdateDBService";
    private static long UPDATE_INTERVAL = 3 * 60 * 1000;  //default
    private static Timer timer = new Timer();

    private boolean isRunning = false;

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");

        isRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "Service onStartCommand");

        timer.scheduleAtFixedRate(

                new TimerTask() {

                    public void run() {

                        doServiceWork();

                    }
                }, 1000, UPDATE_INTERVAL);
        Log.i(TAG, "Timer started....");

        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    private void doServiceWork() {

        try {
            new StoriesJSONHandler(UpdateDBService.this).execute(getString(R.string.NewsAPIUrl));
            Log.i(TAG, "StoriesJSONHandler invoked...");
            new EventsJSONHandler(UpdateDBService.this).execute(getString(R.string.EventsAPIUrl));
            Log.i(TAG, "EventsJSONHandler invoked...");
        } catch (Exception e) {
            String error = e.getMessage();
        }

    }

    @Override
    public void onDestroy() {

        isRunning = false;

        if (timer != null) timer.cancel();
        Log.i(TAG, "Timer stopped...");

        Log.i(TAG, "Service onDestroy");
    }

    @Override
    public void onStoriesJSONHandlerClientResult(List<Story> list) {
        Log.i(TAG, "onStoriesJSONHandlerClientResult invoked..." + list.size());
        Story[] lstStories = list.toArray(new Story[list.size()]);
        int size = lstStories.length;
        StoriesDBHandler db = new StoriesDBHandler(getApplicationContext());
        db.markAllStoriesDeleted();
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
        db.deleteStoriesMrkedDeleted();


    }


    @Override
    public void onEventsJSONHandlerClientResult(List<Event> list) {
        Log.i(TAG, "onEventsJSONHandlerClientResult invoked..." + list.size());
        Event[] lstEvents = list.toArray(new Event[list.size()]);
        int size = lstEvents.length;
        EventsDBHandler db = new EventsDBHandler(getApplicationContext());
        db.markAllEventsDeleted();
        for (Event Event : lstEvents) {
            Event oldEvent = db.getEvent(Event.get_id());

            if (oldEvent != null) {
                if (!oldEvent.get_timestamp().equals(Event.get_timestamp())) {
                    Event.set_isbookmarked(oldEvent.get_isbookmarked());
                    db.updateEvent(Event);
                }
                continue;
            }

            db.addEvent(Event);
        }
        db.deleteEventsMrkedDeleted();


    }

}