package br.edu.farol.gadoplus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.farol.gadoplus.R;
import br.edu.farol.gadoplus.model.Animal;


public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    private List<Animal> animals;
    private OnItemClickListener listener;

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new AnimalViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(AnimalViewHolder viewHolder, int i) {

        viewHolder.nome.setText(animals.get(i).getNome());
        viewHolder.observacoes.setText(animals.get(i).getObservacoes());
        viewHolder.dtEntrada.setText("Data Ent.: " + animals.get(i).getDtEntrada());

    }

    @Override
    public int getItemCount() {
        if (animals != null)
            return animals.size();
        else return 0;
    }

    public void setAnimal(List<Animal> animals) {
        this.animals = animals;
        notifyDataSetChanged();
    }

    class AnimalViewHolder extends RecyclerView.ViewHolder {

        private TextView nome, observacoes, dtEntrada;

        public AnimalViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.text_view_title);
            observacoes = itemView.findViewById(R.id.text_view_description);
            dtEntrada = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(animals.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Animal animal);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
