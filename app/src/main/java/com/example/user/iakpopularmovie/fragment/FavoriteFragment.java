package com.example.user.iakpopularmovie.fragment;


import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.iakpopularmovie.DataMovie;
import com.example.user.iakpopularmovie.R;
import com.example.user.iakpopularmovie.adapter.AdapterMovie;
import com.example.user.iakpopularmovie.database.MovieContract;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    @BindView(R.id.rvfavorite)
    RecyclerView rvfavorite;
    butterknife.Unbinder unbinder;
    ProgressDialog dialog;
    ArrayList<DataMovie> dataMovies;


    public FavoriteFragment() {
        // Required empty public constructor
    }


    RecyclerView recycler;
    ArrayList<DataMovie> listdata = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_favorite, container, false);
        recycler = (RecyclerView) fragmentView.findViewById(R.id.rvfavorite);

        getActivity().setTitle("Favorite");
        //data real
        getActivity().getSupportLoaderManager().initLoader(100, null, this);

        //  Adapter
        AdapterMovie adapter = new AdapterMovie(getActivity(), listdata);
        recycler.setAdapter(adapter);

        //  Layout Manager
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return fragmentView;
    }

    //Membuat instance dan mengembalikan Loader baru
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case 100:
                return new CursorLoader(
                        getActivity(),
                        MovieContract.MovieEntry.CONTENT_URI,
                        null, null, null, null);
            default:
                throw new RuntimeException("Loader Not working");
        }
    }

    // Dipanggil apabila loader yang dibuat sebelumnya selesai dijalankan
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        listdata.clear();
        if (data.getCount() > 0) {
            if (data.moveToFirst()) {
                do {
                    DataMovie movie = new DataMovie();
                    movie.setId(data.getInt(data.getColumnIndex(MovieContract.MovieEntry.COLUMN_ID)));
                    movie.setTitle(data.getString(data.getColumnIndex(MovieContract.MovieEntry.COLUMN_JUDUL)));
                    movie.setPosterPath(data.getString(data.getColumnIndex(MovieContract.MovieEntry.COLUMN_POSTER)));
                    movie.setOverview(data.getString(data.getColumnIndex(MovieContract.MovieEntry.COLUMN_OVERVIEW)));
                    movie.setVoteAverage(data.getDouble(data.getColumnIndex(String.valueOf(MovieContract.MovieEntry.COLUMN_POPULAR))));
                    movie.setReleaseDate(data.getString(data.getColumnIndex(MovieContract.MovieEntry.COLUMN_RD)));
                    movie.setBackdropPath(data.getString(data.getColumnIndex(MovieContract.MovieEntry.COLUMN_BACKDROP)));
                    //4 tambah sini
                    listdata.add(movie);
                    AdapterMovie adapter = new AdapterMovie(getActivity(), listdata);
                    recycler.setAdapter(adapter);
                } while (data.moveToNext());
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getSupportLoaderManager().initLoader(100, null, this);
    }
}