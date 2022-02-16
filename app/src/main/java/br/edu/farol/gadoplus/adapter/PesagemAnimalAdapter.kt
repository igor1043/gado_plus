package br.edu.farol.gadoplus.adapter

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.farol.gadoplus.R
import br.edu.farol.gadoplus.adapter.PesagemAnimalAdapter.PesagemAnimalViewHolder
import br.edu.farol.gadoplus.model.PesagemAnimal
import br.edu.farol.gadoplus.storage.database.repository.AnimalRepository

class PesagemAnimalAdapter : RecyclerView.Adapter<PesagemAnimalViewHolder>() {
    private var pesagemAnimals: List<PesagemAnimal>? = null
    private var listener: OnItemClickListener? = null
    private var animalRepository: AnimalRepository? = null
    private var application: Application? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesagemAnimalViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        animalRepository = AnimalRepository(application)
        return PesagemAnimalViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: PesagemAnimalViewHolder, i: Int) {
        viewHolder.nome.setText("${pesagemAnimals!![i].peso}   Kg")
        try {
            val animal = animalRepository!!.getById(pesagemAnimals!![i].animalId)
            viewHolder.descricao.text = "Animal: " + animal.nome
        } catch (e: Exception) {
            println(e)
        }
    }

    override fun getItemCount(): Int {
        return if (pesagemAnimals != null) pesagemAnimals!!.size else 0
    }

    fun setPesagemAnimal(pesagemAnimals: List<PesagemAnimal>?) {
        this.pesagemAnimals = pesagemAnimals
        notifyDataSetChanged()
    }

    fun setApplication(application: Application?) {
        this.application = application
    }

    inner class PesagemAnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView
        val descricao: TextView
        private val hectares: TextView

        init {
            nome = itemView.findViewById(R.id.text_view_title)
            descricao = itemView.findViewById(R.id.text_view_description)
            hectares = itemView.findViewById(R.id.text_view_priority)
            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) listener!!.onItemClick(pesagemAnimals!![position])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(pesagemAnimal: PesagemAnimal?)
    }

    fun setOnClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}