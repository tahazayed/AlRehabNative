package com.alrehablife.alrehab.JSON;

import android.os.AsyncTask;

import com.alrehablife.alrehab.BusinessEntities.Story;
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

public class StoriesJSONHandler extends AsyncTask<String, String, List<Story>> {

    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_TITLE = "Title";
    public static final String COLUMN_BODY = "Body";
    public static final String COLUMN_PUBLISHDATE = "PublishDate";
    public static final String COLUMN_EXPIRATIONDATE = "ExpirationDate";
    public static final String COLUMN_CATEGORY = "Category";
    public static final String COLUMN_ISPRIVATE = "IsPrivate";
    public static final String COLUMN_IMAGEURL = "ImageUrl";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_ISFEATURED = "IsFeatured";
    public static final String COLUMN_ISCOMMUNICATIONMESSAGE = "IsCommunicationMessage";
    public static final String COLUMN_STORYTIMESTAMP = "TimeStamp";
    private final StoriesJSONHandlerClient mClient;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public StoriesJSONHandler(StoriesJSONHandlerClient client) {
        mClient = client;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected List<Story> doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        List<Story> StoryList = new ArrayList<>();
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
                    Date _publishdate = dateFormat.parse(finalObject.getString(COLUMN_PUBLISHDATE));
                    Date _expirationdate = dateFormat.parse("2050-04-16T00:00:00");
                    if (!finalObject.getString(COLUMN_EXPIRATIONDATE).equals("null")) {
                        _expirationdate = dateFormat.parse(finalObject.getString(COLUMN_EXPIRATIONDATE));
                    }
                    String _category = finalObject.getString(COLUMN_CATEGORY);
                    boolean _isprivate = Boolean.parseBoolean(finalObject.getString(COLUMN_ISPRIVATE));
                    String _imageUrl = finalObject.getString(COLUMN_IMAGEURL);
                    String _description = finalObject.getString(COLUMN_DESCRIPTION);
                    boolean _isfeatured = Boolean.parseBoolean(finalObject.getString(COLUMN_ISFEATURED));
                    boolean _iscommunicationmessage = Boolean.parseBoolean(finalObject.getString(COLUMN_ISCOMMUNICATIONMESSAGE));
                    String _storytimestamp = finalObject.getString(COLUMN_STORYTIMESTAMP);
                    //boolean _isbookmarked = false;
                    StoryList.add(new Story(_id,
                            _title,
                            _body,
                            _publishdate,
                            _expirationdate,
                            _category,
                            _isprivate,
                            _imageUrl,
                            _description,
                            _isfeatured,
                            _iscommunicationmessage,
                            _storytimestamp,
                            false));

//
//                    List<Story.Cast> castList = new ArrayList<>();
//                    for(int j=0; j<finalObject.getJSONArray("cast").length(); j++){
//                        Story.Cast cast = new Story.Cast();
//                        cast.setName(finalObject.getJSONArray("cast").getJSONObject(j).getString("name"));
//                        castList.add(cast);
//                    }


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
        return StoryList;
    }

    @Override
    protected void onPostExecute(List<Story> result) {

        mClient.onStoriesJSONHandlerClientResult(result);


    }

    public interface StoriesJSONHandlerClient {
        void onStoriesJSONHandlerClientResult(List<Story> result);
    }
}
