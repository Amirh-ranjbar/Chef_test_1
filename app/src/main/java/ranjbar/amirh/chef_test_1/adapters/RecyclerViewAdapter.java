package ranjbar.amirh.chef_test_1.adapters;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import ranjbar.amirh.chef_test_1.R;

import static android.content.ContentValues.TAG;

/**
 * Created by amirh on 10/09/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    onViewHolderListener viewHolderListener;
    private ArrayList<Integer> drawables = new ArrayList<>();

    public RecyclerViewAdapter(Fragment context, ArrayList<Integer> drawablesList) {
        drawables = drawablesList;
        Log.d(TAG, " RecyclerViewAdapter constructor :::::");
        viewHolderListener = (onViewHolderListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView view = (ImageView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        Log.d(TAG, " onCreateViewHolder :::::");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.setImageView(drawables.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, " OnClickListener ::::: " + position);

                viewHolderListener.notifyImageViewIsTouched(position);
            }
        });
        Log.d(TAG, " onBindViewHolder :::::");

    }

    @Override
    public int getItemCount() {
        return drawables.size();
    }

    public interface onViewHolderListener {
        void notifyImageViewIsTouched(@DrawableRes int resId);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(ImageView v) {
            super(v);
            imageView = v;
            Log.d(TAG, " ViewHolder :::::");

        }

        public void setImageView(@DrawableRes int drawable) {
            imageView.setImageResource(drawable);
        }
    }

}
