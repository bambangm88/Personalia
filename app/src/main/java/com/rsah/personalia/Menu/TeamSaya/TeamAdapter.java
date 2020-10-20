package com.rsah.personalia.Menu.TeamSaya;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.rsah.personalia.Model.ResponseEntityTeam;
import com.rsah.personalia.R;
import com.rsah.personalia.sessionManager.SessionManager;

import java.util.List;

import butterknife.ButterKnife;

import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_BIDANG;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_DEPTID_TEAM;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_EMP_ID_TEAM;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_IMAGE_STRING;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_JABATAN;

import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_NAMA_;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_UNIT;


public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.AdapterHolder>{

    List<ResponseEntityTeam> AllPaymentItemList;
    Context mContext;

    SessionManager sessionManager ;


    public TeamAdapter(Context context, List<ResponseEntityTeam> paymentList){
        this.mContext = context;
        AllPaymentItemList = paymentList;
        sessionManager = new SessionManager(mContext);

    }

    @Override
    public AdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        return new AdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterHolder holder, int position) {
        final ResponseEntityTeam responsePaymentMethod = AllPaymentItemList.get(position);

        String firstName = responsePaymentMethod.getsFisrtName();
        String sex = responsePaymentMethod.getSex();
        String jabatan = responsePaymentMethod.getJabatan();
        String bidang = responsePaymentMethod.getBidang();
        String unit = responsePaymentMethod.getUnit();
        String image_ = responsePaymentMethod.getFoto();
        String empID_ = responsePaymentMethod.getsEmpID();
        String dept_ = responsePaymentMethod.getsDeptID();

        holder.nama.setText(""+firstName);
        holder.jabatan.setText(": "+jabatan);
        holder.bidang.setText(": "+bidang);
        holder.unit.setText(": "+unit);





        if (image_ == null || image_.equals("") ) {
            if (sex.equals("M")){
                holder.imageRoom.setBackgroundResource(R.mipmap.profile);
            }else{
                holder.imageRoom.setBackgroundResource(R.mipmap.female);
            }

        }else{
                 //setImage


                String url = mContext.getString(R.string.hostImage) + image_;

                Glide.with(mContext).load(url).into(holder.imageRoom);


        }

        //hak akses selain kepala unit tidak bisa diklik
        if (sessionManager.getJabatan().equals("KEPALA BIDANG")){

            holder.btnItemRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //kepala unit tidak bisa di nilai
                    if (jabatan.equals("KEPALA UNIT")) {

                        TAG_NAMA_ = firstName ;
                        TAG_BIDANG = bidang ;
                        TAG_JABATAN = jabatan ;
                        TAG_UNIT = unit ;
                        TAG_EMP_ID_TEAM = empID_ ;
                        TAG_IMAGE_STRING = image_;
                        TAG_DEPTID_TEAM = dept_;

                        sessionManager.createTeamSession(firstName,bidang,jabatan,unit,empID_,image_,dept_);

                        mContext.startActivity(new Intent(mContext,PreviewTeam.class));

                    }else  {

                        //tidak bida dinilai

                    }


                }
            });


        }else if (sessionManager.getJabatan().equals("KEPALA UNIT")){   //hak akses selain kepala unit tidak bisa diklik

                holder.btnItemRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //kepala unit tidak bisa di nilai
                        if (jabatan.equals("KEPALA BIDANG")) {

                            //tidak bisa dinilai

                        }else if (!jabatan.equals("KEPALA UNIT")) {

                            TAG_NAMA_ = firstName ;
                            TAG_BIDANG = bidang ;
                            TAG_JABATAN = jabatan ;
                            TAG_UNIT = unit ;
                            TAG_EMP_ID_TEAM = empID_ ;
                            TAG_IMAGE_STRING = image_;
                            TAG_DEPTID_TEAM = dept_;

                            sessionManager.createTeamSession(firstName,bidang,jabatan,unit,empID_,image_,dept_);

                            mContext.startActivity(new Intent(mContext,PreviewTeam.class));

                        }

                    }
                });


        }



        if (jabatan.equals("KEPALA UNIT") || jabatan.equals("KEPALA BIDANG") ){
            holder.jabatan.setTypeface(null, Typeface.BOLD);
        }


    }

    @Override
    public int getItemCount() {
        return AllPaymentItemList.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{



        TextView bidang , unit;
        TextView jabatan;
        TextView nama;
        ImageView imageRoom ;
        LinearLayout bgImage ;
        RelativeLayout btnItemRow;
        CardView cv_row ;

        AlertDialog.Builder dialog;


        public AdapterHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

//            bgImage = itemView.findViewById(R.id.bgImage);
            nama = itemView.findViewById(R.id.tvNama);
            jabatan = itemView.findViewById(R.id.tvJabatan);
            bidang = itemView.findViewById(R.id.tvBidang);
            unit = itemView.findViewById(R.id.tvUnit);

            imageRoom = itemView.findViewById(R.id.ivTextDrawable);
            btnItemRow = itemView.findViewById(R.id.btn_itemRow);
            cv_row = itemView.findViewById(R.id.card_view);

            dialog = new AlertDialog.Builder(mContext);



        }
    }






}
