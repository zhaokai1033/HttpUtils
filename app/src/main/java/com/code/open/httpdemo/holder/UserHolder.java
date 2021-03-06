package com.code.open.httpdemo.holder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.code.open.httpdemo.R;
import com.code.open.httpdemo.databinding.ViewUserItemBinding;
import com.code.open.httpdemo.model.GitUser;

/**
 * ================================================
 * Created by zhaokai on 2017/3/27.
 * Email zhaokai1033@126.com
 * Describe :
 * ================================================
 */

public class UserHolder extends RecyclerView.ViewHolder {

    private final ViewUserItemBinding mBinding;

    private UserHolder(ViewUserItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public static UserHolder create(LayoutInflater inflater, ViewGroup parent) {
        ViewUserItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_user_item, parent, false);
        return new UserHolder(binding);
    }

    public void bindTo(GitUser user) {
        mBinding.setUser(user);
        mBinding.executePendingBindings();
    }
}
