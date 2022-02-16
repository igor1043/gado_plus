package br.edu.farol.gadoplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.farol.gadoplus.R
import br.edu.farol.gadoplus.adapter.PropriedadeAdapter.PropriedadeViewHolder
import br.edu.farol.gadoplus.model.*

class PropriedadeAdapter : RecyclerView.Adapter<PropriedadeViewHolder>() {
    private var propriedades: List<Propriedade>? = null
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropriedadeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return PropriedadeViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: PropriedadeViewHolder, i: Int) {
        viewHolder.nome.text = propriedades!![i].nome
        viewHolder.descricao.text = propriedades!![i].descricao
        viewHolder.hectares.setText("${propriedades!![i].hectares}  hec")
    }

    override fun getItemCount(): Int {
        return if (propriedades != null) propriedades!!.size else 0
    }

    fun setPropriedade(propriedades: List<Propriedade>?) {
        this.propriedades = propriedades
        notifyDataSetChanged()
    }

    inner class PropriedadeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView
        val descricao: TextView
        val hectares: TextView

        init {
            nome = itemView.findViewById(R.id.text_view_title)
            descricao = itemView.findViewById(R.id.text_view_description)
            hectares = itemView.findViewById(R.id.text_view_priority)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) listener!!.onItemClick(propriedades!![position])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(propriedade: Propriedade?)
    }

    fun setOnClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}