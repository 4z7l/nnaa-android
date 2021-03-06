package com.mashup.nnaa.network.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FriendDto implements Serializable {
    String type;
    String name;
    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("questions")
    @Expose
    private JsonObject questions;

    public FriendDto() {

    }

    public JsonObject getQuestions() {
        return questions;
    }

    public void setQuestions(JsonObject questions) {
        this.questions = questions;
    }

    @SerializedName("receiverId")
    @Expose
    private String receiverId;
    @SerializedName("id")
    @Expose
    private String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FriendDto(String type, String name, String email) {
        this.type = type;
        this.name = name;
        this.email = email;
    }
    public FriendDto(String category, String createdAt, JsonObject questions, String receiverId) {
        this.category = category;
        this.createdAt = createdAt;
        this.questions = questions;
        this.receiverId = receiverId;
    }
}
