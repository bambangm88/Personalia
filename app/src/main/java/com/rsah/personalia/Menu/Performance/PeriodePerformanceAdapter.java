package com.rsah.personalia.Menu.Performance;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rsah.personalia.Menu.SlipGaji.slipGaji;
import com.rsah.personalia.Model.ResponseEntityPeriode;
import com.rsah.personalia.R;
import com.rsah.personalia.sessionManager.SessionManager;

import java.util.List;

import butterknife.ButterKnife;

import static com.rsah.personalia.Menu.SlipGaji.slipGaji.TAG_PERIODE;


public class PeriodePerformanceAdapter extends RecyclerView.Adapter<PeriodePerformanceAdapter.AdapterHolder>{

    List<ResponseEntityPeriode> AllPaymentItemList;
    Context mContext;
    SessionManager sessionManager ;


    public PeriodePerformanceAdapter(Context context, List<ResponseEntityPeriode> paymentList){
        this.mContext = context;
        AllPaymentItemList = paymentList;
        this.sessionManager = new SessionManager(mContext);
    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_periode_gaji, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        final ResponseEntityPeriode responsePaymentMethod = AllPaymentItemList.get(position);

        String tahunID = responsePaymentMethod.getsPeriodeID();



        holder.tahun.setText(tahunID);


        holder.cv_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TAG_PERIODE = tahunID ;

                if (sessionManager.getKeyForm().equals("1")){
                    mContext.startActivity(new Intent(mContext,Performance_IT.class));
                }else{

                }

                String jenisForm = sessionManager.getKeyForm();

                // form it
                if (jenisForm.equals("1")){
                    mContext.startActivity(new Intent(mContext,Performance_IT.class));
                } //form perawat
                if (jenisForm.equals("2")){
                    mContext.startActivity(new Intent(mContext,Performance_Perawat.class));
                } //form bidan
                if (jenisForm.equals("3")){
                    mContext.startActivity(new Intent(mContext,Performance_Bidan.class));
                } //form general
                if (jenisForm.equals("4")){ //general
                    mContext.startActivity(new Intent(mContext,Performance.class));
                }



            }
        });




    }

    @Override
    public int getItemCount() {
        return AllPaymentItemList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{



        TextView tahun ;

        LinearLayout bgImage ;
        RelativeLayout btnItemRow;
        CardView cv_row ;

        AlertDialog.Builder dialog;


        public AdapterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            btnItemRow = itemView.findViewById(R.id.btn_itemRow);
            tahun= itemView.findViewById(R.id.periode);
            cv_row = itemView.findViewById(R.id.card_view);

            dialog = new AlertDialog.Builder(mContext);



        }
    }





}
