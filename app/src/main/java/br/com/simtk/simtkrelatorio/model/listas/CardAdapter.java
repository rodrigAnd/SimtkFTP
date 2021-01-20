package br.com.simtk.simtkrelatorio.model.listas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.simtk.simtkrelatorio.R;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            linearLayout = itemView.findViewById(R.id.linear_opcao);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_estoque, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
