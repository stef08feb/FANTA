package com.example.android.projectfanta;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int age;
    private int weight;
    private int height;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<Food> foods =  new ArrayList<Food>(Information.information.getMyFoods().values());
        ArrayList<Food> foodsToDisplay = new ArrayList<>();
        for(int i = 0; i < foods.size(); i++) {
            if(foods.get(i).getCount() > 0) {
                foodsToDisplay.add(foods.get(i));
            }
        }
        /*
        for(Food food: foods) {
            System.out.println("joyaan HHHHHHHHHHHHHHHHHHHHHHHHHHHHH" + food.getCount() +" "+ foods.size() +" "+ foodsToDisplay.size());
            if(food.getCount() > 0) {
                foodsToDisplay.add(food);
            }
        } */
        if(foodsToDisplay.size() == 0) {
            TextView text = (TextView) view.findViewById(R.id.onetext);
            text.setText("You have no favorite foods yet!");
            TextView text1 = (TextView) view.findViewById(R.id.onetext);
            TextView text2 = (TextView) view.findViewById(R.id.twotext);
            TextView text3 = (TextView) view.findViewById(R.id.threetext);
            TextView text4 = (TextView) view.findViewById(R.id.fourtext);
            TextView text5 = (TextView) view.findViewById(R.id.fivetext);
            TextView text6 = (TextView) view.findViewById(R.id.sixtext);

            text1.setClickable(false);
            text2.setClickable(false);
            text3.setClickable(false);
            text4.setClickable(false);
            text5.setClickable(false);
            text6.setClickable(false);

        }
        else {
            Collections.sort(foodsToDisplay, new Comparator<Food>(){
                // Reverse order sorting means 1 and -1 are switched
                public int compare(Food o1, Food o2) {
                    if (o1.getCount() <= o2.getCount()) {
                        return 1;
                    } else if (o1.getCount() >= o2.getCount()){
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });

            TextView text1 = (TextView) view.findViewById(R.id.onetext);
            text1.setText(foodsToDisplay.get(0).getName());
            text1.setClickable(true);

            if(foodsToDisplay.size() > 1) {
                TextView text = (TextView) view.findViewById(R.id.twotext);
                text.setText(foodsToDisplay.get(1).getName());
                text.setClickable(true);
            }

            if(foodsToDisplay.size() > 2) {
                TextView text = (TextView) view.findViewById(R.id.threetext);
                text.setText(foodsToDisplay.get(2).getName());
                text.setClickable(true);
            }

            if(foodsToDisplay.size() > 3) {
                TextView text = (TextView) view.findViewById(R.id.fourtext);
                text.setText(foodsToDisplay.get(3).getName());
                text.setClickable(true);

            }

            if(foodsToDisplay.size() > 4) {
                TextView text = (TextView) view.findViewById(R.id.fivetext);
                text.setText(foodsToDisplay.get(4).getName());
                text.setClickable(true);
            }

            if(foodsToDisplay.size() > 5) {
                TextView text = (TextView) view.findViewById(R.id.sixtext);
                text.setText(foodsToDisplay.get(5).getName());
                text.setClickable(true);
            }
        }


        // UI components for navigation bar
        final FloatingActionButton fab_plus, fab_search, fab_camera, fab_fresh, fab_manual;
        final Animation fabOpen, fabClose, fabForward, fabBackward;


        fab_plus = (FloatingActionButton)view.findViewById(R.id.plus);
        fab_search = (FloatingActionButton)view.findViewById(R.id.search);
        fab_camera = (FloatingActionButton)view.findViewById(R.id.camera);
        fab_manual = (FloatingActionButton)view.findViewById(R.id.manual);

        fabOpen = AnimationUtils.loadAnimation(getActivity(),R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getActivity(),R.anim.fab_close);
        fabForward = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_forward);
        fabBackward = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_backward);

        fab_plus.setOnClickListener(new View.OnClickListener(){
            boolean isOpen = false;
            @Override
            public void onClick(View v) {
                if(isOpen){
                    fab_camera.startAnimation(fabClose);
                    fab_manual.startAnimation(fabClose);
                    fab_search.startAnimation(fabClose);
                    fab_plus.startAnimation(fabBackward);
                    fab_camera.setClickable(false);
                    fab_manual.setClickable(false);
                    fab_search.setClickable(false);
                    isOpen = false;
                }
                else{
                    fab_camera.startAnimation(fabOpen);
                    fab_manual.startAnimation(fabOpen);
                    fab_search.startAnimation(fabOpen);
                    fab_plus.startAnimation(fabForward);
                    fab_camera.setClickable(true);
                    fab_manual.setClickable(true);
                    fab_search.setClickable(true);
                    isOpen = true;
                }
            }
        });

        //TODO: fab_search for searching recent food

        fab_search.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view){
                Intent search_intent = new Intent(getContext(), FoodListActivity.class);
                startActivity(search_intent);
            }

        });

        fab_manual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent manual_intent = new Intent(getContext(), NutritionLabelConfirmActivity.class);
                manual_intent.putExtra("MAN", true);
                startActivity(manual_intent);
            }
        });

        fab_camera.setOnClickListener(new View.OnClickListener(){
                  @Override
                  public void onClick(View view){
                      Intent intent = new Intent(getContext(), CameraActivity.class);
                      startActivityForResult(intent, 0);
                  }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //do nothing
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
