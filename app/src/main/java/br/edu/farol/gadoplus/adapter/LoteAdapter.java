package br.edu.farol.gadoplus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.edu.farol.gadoplus.R;
import br.edu.farol.gadoplus.model.Lote;

public class LoteAdapter extends RecyclerView.Adapter<LoteAdapter.LoteViewHolder> {

    private List<Lote> lotes;
    private OnItemClickListener listener;


    @Override
    public LoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new LoteViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(LoteViewHolder viewHolder, int i) {

        viewHolder.nome.setText(lotes.get(i).getNome());
        viewHolder.descricao.setText(lotes.get(i).getDescricao());

    }

    @Override
    public int getItemCount() {
        if (lotes != null)
            return lotes.size();
        else return 0;
    }

    public void setLote(List<Lote> lotes) {
        this.lotes = lotes;
        notifyDataSetChanged();
    }

    class LoteViewHolder extends RecyclerView.ViewHolder {

        private TextView nome, descricao, sPropriedade;

        public LoteViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.text_view_title);
            descricao = itemView.findViewById(R.id.text_view_description);
            sPropriedade = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(lotes.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Lote lote);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    
}
