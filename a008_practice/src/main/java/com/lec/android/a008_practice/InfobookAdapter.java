package com.lec.android.a008_practice;

import android.icu.text.IDNA;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InfobookAdapter<infobook> extends RecyclerView.Adapter<InfobookAdapter.ViewHolder> {

    List<Infobook> items=new ArrayList<Infobook>();

    static InfobookAdapter adapter;

    public InfobookAdapter() { this.adapter = this; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvAge,tvName,tvAddress;
        ImageButton btnDel;

        public ViewHolder(@NonNull View itemView) {//item 레이아웃 View 객체가 전달됨.
            super(itemView);


            tvAge=itemView.findViewById(R.id.tvAge);
            tvName=itemView.findViewById(R.id.tvName);
            tvAddress=itemView.findViewById(R.id.tvAddress);
            btnDel=itemView.findViewById(R.id.btnDel);

            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.removeItem(getAdapterPosition()); //데이터삭제, 이단계까지 하면 죽음
                    //데이터 변경내역(추가, 삭제, 수정)이 adapter 에 반영되어야 정상적으로 동작함!!!
                    adapter.notifyDataSetChanged();
                }
            });


        }//end 생성자
        public void setItem(Infobook item){
            tvAge.setText(item.getAge());
            tvName.setText(item.getName());
            tvAge.setText(item.getAddress());
        }
    }//end viewholder
    public void removeItem(int position){ items.remove(position); }
}//end adapter
