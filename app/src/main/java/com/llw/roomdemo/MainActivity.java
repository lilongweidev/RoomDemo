package com.llw.roomdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.llw.roomdemo.adapter.UserAdapter;
import com.llw.roomdemo.db.MyDatabase;
import com.llw.roomdemo.db.bean.User;
import com.llw.roomdemo.db.dao.UserDao;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    private MyDatabase db;
    private List<User> mList = new ArrayList<>();
    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        //列表
        RecyclerView rv = findViewById(R.id.rv);
        mAdapter = new UserAdapter(R.layout.item_rv, mList);
        rv.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);
        //初始化数据库
        initDB();
    }

    /**
     * 初始化数据库
     */
    private void initDB() {
        Room.inMemoryDatabaseBuilder(getApplicationContext(), MyDatabase.class);
        //本地持久化数据库
        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "DemoDB")
                //是否允许在主线程上操作数据库，默认false。
                .allowMainThreadQueries()
                //数据库创建和打开的事件会回调到这里，可以再次操作数据库
                .addCallback(new CallBack())
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                //添加用户
                addUser();
                break;
            case R.id.btn_delete:
                //删除用户
                deleteUser();
                break;
            case R.id.btn_update:
                //修改用户
                updateUser();
                break;
            case R.id.btn_query:
                //查询所有用户
                queryAll();
                break;
            default:
                break;
        }
    }

    /**
     * 增加用户
     */
    private void addUser() {
        runOnUiThread(() -> {
            db.userDao().insertUser(new User("张三", 20, "张大炮", "北京八宝山4号墓地"),
                    new User("李四", 60, "尼古拉斯.凯奇", "美国佛罗里达州"),
                    new User("王五", 70, "爱新觉罗.爱国", "北京故宫乾清宫西北方向角落"),
                    new User("赵六", 30, "叶赫那拉.啦啦啦", "北京前门外前门大街皮条胡同"));
            Toast.makeText(MainActivity.this, "增加成功", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * 删除用户
     */
    private void deleteUser() {
        runOnUiThread(() -> {
            User user = db.userDao().findByName("张三");
            if (user == null) return;
            db.userDao().delete(user);
            Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * 修改用户
     */
    private void updateUser() {
        runOnUiThread(() -> {
            User user = db.userDao().findByName("李四");
            if (user == null) return;
            user.setUserName("赵四");
            user.setUserAge(10);
            user.setNickName("尼古拉斯.赵四");
            user.setAddress("中国东北");
            db.userDao().update(user);

            Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * 查询所有用户
     */
    private void queryAll() {
        runOnUiThread(() -> {
            mList.clear();
            mList.addAll(db.userDao().queryAll());
            mAdapter.notifyDataSetChanged();
        });
    }

    static class CallBack extends RoomDatabase.Callback {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d(TAG, "db create");
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.d(TAG, "db open");
        }
    }


}