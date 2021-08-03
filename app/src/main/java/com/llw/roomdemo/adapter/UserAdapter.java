package com.llw.roomdemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.llw.roomdemo.R;
import com.llw.roomdemo.db.bean.User;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author llw
 * @description UserAdapter
 * @date 2021/8/3 9:48
 */
public class UserAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

    public UserAdapter(int layoutResId, @Nullable List<User> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, User user) {
        holder.setText(R.id.tv_id, String.format("%d",user.id))
                .setText(R.id.tv_name, user.userName)
                .setText(R.id.tv_age, String.format("%d",user.userAge))
                .setText(R.id.tv_nickname, user.nickName)
                .setText(R.id.tv_address, user.address);
    }
}
