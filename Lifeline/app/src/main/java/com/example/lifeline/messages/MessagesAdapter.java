package com.example.lifeline.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeline.R;

import java.text.BreakIterator;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder> {

    private final List<MessagesList> messagesLists;
    private final Context context;

    public MessagesAdapter(List<MessagesList> messagesLists, Context context) {
        this.messagesLists = messagesLists;
        this.context = context;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView nameShow, lastMessageShow, mobileShow, unseenMessageShow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameShow = itemView.findViewById(R.id.textViewMessageName);
            lastMessageShow = itemView.findViewById(R.id.textViewMessageLast);
            unseenMessageShow = itemView.findViewById(R.id.showUnseen);
        }
    }

    @NonNull
    @Override
    public MessagesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.MyViewHolder holder, int position) {
        MessagesList list2 = messagesLists.get(position);

        if(list2 != null) {

            holder.nameShow.setText(list2.getName());
            holder.lastMessageShow.setText(list2.getLastMessage());
            if (list2.getUnseenMessages() == 0) {
                holder.unseenMessageShow.setVisibility(View.GONE);
            } else {
                holder.unseenMessageShow.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return messagesLists.size();
    }
}
