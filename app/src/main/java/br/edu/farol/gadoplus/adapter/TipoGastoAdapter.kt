package br.edu.farol.gadoplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.farol.gadoplus.R
import br.edu.farol.gadoplus.adapter.TipoGastoAdapter.TipoGastoViewHolder
import br.edu.farol.gadoplus.model.TipoGasto

class TipoGastoAdapter : RecyclerView.Adapter<TipoGastoViewHolder>() {
    private var tipoGastos: List<TipoGasto>? = null
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipoGastoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return TipoGastoViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: TipoGastoViewHolder, i: Int) {

        //   viewHolder.nome.setText(tipoGastos.get(i).getNome());
        //   viewHolder.descricao.setText(tipoGastos.get(i).getDescricao());
        //   viewHolder.hectares.setText(String.valueOf(tipoGastos.get(i).getHectares()) + " hec");
    }

    override fun getItemCount(): Int {
        return if (tipoGastos != null) tipoGastos!!.size else 0
    }

    fun setTipoGasto(tipoGastos: List<TipoGasto>?) {
        this.tipoGastos = tipoGastos
        notifyDataSetChanged()
    }

    inner class TipoGastoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nome: TextView
        private val descricao: TextView
        private val hectares: TextView

        init {
            nome = itemView.findViewById(R.id.text_view_title)
            descricao = itemView.findViewById(R.id.text_view_description)
            hectares = itemView.findViewById(R.id.text_view_priority)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) listener!!.onItemClick(tipoGastos!![position])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(tipoGasto: TipoGasto?)
    }

    fun setOnClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}