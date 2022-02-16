package br.edu.farol.gadoplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.farol.gadoplus.R
import br.edu.farol.gadoplus.adapter.RacaAdapter.RacaViewHolder
import br.edu.farol.gadoplus.model.Raca

class RacaAdapter : RecyclerView.Adapter<RacaViewHolder>() {
    private var racas: List<Raca>? = null
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RacaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return RacaViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RacaViewHolder, i: Int) {

        //    viewHolder.nome.setText(racas.get(i).getNome());
        //   viewHolder.descricao.setText(racas.get(i).getDescricao());
        //   viewHolder.hectares.setText(String.valueOf(racas.get(i).getHectares()) + " hec");
    }

    override fun getItemCount(): Int {
        return if (racas != null) racas!!.size else 0
    }

    fun setRaca(racas: List<Raca>?) {
        this.racas = racas
        notifyDataSetChanged()
    }

    inner class RacaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nome: TextView
        private val descricao: TextView
        private val hectares: TextView

        init {
            nome = itemView.findViewById(R.id.text_view_title)
            descricao = itemView.findViewById(R.id.text_view_description)
            hectares = itemView.findViewById(R.id.text_view_priority)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) listener!!.onItemClick(racas!![position])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(raca: Raca?)
    }

    fun setOnClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}