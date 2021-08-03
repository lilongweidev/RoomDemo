package com.llw.roomdemo.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.llw.roomdemo.db.bean.User;

import java.util.List;

/**
 * @author llw
 * @description UserDao
 * @date 2021/8/2 20:27
 */

@Dao
public interface UserDao {

    /**
     * 增加
     *
     * @param users 用户
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User... users);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @Query("SELECT * FROM user")
    List<User> queryAll();

    /**
     * 按用户名查询
     * @param userName 用户名
     * @return 用户
     */
    @Query("SELECT * FROM user WHERE user_name LIKE :userName LIMIT 1")
    User findByName(String userName);

    /**
     * 修改
     * @param users 根据用户进行修改
     */
    @Update
    void update(User... users);

    /**
     * 删除
     * @param users 根据用户进行删除
     */
    @Delete
    void delete(User... users);
}
