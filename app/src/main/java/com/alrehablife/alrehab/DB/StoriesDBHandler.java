package com.alrehablife.alrehab.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alrehablife.alrehab.BusinessEntities.Story;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoriesDBHandler extends SQLiteOpenHelper {

    public static final String TABLE_STORIES = "stories";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_BODY = "body";
    public static final String COLUMN_PUBLISHDATE = "publishdate";
    public static final String COLUMN_EXPIRATIONDATE = "expirationdate";

    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_ISPRIVATE = "isprivate";
    public static final String COLUMN_IMAGEURL = "imageurl";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ISFEATURED = "isfeatured";
    public static final String COLUMN_ISCOMMUNICATIONMESSAGE = "iscommunicationmessage";
    public static final String COLUMN_STORYTIMESTAMP = "storytimestamp";
    public static final String COLUMN_ISBOOKMARKED = "isbookmarked";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "storiesdb.db";

    //We need to pass database information along to superclass
    public StoriesDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_STORIES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                COLUMN_TITLE + " nvarchar(500) NOT NULL," +

                COLUMN_BODY + " nvarchar(max) NOT NULL," +

                COLUMN_PUBLISHDATE + " datetime NOT NULL," +

                COLUMN_EXPIRATIONDATE + " datetime NULL," +

                COLUMN_CATEGORY + " nvarchar(100) NOT NULL," +

                COLUMN_ISPRIVATE + " BOOLEAN NOT NULL," +

                COLUMN_IMAGEURL + " nvarchar(500) NOT NULL," +

                COLUMN_DESCRIPTION + " nvarchar(500) NOT NULL," +

                COLUMN_ISFEATURED + " BOOLEAN NOT NULL," +

                COLUMN_ISCOMMUNICATIONMESSAGE + " BOOLEAN NOT NULL," +
                COLUMN_STORYTIMESTAMP + " NUMERIC NOT NULL," +
                COLUMN_ISBOOKMARKED + " BOOLEAN NOT NULL" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORIES);
        onCreate(db);
    }

    //Add a new row to the database
    public void addStory(Story story) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, story.get_id());
        values.put(COLUMN_TITLE, story.get_title());
        values.put(COLUMN_BODY, story.get_body());
        values.put(COLUMN_PUBLISHDATE, dateFormat.format(story.get_publishdate()));
        values.put(COLUMN_EXPIRATIONDATE, dateFormat.format(story.get_expirationdate()));
        values.put(COLUMN_CATEGORY, story.get_category());
        values.put(COLUMN_ISPRIVATE, story.get_isprivate());
        values.put(COLUMN_IMAGEURL, story.get_imageUrl());
        values.put(COLUMN_DESCRIPTION, story.get_description());
        values.put(COLUMN_ISFEATURED, story.get_isfeatured());
        values.put(COLUMN_ISCOMMUNICATIONMESSAGE, story.get_iscommunicationmessage());
        values.put(COLUMN_STORYTIMESTAMP, story.get_storytimestamp());
        values.put(COLUMN_ISBOOKMARKED, story.get_isbookmarked());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_STORIES, null, values);
        db.close();
    }

    public int updateStory(Story story) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, story.get_title());
        values.put(COLUMN_BODY, story.get_body());
        values.put(COLUMN_PUBLISHDATE, dateFormat.format(story.get_publishdate()));
        values.put(COLUMN_EXPIRATIONDATE, dateFormat.format(story.get_expirationdate()));
        values.put(COLUMN_CATEGORY, story.get_category());
        values.put(COLUMN_ISPRIVATE, story.get_isprivate());
        values.put(COLUMN_IMAGEURL, story.get_imageUrl());
        values.put(COLUMN_DESCRIPTION, story.get_description());
        values.put(COLUMN_ISFEATURED, story.get_isfeatured());
        values.put(COLUMN_ISCOMMUNICATIONMESSAGE, story.get_iscommunicationmessage());
        values.put(COLUMN_STORYTIMESTAMP, story.get_storytimestamp());
        values.put(COLUMN_ISBOOKMARKED, story.get_isbookmarked());
        SQLiteDatabase db = getWritableDatabase();
        int rows_affected = db.update(TABLE_STORIES, values, COLUMN_ID + "=?", new String[]{String.valueOf(story.get_id())});

        db.close();
        return rows_affected;
    }

    //Delete a product from the database
    public void deleteStory(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_STORIES + " WHERE " + COLUMN_ID + "=" + String.valueOf(id) + ";");
        db.close();
    }

    public Story getStory(int id) {
        Story story = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_STORIES, new String[]{
                        COLUMN_ID,
                        COLUMN_TITLE,
                        COLUMN_BODY,
                        COLUMN_PUBLISHDATE,
                        COLUMN_EXPIRATIONDATE,
                        COLUMN_CATEGORY,
                        COLUMN_ISPRIVATE,
                        COLUMN_IMAGEURL,
                        COLUMN_DESCRIPTION,
                        COLUMN_ISFEATURED,
                        COLUMN_ISCOMMUNICATIONMESSAGE,
                        COLUMN_STORYTIMESTAMP,
                        COLUMN_ISBOOKMARKED},
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String _title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                    String _body = cursor.getString(cursor.getColumnIndex(COLUMN_BODY));
                    Date _publishdate = dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_PUBLISHDATE)));
                    Date _expirationdate = dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_EXPIRATIONDATE)));
                    String _category = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY));
                    boolean _isprivate = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISPRIVATE)));
                    String _imageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEURL));
                    String _description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                    boolean _isfeatured = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISFEATURED)));
                    boolean _iscommunicationmessage = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISCOMMUNICATIONMESSAGE)));
                    String _storytimestamp = cursor.getString(cursor.getColumnIndex(COLUMN_STORYTIMESTAMP));
                    boolean _isbookmarked = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISBOOKMARKED)));

                    story = new Story(id,
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
                            _isbookmarked);
                } catch (Exception e) {
                    String error = e.getMessage();
                }
            }
        }
        db.close();
        try {
            if (cursor != null)
                cursor.close();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
        return story;

    }

    public List<Story> getStories() {
        List<Story> lstStories = new ArrayList<>();


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_STORIES, null);


        if (cursor != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (cursor.moveToFirst()) {
                do {

                    try {
                        int _id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                        String _title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                        String _body = cursor.getString(cursor.getColumnIndex(COLUMN_BODY));
                        Date _publishdate = dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_PUBLISHDATE)));
                        Date _expirationdate = dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_EXPIRATIONDATE)));
                        String _category = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY));
                        boolean _isprivate = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISPRIVATE)));
                        String _imageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEURL));
                        String _description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                        boolean _isfeatured = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISFEATURED)));
                        boolean _iscommunicationmessage = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISCOMMUNICATIONMESSAGE)));
                        String _storytimestamp = cursor.getString(cursor.getColumnIndex(COLUMN_STORYTIMESTAMP));
                        boolean _isbookmarked = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISBOOKMARKED)));

                        lstStories.add(new Story(_id,
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
                                _isbookmarked));
                    } catch (Exception e) {
                        String error = e.getMessage();
                    }
                }
                while (cursor.moveToNext());
            }
        }
        db.close();
        try {
            if (cursor != null)
                cursor.close();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }

        return lstStories;

    }
}