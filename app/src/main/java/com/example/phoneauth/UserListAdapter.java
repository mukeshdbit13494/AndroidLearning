package com.example.phoneauth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Comment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    public List<Users> listUsers;
    public UserListAdapter(List<Users> listUsers)
    {
        this.listUsers=listUsers;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listView=layoutInflater.inflate(R.layout.userdata_list, parent,false);
        ViewHolder holder=new ViewHolder(listView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        final Users users=listUsers.get(position);
        holder.tv_name.setText(listUsers.get(position).getName());
        holder.tv_no.setText(listUsers.get(position).getNumber());
        holder.tv_email.setText(listUsers.get(position).getEmail());
        holder.tv_goal.setText(listUsers.get(position).getGoal());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(),UserComments.class);
                intent.putExtra(UserDataRead.USER_ID,users.getId());
                intent.putExtra(UserDataRead.USER_NAME,users.getName());
                v.getContext().startActivity(intent);
                //Toast.makeText(v.getContext(),"You click on "+users.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name,tv_no,tv_email,tv_goal;
        LinearLayout linearLayout;
        public ViewHolder(View itemView) {
          super(itemView);
          this.tv_name=(TextView)itemView.findViewById(R.id.tv_username);
          this.tv_no=(TextView)itemView.findViewById(R.id.tv_no);
          this.tv_email=(TextView)itemView.findViewById(R.id.tv_emailid);
          this.tv_goal=(TextView)itemView.findViewById(R.id.tv_goal2);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.linearLayout);
      }
  }
}
