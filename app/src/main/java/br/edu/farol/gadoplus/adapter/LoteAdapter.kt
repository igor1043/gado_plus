package br.edu.farol.gadoplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.farol.gadoplus.R
import br.edu.farol.gadoplus.adapter.LoteAdapter.LoteViewHolder
import br.edu.farol.gadoplus.model.Lote

class LoteAdapter : RecyclerView.Adapter<LoteViewHolder>() {
    private var lotes: List<Lote>? = null
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return LoteViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: LoteViewHolder, i: Int) {
        viewHolder.nome.text = lotes!![i].nome
        viewHolder.descricao.text = lotes!![i].descricao
    }

    override fun getItemCount(): Int {
        return if (lotes != null) lotes!!.size else 0
    }

    fun setLote(lotes: List<Lote>?) {
        this.lotes = lotes
        notifyDataSetChanged()
    }

    inner class LoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView
        val descricao: TextView
        private val sPropriedade: TextView

        init {
            nome = itemView.findViewById(R.id.text_view_title)
            descricao = itemView.findViewById(R.id.text_view_description)
            sPropriedade = itemView.findViewById(R.id.text_view_priority)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) listener!!.onItemClick(lotes!![position])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(lote: Lote?)
    }

    fun setOnClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}