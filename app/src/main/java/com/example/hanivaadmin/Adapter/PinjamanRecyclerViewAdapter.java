package com.example.hanivaadmin.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanivaadmin.DetailPinjamanActivity;
import com.example.hanivaadmin.Model.data_lengkap_user;
import com.example.hanivaadmin.Model.pinjaman_user;
import com.example.hanivaadmin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PinjamanRecyclerViewAdapter extends RecyclerView.Adapter<PinjamanRecyclerViewAdapter.ViewHolder>{

    private ArrayList<pinjaman_user> listPinjaman;
    private ArrayList<data_lengkap_user> listUer;
    private Context context;
    private DatabaseReference mData_nama;
    private FirebaseDatabase firebaseDatabase;

    public PinjamanRecyclerViewAdapter(ArrayList<pinjaman_user> listPinjaman, Context context){
        this.listPinjaman = listPinjaman;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvJumlah, tvWaktu, tvJaminan, tvStatus, tvIduser;
        private CardView ListItem;

        ViewHolder(View itemView){
            super(itemView);
            tvIduser = itemView.findViewById(R.id.tvId);
            tvJumlah = itemView.findViewById(R.id.tvJmlh);
            tvWaktu = itemView.findViewById(R.id.tvJangkaWaktu);
            tvJaminan = itemView.findViewById(R.id.tvJaminan);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            ListItem = itemView.findViewById(R.id.list_item1);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.pinjaman_desgin,parent,false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final String IdUser  = listPinjaman.get(position).getIduser();
        final String Jumlah = listPinjaman.get(position).getJmlhpinjaman();
        final String Waktu = listPinjaman.get(position).getJangkawaktu();
        final String Jaminan = listPinjaman.get(position).getJaminan();
        final String Status = listPinjaman.get(position).getStatus();

        holder.tvIduser.setText("IdUser : "+IdUser);
        holder.tvJumlah.setText("Jumlah : Rp."+Jumlah);
        holder.tvWaktu.setText("Waktu : "+Waktu);
        holder.tvJaminan.setText("Jaminan : "+Jaminan);
        holder.tvStatus.setText(Status);

        holder.ListItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final String[] action = {"Lengkapi Survei", "Delete"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setItems(action,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                        /*
                          Berpindah Activity pada halaman layout updateData
                          dan mengambil data pada listMahasiswa, berdasarkan posisinya
                          untuk dikirim pada activity selanjutnya
                        */
                                Bundle bundle = new Bundle();
                                bundle.putString("dataJumlah", listPinjaman.get(position).getJmlhpinjaman());
                                bundle.putString("dataWaktu", listPinjaman.get(position).getJangkawaktu());
                                bundle.putString("dataJaminan", listPinjaman.get(position).getJaminan());
                                bundle.putString("dataStatus", listPinjaman.get(position).getStatus());
                                bundle.putString("dataIduser", listPinjaman.get(position).getIduser());
                                bundle.putString("getPrimaryKey", listPinjaman.get(position).getKey());
                                Intent intent = new Intent(view.getContext(), DetailPinjamanActivity.class);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                                break;

                            case 1:
                                //Pembahasan selanjutnya mengenai fungsi Delete
                                break;
                        }
                    }
                });
                alert.create();
                alert.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPinjaman.size();
    }
}
