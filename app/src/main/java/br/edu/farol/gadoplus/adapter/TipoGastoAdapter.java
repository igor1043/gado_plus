package br.edu.farol.gadoplus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.farol.gadoplus.R;
import br.edu.farol.gadoplus.model.TipoGasto;

public class TipoGastoAdapter extends RecyclerView.Adapter<TipoGastoAdapter.TipoGastoViewHolder> {

    private List<TipoGasto> tipoGastos;
    private OnItemClickListener listener;

    @Override
    public TipoGastoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new TipoGastoViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(TipoGastoViewHolder viewHolder, int i) {

     //   viewHolder.nome.setText(tipoGastos.get(i).getNome());
     //   viewHolder.descricao.setText(tipoGastos.get(i).getDescricao());
     //   viewHolder.hectares.setText(String.valueOf(tipoGastos.get(i).getHectares()) + " hec");

    }

    @Override
    public int getItemCount() {
        if (tipoGastos != null)
            return tipoGastos.size();
        else return 0;
    }

    public void setTipoGasto(List<TipoGasto> tipoGastos) {
        this.tipoGastos = tipoGastos;
        notifyDataSetChanged();
    }

    class TipoGastoViewHolder extends RecyclerView.ViewHolder {

        private TextView nome, descricao, hectares;

        public TipoGastoViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.text_view_title);
            descricao = itemView.findViewById(R.id.text_view_description);
            hectares = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(tipoGastos.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TipoGasto tipoGasto);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
