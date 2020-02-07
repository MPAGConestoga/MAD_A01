package com.example.a_01_mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class memberListAdapter extends RecyclerView.Adapter<memberListAdapter.memberListViewHolder> {
    private ArrayList<memberListItem> memberList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public static class memberListViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mName;
        public ImageView mDeleteImage;

        public memberListViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.memberPic);
            mName = itemView.findViewById(R.id.memberName);
            mDeleteImage = itemView.findViewById(R.id.deleteImage);



            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public memberListAdapter(ArrayList<memberListItem> list)
    {
        memberList =  list;
    }

    @NonNull
    @Override
    public memberListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_list, parent, false);
        memberListViewHolder evh = new memberListViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull memberListViewHolder holder, int position) {
        memberListItem currentMember = memberList.get(position);

        holder.mImageView.setImageResource(currentMember.getmImage());
        holder.mName.setText(currentMember.getmName());
        holder.mDeleteImage.setImageResource(currentMember.getmDeleteImage());
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }
}

