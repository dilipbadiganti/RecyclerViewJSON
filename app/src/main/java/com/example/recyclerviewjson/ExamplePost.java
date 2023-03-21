package com.example.recyclerviewjson;


public class ExamplePost {
    private final String myImageUrl;
    private final String myCreator;
    private final int myLikes;

    public ExamplePost(String imageUrl, String creator, int likes) {
        myImageUrl = imageUrl;
        myCreator = creator;
        myLikes = likes;
    }

    public String getImageUrl() {
        return myImageUrl;
    }

    public String getCreator() {
        return myCreator;
    }

    public int getLikeCount() {
        return myLikes;
    }
}
