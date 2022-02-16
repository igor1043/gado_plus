package br.edu.farol.gadoplus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.edu.farol.gadoplus.R;
import br.edu.farol.gadoplus.model.Raca;

public class RacaAdapter extends RecyclerView.Adapter<RacaAdapter.RacaViewHolder> {

    private List<Raca> racas;
    private OnItemClickListener listener;

    @Override
    public RacaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new RacaViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(RacaViewHolder viewHolder, int i) {

    //    viewHolder.nome.setText(racas.get(i).getNome());
     //   viewHolder.descricao.setText(racas.get(i).getDescricao());
     //   viewHolder.hectares.setText(String.valueOf(racas.get(i).getHectares()) + " hec");

    }

    @Override
    public int getItemCount() {
        if (racas != null)
            return racas.size();
        else return 0;
    }

    public void setRaca(List<Raca> racas) {
        this.racas = racas;
        notifyDataSetChanged();
    }

    class RacaViewHolder extends RecyclerView.ViewHolder {

        private TextView nome, descricao, hectares;

        public RacaViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.text_view_title);
            descricao = itemView.findViewById(R.id.text_view_description);
            hectares = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(racas.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Raca raca);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
