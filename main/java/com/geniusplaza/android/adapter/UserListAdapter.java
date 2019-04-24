package com.geniusplaza.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geniusplaza.android.R;
import com.geniusplaza.android.model.UserData;
import com.geniusplaza.android.view.impl.UserListActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<UserData.UserList> userDataList = new ArrayList<>();
    private UserListActivity context;

    public UserListAdapter(UserListActivity userListActivity) {
        context = userListActivity;
    }

    public void setData(List<UserData.UserList> userDataList) {
        this.userDataList = userDataList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserData.UserList selectedUserData = userDataList.get(position);
        holder.userFirstNameTV.setText(selectedUserData.getFirstName());
        holder.userLastNameTV.setText(selectedUserData.getLastName());
        Picasso.get()
                .load(selectedUserData.getAvatar())
                .into(holder.userAvatarIM);
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userAvatarIM;
        TextView userFirstNameTV;
        TextView userLastNameTV;

        public ViewHolder(View itemView) {
            super(itemView);
            userAvatarIM = (ImageView) itemView.findViewById(R.id.user_avatar_iv);
            userFirstNameTV = (TextView) itemView.findViewById(R.id.user_first_name_tv);
            userLastNameTV = (TextView) itemView.findViewById(R.id.user_last_name_tv);
        }
    }
}
