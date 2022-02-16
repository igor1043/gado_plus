package br.edu.farol.gadoplus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.edu.farol.gadoplus.R;
import br.edu.farol.gadoplus.model.Pesagem;


public class PesagemAdapter extends RecyclerView.Adapter<PesagemAdapter.PesagemViewHolder> {

    private List<Pesagem> pesagems;
    private OnItemClickListener listener;
    private OnItemLongClickListener longListener;

    @Override
    public PesagemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);


        return new PesagemViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(PesagemViewHolder viewHolder, int i) {
        final Pesagem pesagem = pesagems.get(i);
        if (pesagem != null){
            viewHolder.nome.setText(pesagem.getData());
            viewHolder.descricao.setText(pesagem.getDescricao());



            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(pesagem);
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longListener.onItemLongClick(pesagem);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (pesagems != null)
            return pesagems.size();
        else return 0;
    }

    public void setPesagem(List<Pesagem> pesagems) {
        this.pesagems = pesagems;
        notifyDataSetChanged();
    }

    class PesagemViewHolder extends RecyclerView.ViewHolder {

        private TextView nome, descricao, sLote;

        public PesagemViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.text_view_title);
            descricao = itemView.findViewById(R.id.text_view_description);
            sLote = itemView.findViewById(R.id.text_view_priority);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Pesagem pesagem);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(Pesagem pesagem);
    }


    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public void setOnLongClickListener(OnItemLongClickListener listener) {
        this.longListener = listener;
    }




}
