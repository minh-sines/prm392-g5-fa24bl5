package com.fu.fe.minhtq.prm392g5fa24bl5.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "tag")
public class Tag {
    @PrimaryKey
    public int tag_id;
    public String name;

    public Tag(String name) {
        this.name = name;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
