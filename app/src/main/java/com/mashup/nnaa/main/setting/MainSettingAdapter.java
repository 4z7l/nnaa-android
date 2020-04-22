package com.mashup.nnaa.main.setting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mashup.nnaa.NnaaApplication;
import com.mashup.nnaa.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainSettingAdapter extends RecyclerView.Adapter<MainSettingViewHolder> {
    public enum SettingList {
        SIGN_OUT(R.string.setting_sign_out),//,
        //DELETE_ACCOUNT(R.string.setting_del_account),
        //BLOCKED_USERS(R.string.setting_blocked_users);
        MANAGE_FAVORITES(R.string.setting_manage_favorites),
        CHANGE_PW(R.string.setting_change_pw);
        // Todo : 서버 api 없는 기능들 주석처리함. 서버에서 추가되면 기능 지원

        private int textResId;

        public String getText() {
            return NnaaApplication.getAppContext().getString(textResId);
        }

        SettingList(int textResId) {
            this.textResId = textResId;
        }
    }

    private ArrayList<SettingList> items = new ArrayList<>(Arrays.asList(SettingList.values()));

    public MainSettingAdapter() {
    }

    @NonNull
    @Override
    public MainSettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_setting, parent, false);
        return new MainSettingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainSettingViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
