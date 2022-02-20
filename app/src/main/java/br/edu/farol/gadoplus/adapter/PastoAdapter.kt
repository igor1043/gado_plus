package br.edu.farol.gadoplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.farol.gadoplus.R
import br.edu.farol.gadoplus.model.Pasto

class PastoAdapter : RecyclerView.Adapter<PastoAdapter.PastoViewHolder>() {
    private var pastos: List<Pasto>? = null
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_pastos, parent, false)
        return PastoViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: PastoViewHolder, i: Int) {
        viewHolder.nome.text = pastos!![i].nome
        viewHolder.tipoPasto.text = pastos!![i].tipoPasto
    }

    override fun getItemCount(): Int {
        return if (pastos != null) pastos!!.size else 0
    }

    fun setPasto(pastos: List<Pasto>?) {
        this.pastos = pastos
        notifyDataSetChanged()
    }

    inner class PastoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView
        val tipoPasto: TextView

        init {
            nome = itemView.findViewById(R.id.text_view_title_pasto)
            tipoPasto = itemView.findViewById(R.id.text_view_tipo_pasto)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) listener!!.onItemClick(pastos!![position])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(pasto: Pasto?)
    }

    fun setOnClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}