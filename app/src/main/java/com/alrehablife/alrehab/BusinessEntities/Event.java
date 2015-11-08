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

    private String _eventtimestamp;
    private String _placename;
    private boolean _isbookmarked;


    private boolean _isdeleted;

    public Event() {
    }


    public Event(int _id,
                 String _title,
                 String _body,
                 Date _eventdate,
                 Date _expirationdate,
                 boolean _isprivate,
                 String _imageUrl,
                 String _description,
                 String _category,
                 String _address,
                 String _telephone,
                 String _duration,
                 float _ticketprice,
                 String _eventtimestamp,
                 String _placename,
                 boolean _isbookmarked,
                 boolean _isdeleted) {
        this._address = _address;
        this._body = _body;
        this._category = _category;
        this._description = _description;
        this._duration = _duration;
        this._eventdate = _eventdate;
        this._expirationdate = _expirationdate;
        this._id = _id;
        this._imageUrl = _imageUrl;
        this._isbookmarked = _isbookmarked;
        this._isdeleted = _isdeleted;
        this._isprivate = _isprivate;
        this._placename = _placename;
        this._telephone = _telephone;
        this._ticketprice = _ticketprice;
        this._eventtimestamp = _eventtimestamp;
        this._title = _title;
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

    public String get_eventtimestamp() {
        return _eventtimestamp;
    }

    public void set_eventtimestamp(String _eventtimestamp) {
        this._eventtimestamp = _eventtimestamp;
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
