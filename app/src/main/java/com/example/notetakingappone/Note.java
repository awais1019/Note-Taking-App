package com.example.notetakingappone;

import java.io.Serializable;

public class Note implements Serializable {
    private  String Title;
    private String Content;


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }


    public Note(String title, String content) {
        Title = title;
        Content = content;

    }
}

