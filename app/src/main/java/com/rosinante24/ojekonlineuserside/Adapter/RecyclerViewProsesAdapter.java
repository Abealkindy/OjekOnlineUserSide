package com.rosinante24.ojekonlineuserside.Adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.rosinante24.ojekonlineuserside.R;
import com.rosinante24.ojekonlineuserside.Response.DatumFindDriver;

/**
 * Created by KOCHOR on 9/15/2017.
 */

public class RecyclerViewProsesAdapter extends RecyclerView.Adapter<RecyclerViewProsesAdapter.ViewHolder> {

    List<DatumFindDriver> data;
    FragmentActivity fragmentManager;


    public RecyclerViewProsesAdapter(List<DatumFindDriver> data, FragmentActivity fragmentManager) {
        this.data = data;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflater = LayoutInflater.from(fragmentManager).inflate(R.layout.wadah_recycler, parent, false);
        return new ViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.texttgl.setText(data.get(position).getBookingTanggal());
        holder.txtawal.setText("Tempat awal : " + data.get(position).getBookingFrom());
        holder.txtakhir.setText("Tujuan : " + data.get(position).getBookingTujuan());
        holder.txtharga.setText("Harga : Rp." + data.get(position).getBookingBiayaUser());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.texttgl)
        TextView texttgl;
        @BindView(R.id.txtawal)
        TextView txtawal;
        @BindView(R.id.txtakhir)
        TextView txtakhir;
        @BindView(R.id.txtharga)
        TextView txtharga;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
