package com.fu.fe.minhtq.prm392g5fa24bl5.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "account")
public class Account {
    @PrimaryKey(autoGenerate = true)
    public int account_id;
    public String name;
    public String email;
    public String password;
    public long created_at;

    public Account(String name, String email, String password, long created_at) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
    }
    @Ignore
    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    @Ignore
    public Account() {
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    @NonNull
    @Override
    public String toString() {
        return email + password + name + created_at;
    }
}

