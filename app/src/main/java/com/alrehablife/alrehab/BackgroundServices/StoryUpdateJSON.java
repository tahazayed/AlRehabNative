package com.alrehablife.alrehab.BackgroundServices;

import android.os.AsyncTask;

import com.alrehablife.alrehab.BusinessEntities.Story;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StoryUpdateJSON extends AsyncTask<String, String, List<Story>> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected List<Story> doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder buffer = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String finalJson = buffer.toString();

            JSONObject parentObject = new JSONObject(finalJson);
            JSONArray parentArray = parentObject.getJSONArray("stories");

            List<Story> StoryList = new ArrayList<>();

            Gson gson = new Gson();
            for (int i = 0; i < parentArray.length(); i++) {
                JSONObject finalObject = parentArray.getJSONObject(i);
                Story Story = gson.fromJson(finalObject.toString(), Story.class);
//                    Story.setMovie(finalObject.getString("movie"));
//                    Story.setYear(finalObject.getInt("year"));
//                    Story.setRating((float) finalObject.getDouble("rating"));
//                    Story.setDirector(finalObject.getString("director"));
//
//                    Story.setDuration(finalObject.getString("duration"));
//                    Story.setTagline(finalObject.getString("tagline"));
//                    Story.setImage(finalObject.getString("image"));
//                    Story.setStory(finalObject.getString("story"));
//
//                    List<Story.Cast> castList = new ArrayList<>();
//                    for(int j=0; j<finalObject.getJSONArray("cast").length(); j++){
//                        Story.Cast cast = new Story.Cast();
//                        cast.setName(finalObject.getJSONArray("cast").getJSONObject(j).getString("name"));
//                        castList.add(cast);
//                    }
//                    Story.setCastList(castList);
                // adding the final object in the list
                StoryList.add(Story);
            }
            return StoryList;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
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
        return null;
    }

    @Override
    protected void onPostExecute(List<Story> result) {
        super.onPostExecute(result);

        // TODO need to set data to the list
    }
}
