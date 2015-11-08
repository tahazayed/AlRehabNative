package com.alrehablife.alrehab.JSON;


import android.os.AsyncTask;

import com.alrehablife.alrehab.BusinessEntities.Event;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsJSONHandler extends AsyncTask<String, String, List<Event>> {


    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_TITLE = "Title";
    public static final String COLUMN_BODY = "Body";
    public static final String COLUMN_EVENTDATE = "EventDate";
    public static final String COLUMN_EXPIRATIONDATE = "ExpirationDate";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_CATEGORY = "Category";
    public static final String COLUMN_ISPRIVATE = "IsPrivate";
    public static final String COLUMN_IMAGEURL = "ImageUrl";

    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_TELEPHONE = "Telephone";
    public static final String COLUMN_EVENTTIMESTAMP = "TimeStamp";
    public static final String COLUMN_DURATION = "Duration";
    public static final String COLUMN_TICKETPRICE = "TicketPrice";
    public static final String COLUMN_PLACENAME = "PlaceName";

    private final EventsJSONHandlerClient mClient;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public EventsJSONHandler(EventsJSONHandlerClient client) {
        mClient = client;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected List<Event> doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        List<Event> EventList = new ArrayList<>();
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String finalJson = buffer.toString();

            //JSONObject parentObject = new JSONObject(finalJson);
            // JSONArray parentArray = parentObject.getJSONArray("stories");
            JSONArray parentArray = new JSONArray(finalJson);


            Gson gson = new Gson();
            for (int i = 0; i < parentArray.length(); i++) {

                JSONObject finalObject = parentArray.getJSONObject(i);
                try {
                    int _id = finalObject.getInt(COLUMN_ID);
                    String _title = finalObject.getString(COLUMN_TITLE);
                    String _body = finalObject.getString(COLUMN_BODY);
                    Date _eventdate = dateFormat.parse(finalObject.getString(COLUMN_EVENTDATE));
                    Date _expirationdate = dateFormat.parse("2050-04-16T00:00:00");
                    if (!finalObject.getString(COLUMN_EXPIRATIONDATE).equals("null")) {
                        _expirationdate = dateFormat.parse(finalObject.getString(COLUMN_EXPIRATIONDATE));
                    }
                    String _category = finalObject.getString(COLUMN_CATEGORY);
                    boolean _isprivate = Boolean.parseBoolean(finalObject.getString(COLUMN_ISPRIVATE));
                    String _imageUrl = finalObject.getString(COLUMN_IMAGEURL);
                    String _description = finalObject.getString(COLUMN_DESCRIPTION);

                    String _address = finalObject.getString(COLUMN_ADDRESS);
                    String _duration = finalObject.getString(COLUMN_DURATION);
                    String _placename = finalObject.getString(COLUMN_PLACENAME);
                    String _telephone = finalObject.getString(COLUMN_TELEPHONE);
                    float _ticketprice = Float.parseFloat(finalObject.getString(COLUMN_TICKETPRICE));

                    String _eventtimestamp = finalObject.getString(COLUMN_EVENTTIMESTAMP);

                    EventList.add(new Event(_id,
                            _title,
                            _body,
                            _eventdate,
                            _expirationdate,
                            _isprivate,
                            _imageUrl,
                            _description,
                            _category,
                            _address,
                            _telephone,
                            _duration,
                            _ticketprice,
                            _eventtimestamp,
                            _placename,
                            false,
                            false));

                } catch (Exception ex) {
                    String error = ex.getMessage();
                }
            }


        }
//        catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return EventList;
    }

    @Override
    protected void onPostExecute(List<Event> result) {

        mClient.onEventsJSONHandlerClientResult(result);


    }

    public interface EventsJSONHandlerClient {
        void onEventsJSONHandlerClientResult(List<Event> result);
    }
}
