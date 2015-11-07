package com.alrehablife.alrehab.businessentities;

import java.util.Date;

public class Story {
    private int _id;
    private String _title;
    private String _body;
    private Date _publishdate;
    private Date _expirationdate;
    private String _category;
    private boolean _isprivate;
    private String _imageUrl;
    private String _description;
    private boolean _isfeatured;
    private boolean _iscommunicationmessage;
    private String _storytimestamp;
    private boolean _isbookmarked;


    public Story() {
    }


    public Story(int id,
                       String title,
                       String body,
                       Date publishdate,
                       Date expirationdate,
                       String category,
                       boolean isprivate,
                       String imageUrl,
                       String description,
                       boolean isfeatured,
                       boolean iscommunicationmessage,
                       String storytimestamp,
                       boolean isbookmarked) {
        this._id = id;
        this._title = title;
        this._body = body;
        this._publishdate = publishdate;
        this._expirationdate = expirationdate;
        this._category = category;
        this._isprivate = isprivate;
        this._imageUrl = imageUrl;
        this._description = description;
        this._isfeatured = isfeatured;
        this._iscommunicationmessage = iscommunicationmessage;
        this._storytimestamp = storytimestamp;
        this._isbookmarked = isbookmarked;

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

    public Date get_publishdate() {
        return _publishdate;
    }

    public void set_publishdate(Date _publishdate) {
        this._publishdate = _publishdate;
    }

    public Date get_expirationdate() {
        return _expirationdate;
    }

    public void set_expirationdate(Date _expirationdate) {
        this._expirationdate = _expirationdate;
    }


    public String get_category() {
        return _category;
    }

    public void set_category(String _category) {
        this._category = _category;
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

    public boolean get_isfeatured() {
        return _isfeatured;
    }

    public void set_isfeatured(boolean _isfeatured) {
        this._isfeatured = _isfeatured;
    }

    public boolean get_iscommunicationmessage() {
        return _iscommunicationmessage;
    }

    public void set_iscommunicationmessage(boolean _iscommunicationmessage) {
        this._iscommunicationmessage = _iscommunicationmessage;
    }

    public String get_storytimestamp() {
        return _storytimestamp;
    }

    public void set_storytimestamp(String _storytimestamp) {
        this._storytimestamp = _storytimestamp;
    }

    public boolean get_isbookmarked() {
        return _isbookmarked;
    }

    public void set_isbookmarked(boolean _isbookmarked) {
        this._isbookmarked = _isbookmarked;
    }
}