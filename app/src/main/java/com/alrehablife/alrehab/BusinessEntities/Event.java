package com.alrehablife.alrehab.BusinessEntities;

import java.util.Date;


public class Event {

    private int _id;
    private String _title;
    private String _body;
    private Date _eventdate;
    private Date _expirationdate;

    private boolean _isprivate;
    private String _imageUrl;
    private String _description;
    private String _category;
    private String _address;
    private String _telephone;
    private String _duration;
    private float _ticketprice;

    private String _timestamp;
    private String _placename;
    private boolean _isbookmarked;


    private boolean _isdeleted;

    public Event() {
    }


    public Event(int id,
                 String title,
                 String body,
                 Date eventdate,
                 Date expirationdate,
                 boolean isprivate,
                 String imageUrl,
                 String description,
                 String category,
                 String address,
                 String telephone,
                 String duration,
                 float ticketprice,
                 String timestamp,
                 String placename,
                 boolean isbookmarked,
                 boolean isdeleted) {
        this._address = address;
        this._body = body;
        this._category = category;
        this._description = description;
        this._duration = duration;
        this._eventdate = eventdate;
        this._expirationdate = expirationdate;
        this._id = id;
        this._imageUrl = imageUrl;
        this._isbookmarked = isbookmarked;
        this._isdeleted = isdeleted;
        this._isprivate = isprivate;
        this._placename = placename;
        this._telephone = telephone;
        this._ticketprice = ticketprice;
        this._timestamp = timestamp;
        this._title = title;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_body() {
        return _body;
    }

    public void set_body(String _body) {
        this._body = _body;
    }

    public Date get_eventdate() {
        return _eventdate;
    }

    public void set_eventdate(Date _eventdate) {
        this._eventdate = _eventdate;
    }

    public Date get_expirationdate() {
        return _expirationdate;
    }

    public void set_expirationdate(Date _expirationdate) {
        this._expirationdate = _expirationdate;
    }

    public boolean get_isprivate() {
        return _isprivate;
    }

    public void set_isprivate(boolean _isprivate) {
        this._isprivate = _isprivate;
    }

    public String get_imageUrl() {
        return _imageUrl;
    }

    public void set_imageUrl(String _imageUrl) {
        this._imageUrl = _imageUrl;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_category() {
        return _category;
    }

    public void set_category(String _category) {
        this._category = _category;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_telephone() {
        return _telephone;
    }

    public void set_telephone(String _telephone) {
        this._telephone = _telephone;
    }

    public String get_duration() {
        return _duration;
    }

    public void set_duration(String _duration) {
        this._duration = _duration;
    }

    public float get_ticketprice() {
        return _ticketprice;
    }

    public void set_ticketprice(float _ticketprice) {
        this._ticketprice = _ticketprice;
    }

    public String get_timestamp() {
        return _timestamp;
    }

    public void set_timestamp(String _timestamp) {
        this._timestamp = _timestamp;
    }

    public String get_placename() {
        return _placename;
    }

    public void set_placename(String _placename) {
        this._placename = _placename;
    }

    public boolean get_isbookmarked() {
        return _isbookmarked;
    }

    public void set_isbookmarked(boolean _isbookmarked) {
        this._isbookmarked = _isbookmarked;
    }

    public boolean get_isdeleted() {
        return _isdeleted;
    }

    public void set_isdeleted(boolean _isdeleted) {
        this._isdeleted = _isdeleted;
    }
}
