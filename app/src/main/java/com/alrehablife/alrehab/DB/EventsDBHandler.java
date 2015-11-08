package com.alrehablife.alrehab.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alrehablife.alrehab.BusinessEntities.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventsDBHandler extends SQLiteOpenHelper {

    public static final String TABLE_EVENTS = "events";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_BODY = "body";
    public static final String COLUMN_EVENTDATE = "eventdate";
    public static final String COLUMN_EXPIRATIONDATE = "expirationdate";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_ISPRIVATE = "isprivate";
    public static final String COLUMN_IMAGEURL = "imageurl";

    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_TELEPHONE = "telephone";
    public static final String COLUMN_EVENTTIMESTAMP = "eventtimestamp";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_TICKETPRICE = "ticketprice";
    public static final String COLUMN_PLACENAME = "placename";
    public static final String COLUMN_ISBOOKMARKED = "isbookmarked";
    public static final String COLUMN_ISDELETED = "isdeleted";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "eventsdb.db";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //We need to pass database information along to superclass
    public EventsDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public EventsDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_EVENTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                COLUMN_TITLE + " nvarchar(500) NOT NULL," +

                COLUMN_BODY + " nvarchar(8000) NOT NULL," +

                COLUMN_EVENTDATE + " datetime NOT NULL," +

                COLUMN_EXPIRATIONDATE + " datetime NULL," +

                COLUMN_ISPRIVATE + " boolean NOT NULL," +
                COLUMN_IMAGEURL + " nvarchar(500) NOT NULL," +
                COLUMN_DESCRIPTION + " nvarchar(500) NOT NULL," +

                COLUMN_CATEGORY + " nvarchar(500) NULL," +
                COLUMN_ADDRESS + " nvarchar(500) NULL," +
                COLUMN_TELEPHONE + " nvarchar(500) NULL," +
                COLUMN_DURATION + " nvarchar(500) NULL," +
                COLUMN_TICKETPRICE + " float NOT NULL," +
                COLUMN_PLACENAME + " nvarchar(500) NULL," +
                COLUMN_EVENTTIMESTAMP + " NUMERIC NOT NULL," +
                COLUMN_ISBOOKMARKED + " BOOLEAN NOT NULL," +
                COLUMN_ISDELETED + " BOOLEAN NOT NULL" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }

    //Add a new row to the database
    public void addEvent(Event event) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, event.get_id());
            values.put(COLUMN_TITLE, event.get_title());
            values.put(COLUMN_BODY, event.get_body());
            values.put(COLUMN_EVENTDATE, dateFormat.format(event.get_eventdate()));
            values.put(COLUMN_EXPIRATIONDATE, dateFormat.format(event.get_expirationdate()));
            values.put(COLUMN_CATEGORY, event.get_category());
            values.put(COLUMN_ISPRIVATE, event.get_isprivate());
            values.put(COLUMN_IMAGEURL, event.get_imageUrl());
            values.put(COLUMN_DESCRIPTION, event.get_description());
            values.put(COLUMN_ADDRESS, event.get_address());
            values.put(COLUMN_TELEPHONE, event.get_telephone());
            values.put(COLUMN_DURATION, event.get_duration());
            values.put(COLUMN_TICKETPRICE, event.get_ticketprice());
            values.put(COLUMN_PLACENAME, event.get_placename());
            values.put(COLUMN_EVENTTIMESTAMP, event.get_timestamp());
            values.put(COLUMN_ISBOOKMARKED, event.get_isbookmarked());
            values.put(COLUMN_ISDELETED, event.get_isdeleted());

            db.insert(TABLE_EVENTS, null, values);
            db.close();
        } catch (Exception ex) {
            String error = ex.getMessage();
        }
    }

    public int updateEvent(Event event) {

        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, event.get_id());
        values.put(COLUMN_TITLE, event.get_title());
        values.put(COLUMN_BODY, event.get_body());
        values.put(COLUMN_EVENTDATE, dateFormat.format(event.get_eventdate()));
        values.put(COLUMN_EXPIRATIONDATE, dateFormat.format(event.get_expirationdate()));
        values.put(COLUMN_CATEGORY, event.get_category());
        values.put(COLUMN_ISPRIVATE, event.get_isprivate());
        values.put(COLUMN_IMAGEURL, event.get_imageUrl());
        values.put(COLUMN_DESCRIPTION, event.get_description());
        values.put(COLUMN_ADDRESS, event.get_address());
        values.put(COLUMN_TELEPHONE, event.get_telephone());
        values.put(COLUMN_DURATION, event.get_duration());
        values.put(COLUMN_TICKETPRICE, event.get_ticketprice());
        values.put(COLUMN_PLACENAME, event.get_placename());
        values.put(COLUMN_EVENTTIMESTAMP, event.get_timestamp());
        values.put(COLUMN_ISBOOKMARKED, event.get_isbookmarked());
        values.put(COLUMN_ISDELETED, event.get_isdeleted());
        SQLiteDatabase db = getWritableDatabase();
        int rows_affected = db.update(TABLE_EVENTS, values, COLUMN_ID + "=?", new String[]{String.valueOf(event.get_id())});

        db.close();
        return rows_affected;
    }


    public void deleteEvent(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_ID + "=" + String.valueOf(id) + ";");
        db.close();
    }

    public void markAllEventsDeleted() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_EVENTS + " set " + COLUMN_ISDELETED + "=1 WHERE 1;");
        db.close();
    }

    public void deleteEventsMrkedDeleted() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_EVENTS + " WHERE " + COLUMN_ISDELETED + "=1;");
        db.close();
    }

    public Event getEvent(int id) {
        Event event = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_EVENTS, new String[]{
                        COLUMN_ID,
                        COLUMN_TITLE,
                        COLUMN_BODY,
                        COLUMN_EVENTDATE,
                        COLUMN_EXPIRATIONDATE,
                        COLUMN_ISPRIVATE,
                        COLUMN_IMAGEURL,
                        COLUMN_DESCRIPTION,
                        COLUMN_CATEGORY,
                        COLUMN_ADDRESS,
                        COLUMN_TELEPHONE,
                        COLUMN_DURATION,
                        COLUMN_TICKETPRICE,
                        COLUMN_PLACENAME,
                        COLUMN_EVENTTIMESTAMP,
                        COLUMN_ISBOOKMARKED,
                        COLUMN_ISDELETED},
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String _title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                    String _body = cursor.getString(cursor.getColumnIndex(COLUMN_BODY));
                    Date _eventdate = dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_EVENTDATE)));
                    Date _expirationdate = dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_EXPIRATIONDATE)));
                    String _category = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY));
                    boolean _isprivate = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISPRIVATE)));
                    String _imageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEURL));
                    String _description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                    String _address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                    String _telephone = cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE));
                    String _duration = cursor.getString(cursor.getColumnIndex(COLUMN_DURATION));
                    String _placename = cursor.getString(cursor.getColumnIndex(COLUMN_PLACENAME));
                    float _ticketprice = Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_TICKETPRICE)));
                    String _eventtimestamp = cursor.getString(cursor.getColumnIndex(COLUMN_EVENTTIMESTAMP));
                    boolean _isbookmarked = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISBOOKMARKED)));
                    boolean _isdeleted = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISDELETED)));

                    event = new Event(id,
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
                            _isbookmarked,
                            _isdeleted);
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
        return event;

    }

    public List<Event> getAllEvents() {
        List<Event> lstEvents = new ArrayList<>();


        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from " + TABLE_EVENTS;

        Cursor cursor = db.rawQuery(query, null);


        if (cursor != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (cursor.moveToFirst()) {
                do {

                    try {
                        int _id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                        String _title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                        String _body = cursor.getString(cursor.getColumnIndex(COLUMN_BODY));
                        Date _eventdate = dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_EVENTDATE)));
                        Date _expirationdate = dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_EXPIRATIONDATE)));
                        String _category = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY));
                        boolean _isprivate = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISPRIVATE)));
                        String _imageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEURL));
                        String _description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                        String _address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                        String _telephone = cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE));
                        String _duration = cursor.getString(cursor.getColumnIndex(COLUMN_DURATION));
                        String _placename = cursor.getString(cursor.getColumnIndex(COLUMN_PLACENAME));
                        float _ticketprice = Float.parseFloat(cursor.getString(cursor.getColumnIndex(COLUMN_TICKETPRICE)));
                        String _eventtimestamp = cursor.getString(cursor.getColumnIndex(COLUMN_EVENTTIMESTAMP));
                        boolean _isbookmarked = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISBOOKMARKED)));
                        boolean _isdeleted = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ISDELETED)));

                        lstEvents.add(new Event(_id,
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
                                _isbookmarked,
                                _isdeleted));
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

        return lstEvents;

    }
}