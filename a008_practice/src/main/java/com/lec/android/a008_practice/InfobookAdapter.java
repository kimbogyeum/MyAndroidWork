package com.lec.android.a008_practice;

import android.icu.text.IDNA;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InfobookAdapter<infobook> extends RecyclerView.Adapter<InfobookAdapter.ViewHolder> {

    List<Infobook> items = new ArrayList<Infobook>();

    static InfobookAdapter adapter;

    public InfobookAdapter() {
        this.adapter = this;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // 주어진 ViewGroup 으로부터 LayoutInflater 추출
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        // 준비된 레이아웃(XML) 으로부터 View 를 만들어 ViewGroup 에 붙이고
        // 그렇게 만들어진 View 를 리턴한다
        View itemView = inf.inflate(R.layout.item, parent, false);

        // 위에서 마들어진 새로운 View 를 ViewHolder 에 담아 리턴
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Infobook item=items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
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

        // Phonebook 데이터를 받아서 멤버변수 세팅
        public void setItem(Infobook item){
            tvName.setText(item.getName());
            tvAge.setText(item.getAge());
            tvAddress.setText(item.getAddress());
        }


    }//end viewholder
    public void addItem(Infobook item) {  items.add(item); }
    public void addItem(int position, Infobook item) {   items.add(position, item);}
    public void setItems(ArrayList<Infobook> items) {   this.items = items;}
    public Infobook getItem(int position) {   return items.get(position);}
    public void setItem(int position, Infobook item) {   items.set(position, item); }
    public void removeItem(int position){ items.remove(position); }}//end adapter
