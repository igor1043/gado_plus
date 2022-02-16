package br.edu.farol.gadoplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.farol.gadoplus.R
import br.edu.farol.gadoplus.adapter.PesagemAdapter.PesagemViewHolder
import br.edu.farol.gadoplus.model.Pesagem

class PesagemAdapter : RecyclerView.Adapter<PesagemViewHolder>() {
    private var pesagems: List<Pesagem>? = null
    private var listener: OnItemClickListener? = null
    private var longListener: OnItemLongClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesagemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return PesagemViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: PesagemViewHolder, i: Int) {
        val pesagem = pesagems!![i]
        if (pesagem != null) {
            viewHolder.nome.text = pesagem.data
            viewHolder.descricao.text = pesagem.descricao
            viewHolder.itemView.setOnClickListener { listener!!.onItemClick(pesagem) }
            viewHolder.itemView.setOnLongClickListener {
                longListener!!.onItemLongClick(pesagem)
                false
            }
        }
    }

    override fun getItemCount(): Int {
        return if (pesagems != null) pesagems!!.size else 0
    }

    fun setPesagem(pesagems: List<Pesagem>?) {
        this.pesagems = pesagems
        notifyDataSetChanged()
    }

    inner class PesagemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView
        val descricao: TextView
        private val sLote: TextView

        init {
            nome = itemView.findViewById(R.id.text_view_title)
            descricao = itemView.findViewById(R.id.text_view_description)
            sLote = itemView.findViewById(R.id.text_view_priority)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(pesagem: Pesagem?)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(pesagem: Pesagem?)
    }

    fun setOnClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    fun setOnLongClickListener(listener: OnItemLongClickListener?) {
        longListener = listener
    }
}