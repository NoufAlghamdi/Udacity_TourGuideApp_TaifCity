package com.example.android.taifcity.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.taifcity.models.Park;
import com.example.android.taifcity.R;

import java.util.ArrayList;

public class ParksFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.view_recycler, container, false);

        ArrayList<Park> parks = new ArrayList<>();
        parks.add(new Park(R.string.parks_park1name, R.drawable.photo_parks_park1abdullah,
                R.string.parks_park1openhours, R.string.parks_park1location));
        parks.add(new Park(R.string.parks_park2name, R.drawable.photo_parks_park2arruddaf,
                R.string.parks_park2openhours, R.string.parks_park2location));
        parks.add(new Park(R.string.parks_park3name, R.drawable.photo_parks_park3faisal,
                R.string.parks_park3openhours, R.string.parks_park3location));
        parks.add(new Park(R.string.parks_park4name, R.drawable.photo_parks_park4fahad,
                R.string.parks_park4openhours, R.string.parks_park4location));
        parks.add(new Park(R.string.parks_park5name, R.drawable.photo_parks_park5ashallal,
                R.string.parks_park5openhours, R.string.parks_park5location));

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview_all_rootview);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new ParkAdapter(parks);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public class ParkAdapter extends RecyclerView.Adapter<ParkAdapter.ParkViewHolder> {

        private ArrayList<Park> parkArrayList;

        // Provide a suitable constructor (depends on the kind of dataset)
        ParkAdapter(ArrayList<Park> parkArrayList) {
            this.parkArrayList = parkArrayList;
        }

        // Create new views (invoked by the layout manager).
        @Override
        public ParkAdapter.ParkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_park, parent, false);

            // set the view's size, margins, paddings and layout parameters
            return new ParkViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ParkViewHolder holder, final int position) {

            //Setup the photograph of the park.
            holder.parkPhotoImageView.setImageResource(parkArrayList.get(position).getPhotograph());
            //Set the content description of the ImageView.
            holder.parkPhotoImageView.setContentDescription(getString(parkArrayList.get(position).getName()));

            //Set the name of the park.
            holder.parkNameTextView.setText(parkArrayList.get(position).getName());

            //Set the open hours of the park.
            holder.parkOpenHoursTextView.setText(parkArrayList.get(position).getOpenHours());

            //Set OnClickListener to the parkLocationImageButton.
            holder.parkLocationImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Start a map app to display the location of the restaurant.
                    String location = getResources().getString(parkArrayList.get(position).getLocation());
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
            return parkArrayList.size();
        }

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        class ParkViewHolder extends RecyclerView.ViewHolder {
            //Create references to all views that we need to access inside each park item
            // card {@link item_park}:

            //First, the views display the details of the current park:
            //1. The ImageView display a photograph of the park.
            ImageView parkPhotoImageView;
            //2. The TextView display the name of the park.
            TextView parkNameTextView;
            //3. The TextView display the open hours of the park.
            TextView parkOpenHoursTextView;
            //4. An open hours icon will be to the left/start of the parkOpenHoursTextView,
            //and we need to access it to change its color to make it match the text color of the
            //parkOpenHoursTextView.
            ImageView parkOpenHoursImageView;

            //Second, the ImageView, which is the ImageButton for the location of the park,
            // we will attach an OnClickListener to it, but first we need to change its color.
            //this ImageButton will start a map app to display the location of the park.
            ImageButton parkLocationImageButton;

            ParkViewHolder(View itemView) {
                super(itemView);
                //After creating all the view objects we need to access, now we will link them
                //with the views in the {@item_park} layout:
                parkPhotoImageView = itemView.findViewById(R.id.imageview_parks_photo);
                parkNameTextView = itemView.findViewById(R.id.textview_parks_name);
                parkOpenHoursTextView = itemView.findViewById(R.id.textview_parks_openhours);
                parkOpenHoursImageView = itemView.findViewById(R.id.imageview_parks_openhours);
                parkLocationImageButton = itemView.findViewById(R.id.imagebutton_parks_location);

                //Change the color of the parkLocationImageButton.
                parkLocationImageButton.setColorFilter(getResources().getColor(R.color.secondColor));
                //Change the color of the parkOpenHoursImageView to make it match the color of
                //parkOpenHoursTextView.
                parkOpenHoursImageView.setColorFilter(getResources().getColor(R.color.secondaryTextColor));
            }
        }

    }


}


