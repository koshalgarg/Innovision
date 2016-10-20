package in.ac.nitrkl.innovisionr;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by dibya on 19-10-2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    private static final String TAG = "CustomAdapter";
    String textcat[]={"im","title","date","venue","time","descrption","rules","judging_criteria","coordinator"};
        Context ctx;
        ArrayList<String> arrayList;
        private int[] mDataSetTypes;

        public static final int IMAGE = 11;
        public static final int TEXT = 15;
        String passeddata;

    public CustomAdapter(ShowEventActivity ctx, ArrayList<String>arrayList, int[] dataSetTypes) {
        this.arrayList=arrayList;
        this.ctx=ctx;

        mDataSetTypes = dataSetTypes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;
        if (viewType == IMAGE) {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_image, viewGroup, false);

            return new ImageViewHolder(v);
        } else  {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.card_text, viewGroup, false);
            return new TextViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (viewHolder.getItemViewType() == IMAGE) {
            final ImageViewHolder holder = (ImageViewHolder) viewHolder;
            Glide.with(ctx)
                    .load(arrayList.get(position))
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            holder.pb.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            holder.pb.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.img);


        }
        else if (viewHolder.getItemViewType() == TEXT) {

            TextViewHolder holder = (TextViewHolder) viewHolder;
            holder.score.setText(arrayList.get(position));
            holder.heading.setText(textcat[position]);
            holder.score.setTypeface(Typeface.createFromAsset(ctx.getAssets(),"kievit.ttf"));
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return mDataSetTypes[position];
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public class ImageViewHolder extends ViewHolder {
        TextView tvttt;
        ImageView img;
        ProgressBar pb;
        public ImageViewHolder(View v) {
            super(v);

            img= (ImageView) v.findViewById(R.id.imageaa);
            pb= (ProgressBar) v.findViewById(R.id.progress);

        }
    }

    public class TextViewHolder extends ViewHolder {
        TextView score,heading;

        public TextViewHolder(View v) {
            super(v);
            score = (TextView) v.findViewById(R.id.textcard);
            heading= (TextView) v.findViewById(R.id.textheading);
        }
    }

}