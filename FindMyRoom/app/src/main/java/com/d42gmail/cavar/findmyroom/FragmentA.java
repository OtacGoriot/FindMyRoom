package com.d42gmail.cavar.findmyroom;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Enigma on 27.11.2015..
 */
public class FragmentA extends Fragment{

    ListView listView;
    ArrayList<Place> arrayPlaces=new ArrayList<Place>();
    PlaceAdapter adapter;
    Communucator comm;
    String[] PlaceArray;
    String[] AdresArray;
    String[] DescriptionArray;
    String[] CityArray;
    int[] RateArray;
    TypedArray PicturesArrayMain,PicturesArrayOne,PicturesArrayTwo,PicturesArrayThree;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragmenta,container,false);

        listView= (android.widget.ListView) view.findViewById(R.id.listView);
        Place place=new Place();
        adapter=new PlaceAdapter(getActivity(),Populate(arrayPlaces));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place place = new Place();
                place = adapter.getItem(position);
                comm.respond(place);
            }
        });

        return view;
    }

    private ArrayList<Place> Populate(ArrayList<Place> arrayPlace) {

        PlaceArray=getResources().getStringArray(R.array.place);
        AdresArray=getResources().getStringArray(R.array.adress);
        CityArray=getResources().getStringArray(R.array.city);
        DescriptionArray=getResources().getStringArray(R.array.description);
        RateArray=getResources().getIntArray(R.array.rate);
        PicturesArrayMain=getResources().obtainTypedArray(R.array.picturesMain);
        PicturesArrayOne=getResources().obtainTypedArray(R.array.picturesOne);
        PicturesArrayTwo=getResources().obtainTypedArray(R.array.picturesTwo);
        PicturesArrayThree=getResources().obtainTypedArray(R.array.picturesThree);

        Log.i("slija",""+getResources().getDrawable(R.drawable.vl1));
        for(int i=0;i<=4;i++){
            Place place1=new Place();
            place1.setPlaceName(PlaceArray[i]);
            place1.setPlaceAdress(AdresArray[i]);
            place1.setPlaceCity(CityArray[i]);
            place1.setPlaceDescription(DescriptionArray[i]);
            place1.setPlaceRate(RateArray[i]);
            place1.setImageMain(PicturesArrayMain.getResourceId(i, 0));
            place1.setImgOne(PicturesArrayOne.getResourceId(i, 0));
            place1.setImgTwo(PicturesArrayTwo.getResourceId(i, 0));
            place1.setImgThree(PicturesArrayThree.getResourceId(i, 0));


            arrayPlace.add(place1);
        }
        return arrayPlace;
    }

    public void setCommunicator(Communucator comm){
            this.comm=comm;

        }

    public interface Communucator{
         void respond(Place place);
    }

}