package com.llw.roomdemo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.llw.roomdemo.db.bean.User;
import com.llw.roomdemo.db.dao.UserDao;

/**
 * @author llw
 * @description MyDatabase
 * @date 2021/8/2 20:39
 */
@Database(entities = {User.class}, version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
