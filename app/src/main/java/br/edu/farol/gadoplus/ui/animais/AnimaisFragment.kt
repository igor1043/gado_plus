package br.edu.farol.gadoplus.ui.animais

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.farol.gadoplus.R
import br.edu.farol.gadoplus.adapter.AnimalAdapter
import br.edu.farol.gadoplus.model.Animal
import br.edu.farol.gadoplus.model.Raca
import br.edu.farol.gadoplus.ui.propriedade.PropriedadeAddEditActivity
import br.edu.farol.gadoplus.ui.propriedade.PropriedadeFragment
import br.edu.farol.gadoplus.ui.raca.RacaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AnimaisFragment : Fragment() {
    private var animaisViewModel: AnimaisViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        animaisViewModel = ViewModelProviders.of(this).get(AnimaisViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_animais, container, false)
        setHasOptionsMenu(true)
        val recyclerView: RecyclerView = root.findViewById(R.id.rv_animais)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        val adapter = AnimalAdapter()
        recyclerView.adapter = adapter
        val tvVazio = root.findViewById<TextView>(R.id.tv_animais_nenhum_registro)
        animaisViewModel!!.all.observe(this) { animals ->
            assert(animals != null)
            if (animals!!.size > 0) {
                tvVazio.visibility = View.GONE
                adapter.setAnimal(animals)
            } else {
                tvVazio.visibility = View.VISIBLE
            }
        }
        val buttonAdd: FloatingActionButton = root.findViewById(R.id.button_add_animais)
        buttonAdd.setOnClickListener {
            val intent = Intent(context, AnimaisAddEditActivity::class.java)
            startActivityForResult(intent, ADD_REQUEST)
        }
        adapter.setOnClickListener { animal ->
            val intent = Intent(context, AnimaisAddEditActivity::class.java)
            intent.putExtra(AnimaisAddEditActivity.EXTRA_ID, animal.id)
            intent.putExtra(AnimaisAddEditActivity.EXTRA_NOME, animal.nome)
            intent.putExtra(AnimaisAddEditActivity.EXTRA_LOTE_ID, animal.loteId)
            intent.putExtra(AnimaisAddEditActivity.EXTRA_SEXO, animal.sexo)
            intent.putExtra(AnimaisAddEditActivity.EXTRA_DT_ENTRADA, animal.dtEntrada)
            intent.putExtra(AnimaisAddEditActivity.EXTRA_DT_PRIMEIRA_PESAGEM, animal.dtPrimeiraPesagem)
            intent.putExtra(AnimaisAddEditActivity.EXTRA_PRIMEIRO_PESO, animal.primeiroPeso)
            intent.putExtra(AnimaisAddEditActivity.EXTRA_RACA_ID, animal.racaId)
            intent.putExtra(AnimaisAddEditActivity.EXTRA_PRECO_COMPRA, animal.precoCompra)
            intent.putExtra(AnimaisAddEditActivity.EXTRA_DT_NASCIMENTO, animal.dtNascimento)
            intent.putExtra(AnimaisAddEditActivity.EXTRA_DT_DESMAME, animal.dtDesmame)
            intent.putExtra(AnimaisAddEditActivity.EXTRA_OBSERVACOES, animal.observacoes)
            startActivityForResult(intent, EDIT_REQUEST)
        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && (requestCode == ADD_REQUEST || requestCode == EDIT_REQUEST)) {
            val animal = Animal()
            assert(data != null)
            animal.nome = data!!.getStringExtra(AnimaisAddEditActivity.EXTRA_NOME)
            animal.loteId = data.getIntExtra(AnimaisAddEditActivity.EXTRA_LOTE_ID, 0)
            animal.sexo = data.getStringExtra(AnimaisAddEditActivity.EXTRA_SEXO)
            animal.dtEntrada = data.getStringExtra(AnimaisAddEditActivity.EXTRA_DT_ENTRADA)
            animal.dtPrimeiraPesagem = data.getStringExtra(AnimaisAddEditActivity.EXTRA_DT_PRIMEIRA_PESAGEM)
            animal.primeiroPeso = data.getDoubleExtra(AnimaisAddEditActivity.EXTRA_PRIMEIRO_PESO, 0.0)
            animal.racaId = data.getIntExtra(AnimaisAddEditActivity.EXTRA_RACA_ID, 0)
            animal.precoCompra = data.getDoubleExtra(AnimaisAddEditActivity.EXTRA_PRECO_COMPRA, 0.0)
            animal.dtNascimento = data.getStringExtra(AnimaisAddEditActivity.EXTRA_DT_NASCIMENTO)
            animal.dtDesmame = data.getStringExtra(AnimaisAddEditActivity.EXTRA_DT_DESMAME)
            animal.observacoes = data.getStringExtra(AnimaisAddEditActivity.EXTRA_OBSERVACOES)
            when (requestCode) {
                ADD_REQUEST -> {
                    animaisViewModel!!.insert(animal)
                    Toast.makeText(context, "Registro salvo", Toast.LENGTH_SHORT).show()
                }
                EDIT_REQUEST -> {
                    val id = data.getIntExtra(PropriedadeAddEditActivity.EXTRA_ID, -1)
                    if (id == -1) {
                        Toast.makeText(context, "Não é possível salvar!", Toast.LENGTH_SHORT).show()
                        return
                    }
                    animal.id = id
                    animaisViewModel!!.update(animal)
                    Toast.makeText(context, "Registro atualizado", Toast.LENGTH_SHORT).show()
                }
            }
        } else if (resultCode == PropriedadeFragment.DELETE_REQUEST) {
            assert(data != null)
            val id = data!!.getIntExtra(PropriedadeAddEditActivity.EXTRA_ID, -1)
            if (id == -1) {
                Toast.makeText(context, "Não é possivel deletar!", Toast.LENGTH_SHORT).show()
                return
            }
            val animal = Animal()
            animal.id = id
            animaisViewModel!!.delete(animal)
            Toast.makeText(context, "Registro deletado", Toast.LENGTH_SHORT).show()
        }
    }

    fun dialogNewRaca() {
        val builder = AlertDialog.Builder(context!!)
        val input = EditText(context)
        input.setSingleLine()
        val container = FrameLayout(activity!!)
        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.leftMargin = resources.getDimensionPixelSize(R.dimen.dialog_margin)
        input.layoutParams = params
        container.addView(input)
        builder.setTitle("Cadastrar Raça")
        builder.setMessage("Qual o nome da raça que você deseja cadastrar?")
        builder.setView(container)
        builder.setPositiveButton("OK") { dialog, which ->
            if (input.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(context, "Escreva alguma Descrição", Toast.LENGTH_LONG).show()
                dialogNewRaca()
            } else {
                newRaca(input.text.toString().trim { it <= ' ' })
            }
        }
        builder.setNegativeButton("Cancelar") { dialog, which -> dialog.cancel() }
        builder.show()
    }

    fun newRaca(nome: String?) {
        val racaViewModel: RacaViewModel
        racaViewModel = ViewModelProviders.of(this).get(RacaViewModel::class.java)
        val raca = nome?.let { Raca(it) }
        racaViewModel.insert(raca)
        Toast.makeText(context, "Cadastrado com Sucesso!", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val item = menu.findItem(R.id.action_new_raca)
        item.isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_new_raca) {
            dialogNewRaca()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val ADD_REQUEST = 1
        const val EDIT_REQUEST = 2
        const val DELETE_REQUEST = 3
    }
}