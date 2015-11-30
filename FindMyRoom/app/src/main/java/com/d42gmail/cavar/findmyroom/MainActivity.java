package com.d42gmail.cavar.findmyroom;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements FragmentA.Communucator {

    FragmentA fragA;
    FragmentB fragB;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isTablet=tabletTest(getApplicationContext());
        if(isTablet==true) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            Log.i("prikaz","tablet sam");
        }
        else
        {
            Log.i("prikaz","mob sam");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }



        fragmentManager=getFragmentManager();
        fragA = (FragmentA) fragmentManager.findFragmentById(R.id.fragment);
        fragA.setCommunicator(this);
    }

    @Override
    public void respond(Place place) {

        fragB= (FragmentB) fragmentManager.findFragmentById(R.id.fragment2);

        if(fragB != null && fragB.isVisible())
        {//ako je tablet na landscapeu
            layoutChange();
            fragB.mainImage.setImageResource(place.getImageMain());
            fragB.imageOne.setImageResource(place.getImgOne());
            fragB.imageTwo.setImageResource(place.getImgTwo());
            fragB.imageThree.setImageResource(place.getImgThree());
            fragB.Name.setText(place.getPlaceName());
            fragB.Description.setText(place.getPlaceDescription());
            fragB.Adress.setText(String.format("%s\n%s",place.getPlaceAdress(),place.getPlaceCity()));
            fragB.ratingBar.setRating(place.getPlaceRate());
            Log.i("stanje", "Tablet Landscape");
        }
        else
        {
            Intent intent=new Intent(this,Show.class);
            intent.putExtra("PlaceClass", place);
            startActivityForResult(intent, 0);

        }


    }

    private void layoutChange() {

        fragB.mainImage.requestLayout();
        fragB.mainImage.getLayoutParams().height=350;
        fragB.imageOne.requestLayout();
        fragB.imageOne.getLayoutParams().height=175;
        fragB.imageOne.getLayoutParams().width=175;
        fragB.imageTwo.requestLayout();
        fragB.imageTwo.getLayoutParams().height=175;
        fragB.imageTwo.getLayoutParams().width=175;
        fragB.imageThree.requestLayout();
        fragB.imageThree.getLayoutParams().height=175;
        fragB.imageThree.getLayoutParams().width=175;

        fragB.mainImage.setClickable(true);
        fragB.imageOne.setClickable(true);
        fragB.imageTwo.setClickable(true);
        fragB.imageThree.setClickable(true);


    }

    public static boolean tabletTest(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
