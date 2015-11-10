package com.alrehablife.alrehab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.alrehablife.alrehab.BusinessEntities.Event;
import com.alrehablife.alrehab.DB.EventsDBHandler;

import java.io.InputStream;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Event> Events;
    ListView EventsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabNews = tabHost.newTabSpec("news");
        tabNews.setContent(R.id.tabNewsContent);
        tabNews.setIndicator(getString(R.string.tab_news));
        tabHost.addTab(tabNews);


        TabHost.TabSpec tabEvents = tabHost.newTabSpec("events");
        tabEvents.setContent(R.id.tabEventsContent);
        tabEvents.setIndicator(getString(R.string.tab_events));
        tabHost.addTab(tabEvents);

        TabHost.TabSpec tabCommunicaionMessages = tabHost.newTabSpec("communicaion_messages");
        tabCommunicaionMessages.setContent(R.id.tabCommunicaionMessages);
        tabCommunicaionMessages.setIndicator(getString(R.string.tab_communicaion_messages));
        tabHost.addTab(tabCommunicaionMessages);

        //Event currentContact = Events.get(position);

        final EventsDBHandler eventHandler = new EventsDBHandler(getApplicationContext());
        //Events = eventHandler.getAllEvents();

        /*new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Events = eventHandler.getAllEvents();
                if(Events.size() > 0)
                {
                    populateList(Events);
                }
            }
        }, 0,500);*/

        EventsListView = (ListView) findViewById(R.id.listView);
        Events = eventHandler.getAllEvents();
        if(Events.size() > 0)
        {populateList(Events);}

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_latest_news_and_events) {
            // Handle the camera action
        }
//        else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //my methods

    private void populateList(List<Event> lstOfEvents)
    {

        ArrayAdapter<Event> adapter = new EventsAdapter(lstOfEvents);
        EventsListView.setAdapter(adapter);
    }

    private class EventsAdapter extends ArrayAdapter<Event>
    {
        //listOfEvents
        public EventsAdapter(List<Event> events)
        {

            super(MainActivity.this, R.layout.listview_events, events);
            //listOfEvents = events
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            //event = listOfEvent.get(pos);
            if(convertView == null)
            {
                convertView = getLayoutInflater().inflate(R.layout.listview_events, parent, false);
            }


            ImageView img = (ImageView) convertView.findViewById(R.id.image);
            new DownloadImageTask(img).execute(Events.get(0).get_imageUrl());

            TextView title = (TextView)convertView.findViewById(R.id.title);
            title.setText(Events.get(0).get_title());
            TextView time = (TextView)convertView.findViewById(R.id.time);
            time.setText(Events.get(0).get_timestamp());
            TextView content = (TextView)convertView.findViewById(R.id.content);
            content.setText(Events.get(0).get_body());

            return convertView;
        }

        private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>
        {
            ImageView bmImage;

            public DownloadImageTask(ImageView bmImage) {
                this.bmImage = bmImage;
            }

            protected Bitmap doInBackground(String... urls) {
                String urldisplay = urls[0];
                Bitmap mIcon11 = null;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return mIcon11;
            }

            protected void onPostExecute(Bitmap result) {
                bmImage.setImageBitmap(result);
            }
        }
    }
}
