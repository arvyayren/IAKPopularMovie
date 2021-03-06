package com.example.user.iakpopularmovie.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.iakpopularmovie.DataMovie;
import com.example.user.iakpopularmovie.R;
import com.example.user.iakpopularmovie.adapter.AdapterMovie;
import com.example.user.iakpopularmovie.connection.ApiServices;
import com.example.user.iakpopularmovie.connection.RetroConfig;
import com.example.user.iakpopularmovie.model.ModelListMovie;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment {

    @BindView(R.id.rvmovie)
    RecyclerView rvmovie;
    butterknife.Unbinder unbinder;
    ProgressDialog dialog;
    ArrayList<DataMovie> datamovies = new ArrayList<>();

    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState!=null){
            datamovies = savedInstanceState.getParcelableArrayList("data");
        }
        else {
            getdata();
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        unbinder = butterknife.ButterKnife.bind(this, view);
        //getdata();
        getActivity().setTitle("Popular");
        AdapterMovie adapter = new AdapterMovie(getContext(), datamovies);
        rvmovie.setAdapter(adapter);
        rvmovie.setLayoutManager(new GridLayoutManager(getActivity(),2));
        return view;
    }

    private void getdata() {
        dialog = new ProgressDialog(getContext());
        dialog.setTitle("loading");
        dialog.setMessage("mohon bersabar....");
        dialog.show();

        ApiServices api = RetroConfig.getApiServices();
        Call<ModelListMovie> movieCall = api.getPopular();

        //untuk menangkap respon
        movieCall.enqueue(new Callback<ModelListMovie>() {
            @Override
            public void onResponse(Call<ModelListMovie> call, Response<ModelListMovie> response) {
                dialog.dismiss();
                if (response.isSuccessful()){
                    datamovies = response.body().getResults();

                    AdapterMovie adapter = new AdapterMovie(getContext(), datamovies);
                    //setData Movie ke View
                    rvmovie.setAdapter(adapter);
                    rvmovie.setLayoutManager(new GridLayoutManager(getContext(),2));
                } else {
                    Toast.makeText(getContext(),"response is not successfull", Toast.LENGTH_SHORT).show();


                }

            }


            @Override
            public void onFailure(Call<ModelListMovie> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getContext(),""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onPause() {
        super.onPause();
        //dialog.dismiss();
    }
    //agar view scrollnya tidak mulai dari awal lagi
    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putParcelableArrayList("data", datamovies);
    }
}
