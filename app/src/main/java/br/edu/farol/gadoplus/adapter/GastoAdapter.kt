package br.edu.farol.gadoplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.farol.gadoplus.R
import br.edu.farol.gadoplus.adapter.GastoAdapter.GastoViewHolder
import br.edu.farol.gadoplus.model.Gasto
import java.text.DecimalFormat

class GastoAdapter : RecyclerView.Adapter<GastoViewHolder>() {
    private var gastos: List<Gasto>? = null
    private var listener: OnItemClickListener? = null
    var df = DecimalFormat("#,###.00")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GastoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return GastoViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: GastoViewHolder, i: Int) {
        viewHolder.valor.text = "R$ " + df.format(gastos!![i].valor)
        viewHolder.descricao.text = gastos!![i].descricao
        viewHolder.data.text = gastos!![i].data
    }

    override fun getItemCount(): Int {
        return if (gastos != null) gastos!!.size else 0
    }

    fun setGasto(gastos: List<Gasto>?) {
        this.gastos = gastos
        notifyDataSetChanged()
    }

    inner class GastoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val valor: TextView
        val descricao: TextView
        val data: TextView

        init {
            valor = itemView.findViewById(R.id.text_view_title)
            descricao = itemView.findViewById(R.id.text_view_description)
            data = itemView.findViewById(R.id.text_view_priority)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) listener!!.onItemClick(gastos!![position])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(gasto: Gasto?)
    }

    fun setOnClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}