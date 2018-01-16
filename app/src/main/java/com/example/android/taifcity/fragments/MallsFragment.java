package com.example.android.taifcity.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.taifcity.models.Mall;
import com.example.android.taifcity.R;

import java.util.ArrayList;

public class MallsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_recycler, container, false);

        ArrayList<Mall> malls = new ArrayList<>();
        malls.add(new Mall(R.string.malls_mall1name, R.drawable.photo_malls_mall1jouri,
                R.string.malls_mall1website, R.string.malls_mall1location));
        malls.add(new Mall(R.string.malls_mall2name, R.drawable.photo_malls_mall2heart,
                R.string.malls_mall2website, R.string.malls_mall2location));
        malls.add(new Mall(R.string.malls_mall3name, R.drawable.photo_malls_mall3international,
                R.string.malls_mall3website, R.string.malls_mall3location));
        malls.add(new Mall(R.string.malls_mall4name, R.drawable.photo_malls_mall4obaikan,
                R.string.malls_mall4website, R.string.malls_mall4location));

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview_all_rootview);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new MallAdapter(malls);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private class MallAdapter extends RecyclerView.Adapter<MallAdapter.MallViewHolder> {

        private ArrayList<Mall> mallArrayList;

        public MallAdapter(ArrayList<Mall> mallArrayList) {
            this.mallArrayList = mallArrayList;
        }

        // Create new views (invoked by the layout manager).
        @Override
        public MallAdapter.MallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_mall, parent, false);

            return new MallViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MallAdapter.MallViewHolder holder, final int position) {
            //Setup the photograph of the mall.
            holder.mallPhotoImageView.setImageResource(mallArrayList.get(position).getPhotograph());
            //Set the content description of the ImageView.
            holder.mallPhotoImageView.setContentDescription(getString(mallArrayList.get(position).getName()));

            //Set the name of the mall.
            holder.mallNameTextView.setText(mallArrayList.get(position).getName());

            //Set OnClickListener to the two ImageButtons.
            //1. mallWebsiteImageButton.
            holder.mallWebsiteImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Start a web browser activity to view the restaurant's website.
                    Uri webPage = Uri.parse(getResources().getString(mallArrayList.get(position)
                            .getWebsite()));
                    Intent intent = new Intent(Intent.ACTION_VIEW, webPage);

                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });

            //2. mallLocationImageButton.
            holder.mallLocationImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Start a map app to display the location of the restaurant.
                    String location = getResources().getString(mallArrayList.get(position).getLocation());
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(location));

                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mallArrayList.size();
        }

        public class MallViewHolder extends RecyclerView.ViewHolder {
            //Create references to all views that we need to access inside each mall item
            // card {@link item_mall}:

            //First, the views display the details of the current mall:
            //1. The ImageView display a photograph of the mall.
            ImageView mallPhotoImageView;
            //2. The TextView display the name of the mall.
            TextView mallNameTextView;

            //Second, the two ImageViews, we will attach an OnClickListener to each of them,
            //but first we need to change their color;
            //1. The ImageButton for the website of the mall.
            //this ImageButton will start a web browser activity to view the mall's website.
            ImageButton mallWebsiteImageButton;
            //2. The ImageButton for the location of the mall.
            //this ImageButton will start a map app to display the location of the mall.
            ImageButton mallLocationImageButton;

            public MallViewHolder(View itemView) {
                super(itemView);
                //After creating all the view objects we need to access, now we will link them
                //with the views in the {@item_restaurant} layout:
                mallPhotoImageView = itemView.findViewById(R.id.imageview_malls_photo);
                mallNameTextView = itemView.findViewById(R.id.textview_malls_name);
                mallWebsiteImageButton = itemView.findViewById(R.id.imagebutton_malls_website);
                mallLocationImageButton = itemView.findViewById(R.id.imagebutton_malls_location);

                //Change the color of the two ImageButtons.
                mallWebsiteImageButton.setColorFilter(getResources().getColor(R.color.secondColor));
                mallLocationImageButton.setColorFilter(getResources().getColor(R.color.secondColor));
            }

        }

    }
}
