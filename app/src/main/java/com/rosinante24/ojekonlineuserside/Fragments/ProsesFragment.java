package com.rosinante24.ojekonlineuserside.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.rosinante24.ojekonlineuserside.Adapter.RecyclerViewProsesAdapter;
import com.rosinante24.ojekonlineuserside.Helper.HeroHelper;
import com.rosinante24.ojekonlineuserside.Helper.SessionManager;
import com.rosinante24.ojekonlineuserside.Network.ApiServices;
import com.rosinante24.ojekonlineuserside.Network.InitLibrary;
import com.rosinante24.ojekonlineuserside.R;
import com.rosinante24.ojekonlineuserside.Response.DatumFindDriver;
import com.rosinante24.ojekonlineuserside.Response.ResponseHistory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProsesFragment extends Fragment {


    @BindView(R.id.recycler_proses)
    RecyclerView recyclerProses;
    Unbinder unbinder;

    public ProsesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_proses, container, false);

        getDataBooking();
        // Inflate the layout for this fragment
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void getDataBooking() {
        ApiServices api = InitLibrary.getInstances();
        SessionManager sesi = new SessionManager(getActivity());
        Call<ResponseHistory> call = api.request_history("2", sesi.getIdUser());
        call.enqueue(new Callback<ResponseHistory>() {
            @Override
            public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResult().equals("true")) {
                        List<DatumFindDriver> datum = response.body().getData();
                        RecyclerViewProsesAdapter adapter = new RecyclerViewProsesAdapter(datum, getActivity());
                        recyclerProses.setAdapter(adapter);
                        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                        recyclerProses.setLayoutManager(llm);

                    } else {
                        HeroHelper.pesan(getActivity(), response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseHistory> call, Throwable t) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
