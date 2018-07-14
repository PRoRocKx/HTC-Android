package com.example.evgeniy.htcandroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.evgeniy.htcandroid.dummy.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyUsersRecyclerViewAdapter extends RecyclerView.Adapter<MyUsersRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;

    public MyUsersRecyclerViewAdapter(List<User> items) {
        mValues = items;
    }

    public void addUser(User user){
        mValues.add(user);
        notifyItemInserted(mValues.size()-1);
    }

    public List<User> getValues(){
        return mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        @BindView(R.id.id)
        TextView mIdView;
        @BindView(R.id.content)
        TextView mContentView;

        ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this,view);
        }

        public void bind(User user){
            mIdView.setText(user.getName());
            mContentView.setText(user.getInfo());
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
