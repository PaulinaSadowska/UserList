package com.nekodev.paulina.sadowska.userlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nekodev.paulina.sadowska.userlist.listeners.ItemClickedListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Paulina Sadowska on 21.07.2016.
 */
public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.user_avatar)
    ImageView userAvatar;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_item_layout)
    RelativeLayout userLayout;

    private ItemClickedListener itemClickedListener;

    public UserViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(View v) {
        if(itemClickedListener!=null){
            itemClickedListener.itemClicked(getAdapterPosition());
        }
    }

    public void setItemClickedListener(ItemClickedListener itemClickedListener) {
        this.itemClickedListener = itemClickedListener;
    }
}
