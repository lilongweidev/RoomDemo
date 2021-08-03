package com.llw.roomdemo.db.bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @author llw
 * @description User
 * @date 2021/8/2 20:07
 */
@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "user_name", defaultValue = "")
    public String userName;

    @ColumnInfo(name = "user_age")
    public int userAge;

    @ColumnInfo(name = "nick_name")
    public String nickName;

    @ColumnInfo(name = "address")
    public String address;

    public User(String userName, int userAge, String nickName, String address) {
        this.userName = userName;
        this.userAge = userAge;
        this.nickName = nickName;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
