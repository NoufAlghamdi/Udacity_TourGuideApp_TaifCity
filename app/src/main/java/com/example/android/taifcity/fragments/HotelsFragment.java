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

import com.example.android.taifcity.models.Hotel;
import com.example.android.taifcity.R;

import java.util.ArrayList;

public class HotelsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.view_recycler, container, false);

        ArrayList<Hotel> hotels = new ArrayList<Hotel>();
        hotels.add(new Hotel(R.string.hotels_hotel1name, R.drawable.photo_hotels_hotel1awaliv,
                R.string.hotels_hotel1class, R.string.hotels_hotel1distance,
                R.string.hotels_hotel1website, R.string.hotels_hotel1phonenumber,
                R.string.hotels_hotel1location));
        hotels.add(new Hotel(R.string.hotels_hotel2name, R.drawable.photo_hotels_hotel2intercontintal,
                R.string.hotels_hotel2class, R.string.hotels_hotel2distance,
                R.string.hotels_hotel2website, R.string.hotels_hotel2phonenumber,
                R.string.hotels_hotel2location));
        hotels.add(new Hotel(R.string.hotels_hotel3name, R.drawable.photo_hotels_hotel3lemeridien,
                R.string.hotels_hotel3class, R.string.hotels_hotel3distance,
                R.string.hotels_hotel3website, R.string.hotels_hotel3phonenumber,
                R.string.hotels_hotel3location));
        hotels.add(new Hotel(R.string.hotels_hotel4name, R.drawable.photo_hotels_hotel4ramada,
                R.string.hotels_hotel4class, R.string.hotels_hotel4distance,
                R.string.hotels_hotel4website, R.string.hotels_hotel4phonenumber,
                R.string.hotels_hotel4location));
        hotels.add(new Hotel(R.string.hotels_hotel5name, R.drawable.photo_hotels_hotel5sadeem,
                R.string.hotels_hotel5class, R.string.hotels_hotel5distance,
                R.string.hotels_hotel5website, R.string.hotels_hotel5phonenumber,
                R.string.hotels_hotel5location));
        hotels.add(new Hotel(R.string.hotels_hotel6name, R.drawable.photo_hotels_hotel6tulip,
                R.string.hotels_hotel6class, R.string.hotels_hotel6distance,
                R.string.hotels_hotel6website, R.string.hotels_hotel6phonenumber,
                R.string.hotels_hotel6location));
        hotels.add(new Hotel(R.string.hotels_hotel7name, R.drawable.photo_hotels_hotel7arramla,
                R.string.hotels_hotel7class, R.string.hotels_hotel7distance,
                R.string.hotels_hotel7website, R.string.hotels_hotel7phonenumber,
                R.string.hotels_hotel7location));

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview_all_rootview);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new HotelAdapter(hotels);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

        private ArrayList<Hotel> hotelArrayList;

        HotelAdapter(ArrayList<Hotel> hotelArrayList) {
            this.hotelArrayList = hotelArrayList;
        }

        // Create new views (invoked by the layout manager).
        @Override
        public HotelAdapter.HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_hotel, parent, false);

            return new HotelViewHolder(view);
        }

        @Override
        public void onBindViewHolder(HotelAdapter.HotelViewHolder holder, final int position) {
            //Setup the photograph of the hotel.
            holder.hotelPhotoImageView.setImageResource(hotelArrayList.get(position).getPhotograph());
            //Set the content description of the ImageView.
            holder.hotelPhotoImageView.setContentDescription(getString(hotelArrayList.get(position).getName()));

            //Set the name of the hotel.
            holder.hotelNameTextView.setText(hotelArrayList.get(position).getName());

            //Display the hotel class, by display a specific number of starXImageView.
            //The hotelClass field of the Hotel class is holding an int recourse id of a string value,
            //that sting value will a character of 1-4(1,2,3,4,or 5), so we will take this value and
            //convert it to int, then we will use that int to determine the number of starXImageView
            //will be visible.
            //First, insure that all of them are visible.
            holder.star1ImageView.setVisibility(View.VISIBLE);
            holder.star2ImageView.setVisibility(View.VISIBLE);
            holder.star3ImageView.setVisibility(View.VISIBLE);
            holder.star4ImageView.setVisibility(View.VISIBLE);
            holder.star5ImageView.setVisibility(View.VISIBLE);
            //Second, get the number of the stars need to stay visible and hide the remaining.
            int currentHotelClass = Integer.parseInt(getResources().getString(
                    hotelArrayList.get(position).getHotelClass()));
            if (currentHotelClass <= 4)
                holder.star5ImageView.setVisibility(View.INVISIBLE);
            if (currentHotelClass <= 3)
                holder.star4ImageView.setVisibility(View.INVISIBLE);
            if (currentHotelClass <= 2)
                holder.star3ImageView.setVisibility(View.INVISIBLE);
            if (currentHotelClass <= 1) {
                holder.star2ImageView.setVisibility(View.INVISIBLE);
                holder.star1ImageView.setVisibility(View.INVISIBLE);
            }

            //Display the distance from the city center to the hotel.
            //Again as the hotelClass the distance field refer to a string value, but here we don't
            //need to convert it, we just need to add it to the string: " km from the city center".
            String distanceString = getResources().getString(hotelArrayList.get(position)
                    .getDistanceFromCityCenter());
            distanceString += getResources().getString(R.string.hotels_distancephrase);
            holder.hotelDistanceTextView.setText(distanceString);

            //Set OnClickListener to the three ImageButtons.
            //1. hotelWebsiteImageButton.
            holder.hotelWebsiteImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Start a web browser activity to view the hotel's website.
                    Uri webPage = Uri.parse(getResources().getString(hotelArrayList.get(position)
                            .getWebsite()));
                    Intent intent = new Intent(Intent.ACTION_VIEW, webPage);

                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });

            //2. hotelPhoneNumberImageButton.
            holder.hotelPhoneNumberImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Start a dialer or phone app with the phone number of the hotel.
                    String phoneNumber = getResources().getString(hotelArrayList.get(position)
                            .getPhoneNumber());
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(phoneNumber));

                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });

            //3. hotelLocationImageButton.
            holder.hotelLocationImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Start a map app to display the location of the hotel.
                    String location = getResources().getString(hotelArrayList.get(position).getLocation());
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
            return hotelArrayList.size();
        }

        public class HotelViewHolder extends RecyclerView.ViewHolder {
            //Create references to all views that we need to access inside each hotel item
            // card {@link item_hotel}:

            //First, the views display the details of the current hotel:
            //1. The ImageView display a photograph of the hotel.
            ImageView hotelPhotoImageView;
            //2. The TextView display the name of the hotel.
            TextView hotelNameTextView;
            //3. The five ImageViews that display the hotel class, each one of these views has an image
            //of a star, and we will control the visibility of them, so the number of the views that
            //will be visible reflect the hotel class.
            ImageView star1ImageView;
            ImageView star2ImageView;
            ImageView star3ImageView;
            ImageView star4ImageView;
            ImageView star5ImageView;
            //4. The TextView display the distance from the city center to the hotel.
            TextView hotelDistanceTextView;
            //5. A location icon will be to the left/start of the hotelDistanceTextView,
            //and we need to access it to change its color to make it match the text color of the
            //hotelDistanceTextView.
            ImageView hotelDistanceImageView;

            //Second, the three ImageViews, we will attach an OnClickListener to each of them,
            //but first we need to change their color;
            //1. The ImageButton for the website of the hotel.
            //this ImageButton will start a web browser activity to view the hotel's website.
            ImageButton hotelWebsiteImageButton;
            //2. The ImageButton for the phone number of the hotel.
            //this ImageButton will start a dialer or phone app.
            ImageButton hotelPhoneNumberImageButton;
            //3. The ImageButton for the location of the hotel.
            //this ImageButton will start a map app to display the location of the hotel.
            ImageButton hotelLocationImageButton;

            public HotelViewHolder(View itemView) {
                super(itemView);
                //After creating all the view objects we need to access, now we will link them
                //with the views in the {@item_hotel} layout:
                hotelPhotoImageView = itemView.findViewById(R.id.imageview_hotels_photo);
                hotelNameTextView = itemView.findViewById(R.id.textview_hotels_name);
                star1ImageView = itemView.findViewById(R.id.imageview_hotels_star1);
                star2ImageView = itemView.findViewById(R.id.imageview_hotels_star2);
                star3ImageView = itemView.findViewById(R.id.imageview_hotels_star3);
                star4ImageView = itemView.findViewById(R.id.imageview_hotels_star4);
                star5ImageView = itemView.findViewById(R.id.imageview_hotels_star5);
                hotelDistanceTextView = itemView.findViewById(R.id.textview_hotels_distance);
                hotelDistanceImageView = itemView.findViewById(R.id.imageview_hotels_distance);
                hotelWebsiteImageButton = itemView.findViewById(R.id.imagebutton_hotels_website);
                hotelPhoneNumberImageButton = itemView.findViewById(R.id.imagebutton_hotels_phonenumber);
                hotelLocationImageButton = itemView.findViewById(R.id.imagebutton_hotels_location);

                //Change the colors of the stars
                star1ImageView.setColorFilter(getResources().getColor(R.color.colorAccent));
                star2ImageView.setColorFilter(getResources().getColor(R.color.colorAccent));
                star3ImageView.setColorFilter(getResources().getColor(R.color.colorAccent));
                star4ImageView.setColorFilter(getResources().getColor(R.color.colorAccent));
                star5ImageView.setColorFilter(getResources().getColor(R.color.colorAccent));

                //Change the color of the hotelDistanceImageView to match the color of
                // the hotelDistanceTextView.
                hotelDistanceImageView.setColorFilter(getResources().getColor(
                        R.color.secondaryTextColor));

                //Change the color of the three ImageButtons.
                hotelWebsiteImageButton.setColorFilter(getResources().getColor(R.color.secondColor));
                hotelPhoneNumberImageButton.setColorFilter(getResources().getColor(R.color.secondColor));
                hotelLocationImageButton.setColorFilter(getResources().getColor(R.color.secondColor));
            }

        }
    }
}

