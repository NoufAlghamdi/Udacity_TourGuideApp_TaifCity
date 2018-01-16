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

import com.example.android.taifcity.R;
import com.example.android.taifcity.models.Restaurant;

import java.util.ArrayList;

public class RestaurantsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.view_recycler, container, false);

        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(R.string.restaurants_restaurant1name,
                R.drawable.photo_restaurants_restaurant1revolving,
                R.string.restaurants_restaurant1category, R.string.restaurants_restaurant1website,
                R.string.restaurants_restaurant1phonenumber, R.string.restaurants_restaurant1location));
        restaurants.add(new Restaurant(R.string.restaurants_restaurant2name,
                R.drawable.photo_restaurants_restaurant2mirage,
                R.string.restaurants_restaurant2category, R.string.restaurants_restaurant2website,
                R.string.restaurants_restaurant2phonenumber, R.string.restaurants_restaurant2location));
        restaurants.add(new Restaurant(R.string.restaurants_restaurant3name,
                R.drawable.photo_restaurants_restaurant3dragon,
                R.string.restaurants_restaurant3category, R.string.restaurants_restaurant3website,
                R.string.restaurants_restaurant3phonenumber, R.string.restaurants_restaurant3location));
        restaurants.add(new Restaurant(R.string.restaurants_restaurant4name,
                R.drawable.photo_restaurants_restaurant4lacochina,
                R.string.restaurants_restaurant4category, R.string.restaurants_restaurant4website,
                R.string.restaurants_restaurant4phonenumber, R.string.restaurants_restaurant4location));
        restaurants.add(new Restaurant(R.string.restaurants_restaurant5name,
                R.drawable.photo_restaurants_restaurant5stone,
                R.string.restaurants_restaurant5category, R.string.restaurants_restaurant5website,
                R.string.restaurants_restaurant5phonenumber, R.string.restaurants_restaurant5location));
        restaurants.add(new Restaurant(R.string.restaurants_restaurant6name,
                R.drawable.photo_restaurants_restaurant6durrat,
                R.string.restaurants_restaurant6category, R.string.restaurants_restaurant6website,
                R.string.restaurants_restaurant6phonenumber, R.string.restaurants_restaurant6location));
        restaurants.add(new Restaurant(R.string.restaurants_restaurant7name,
                R.drawable.photo_restaurants_restaurant7ahlan,
                R.string.restaurants_restaurant7category, R.string.restaurants_restaurant7website,
                R.string.restaurants_restaurant7phonenumber, R.string.restaurants_restaurant7location));

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerview_all_rootview);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new RestaurantAdapter(restaurants);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

        private ArrayList<Restaurant> restaurantArrayList;

        RestaurantAdapter(ArrayList<Restaurant> restaurantArrayList) {
            this.restaurantArrayList = restaurantArrayList;
        }

        // Create new views (invoked by the layout manager).
        @Override
        public RestaurantAdapter.RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_restaurant, parent, false);

            return new RestaurantViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RestaurantAdapter.RestaurantViewHolder holder, final int position) {
            //Setup the photograph of the restaurant.
            holder.restaurantPhotoImageView.setImageResource(restaurantArrayList.get(position).getPhotograph());
            //Set the content description of the ImageView.
            holder.restaurantPhotoImageView.setContentDescription(getString(restaurantArrayList.get(position).getName()));

            //Set the name of the restaurant.
            holder.restaurantNameTextView.setText(restaurantArrayList.get(position).getName());

            //Display the category/food type of the restaurant.
            holder.restaurantCategoryTextView.setText(restaurantArrayList.get(position).getCategory());

            //Set OnClickListener to the three ImageButtons.
            //1. restaurantWebsiteImageButton.
            holder.restaurantWebsiteImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Start a web browser activity to view the restaurant's website.
                    Uri webPage = Uri.parse(getResources().getString(restaurantArrayList.get(position)
                            .getWebsite()));
                    Intent intent = new Intent(Intent.ACTION_VIEW, webPage);

                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });

            //2. restaurantPhoneNumberImageButton.
            holder.restaurantPhoneNumberImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Start a dialer or phone app with the phone number of the restaurant.
                    String phoneNumber = getResources().getString(restaurantArrayList.get(position)
                            .getPhoneNumber());
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(phoneNumber));

                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });

            //3. restaurantLocationImageButton.
            holder.restaurantLocationImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Start a map app to display the location of the restaurant.
                    String location = getResources().getString(restaurantArrayList.get(position).getLocation());
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
            return restaurantArrayList.size();
        }

        public class RestaurantViewHolder extends RecyclerView.ViewHolder {
            //Create references to all views that we need to access inside each restaurant item
            // card {@link item_restaurant}:

            //First, the views display the details of the current restaurant:
            //1. The ImageView display a photograph of the restaurant.
            ImageView restaurantPhotoImageView;
            //2. The TextView display the name of the restaurant.
            TextView restaurantNameTextView;
            //3. The TextView display the category/food type of the restaurant.
            TextView restaurantCategoryTextView;
            //4. A label icon will be to the right/end of the restaurantCategoryTextView,
            //and we need to access it to change its color to make it match the text color of the
            //restaurantCategoryTextView.
            ImageView restaurantCategoryImageView;

            //Second, the three ImageViews, we will attach an OnClickListener to each of them,
            //but first we need to change their color;
            //1. The ImageButton for the website of the restaurant.
            //this ImageButton will start a web browser activity to view the restaurant's website.
            ImageButton restaurantWebsiteImageButton;
            //2. The ImageButton for the phone number of the restaurant.
            //this ImageButton will start a dialer or phone app.
            ImageButton restaurantPhoneNumberImageButton;
            //3. The ImageButton for the location of the restaurant.
            //this ImageButton will start a map app to display the location of the restaurant.
            ImageButton restaurantLocationImageButton;

            public RestaurantViewHolder(View itemView) {
                super(itemView);
                //After creating all the view objects we need to access, now we will link them
                //with the views in the {@item_restaurant} layout:
                restaurantPhotoImageView = itemView.findViewById(R.id.imageview_restaurants_photo);
                restaurantNameTextView = itemView.findViewById(R.id.textview_restaurants_name);
                restaurantCategoryTextView = itemView.findViewById(R.id.textview_restaurants_category);
                restaurantCategoryImageView = itemView.findViewById(R.id.imageview_restaurants_category);
                restaurantWebsiteImageButton = itemView.findViewById(R.id.imagebutton_restaurants_website);
                restaurantPhoneNumberImageButton = itemView.findViewById(R.id.imagebutton_restaurants_phonenumber);
                restaurantLocationImageButton = itemView.findViewById(R.id.imagebutton_restaurants_location);


                //Change the color of the restaurantCategoryImageView to match the color of
                // the restaurantCategoryTextView.
                restaurantCategoryImageView.setColorFilter(getResources().getColor(
                        R.color.secondaryTextColor));

                //Change the color of the three ImageButtons.
                restaurantWebsiteImageButton.setColorFilter(getResources().getColor(R.color.secondColor));
                restaurantPhoneNumberImageButton.setColorFilter(getResources().getColor(R.color.secondColor));
                restaurantLocationImageButton.setColorFilter(getResources().getColor(R.color.secondColor));
            }

        }
    }
}
