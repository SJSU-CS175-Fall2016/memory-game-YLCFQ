package com.cs175.chenyulong.memorygame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by chenyulong on 9/18/16.
 */
public class ImageAdapter extends BaseAdapter{
    private Context mContext;
    private int[] pictureData;
    private int[] mImageIds;
    private int[] finishedArr;
    public ImageAdapter(Context c, int[] arr, int [] finishedArr) {
        mContext = c;
        mImageIds = arr;
        this.finishedArr = finishedArr;

    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;

        if (convertView == null) {
            view = new View(mContext);

            view = inflater.inflate(R.layout.cell_image, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.realImage);
            imageView.setImageResource(mImageIds[position]);

            if (finishedArr[position] == 1) {
                view.findViewById(R.id.realImage).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.hideImage).setVisibility(View.INVISIBLE);
            }
        }
        else {
            view = (View) convertView;
        }
        return view;
    }


}
