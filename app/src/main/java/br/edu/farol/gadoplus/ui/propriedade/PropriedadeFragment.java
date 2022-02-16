package br.edu.farol.gadoplus.ui.propriedade;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

import br.edu.farol.gadoplus.R;
import br.edu.farol.gadoplus.adapter.PropriedadeAdapter;
import br.edu.farol.gadoplus.model.Propriedade;


public class PropriedadeFragment extends Fragment {
    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;
    public static final int DELETE_REQUEST = 3;


    private PropriedadeViewModel propriedadeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_propriedades, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.rv_propriedades);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final PropriedadeAdapter adapter = new PropriedadeAdapter();
        recyclerView.setAdapter(adapter);

        final TextView tvVazio = root.findViewById(R.id.tv_propriedade_nenhum_registro);

        propriedadeViewModel = ViewModelProviders.of(this).get(PropriedadeViewModel.class);
        propriedadeViewModel.getAll().observe(this, new Observer<List<Propriedade>>() {
            @Override
            public void onChanged(@Nullable List<Propriedade> propriedades) {
                if (propriedades.size()>0){
                    tvVazio.setVisibility(View.GONE);
                    adapter.setPropriedade(propriedades);
                }else{
                    tvVazio.setVisibility(View.VISIBLE);
                }

            }
        });

        FloatingActionButton buttonAdd = root.findViewById(R.id.button_add_propriedade);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PropriedadeAddEditActivity.class);
                startActivityForResult(intent, ADD_REQUEST);
            }
        });

        adapter.setOnClickListener(new PropriedadeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Propriedade propriedade) {
                Intent intent = new Intent(getContext(), PropriedadeAddEditActivity.class);
                intent.putExtra(PropriedadeAddEditActivity.EXTRA_ID, propriedade.getId());
                intent.putExtra(PropriedadeAddEditActivity.EXTRA_NOME, propriedade.getNome());
                intent.putExtra(PropriedadeAddEditActivity.EXTRA_HECTARES, propriedade.getHectares());
                intent.putExtra(PropriedadeAddEditActivity.EXTRA_DESCRICAO, propriedade.getDescricao());
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQUEST && resultCode == Activity.RESULT_OK) {
            String nome = data.getStringExtra(PropriedadeAddEditActivity.EXTRA_NOME);
            double hectares = data.getDoubleExtra(PropriedadeAddEditActivity.EXTRA_HECTARES, 0);
            String descricao = data.getStringExtra(PropriedadeAddEditActivity.EXTRA_DESCRICAO);

            Propriedade propriedade = new Propriedade(nome, hectares, descricao);
            propriedadeViewModel.insert(propriedade);

            Toast.makeText(getContext(), "Registro salvo", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_REQUEST && resultCode == Activity.RESULT_OK) {
            int id = data.getIntExtra(PropriedadeAddEditActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Não é possível salvar!", Toast.LENGTH_SHORT).show();
                return;
            }

            String nome = data.getStringExtra(PropriedadeAddEditActivity.EXTRA_NOME);
            double hectares = data.getDoubleExtra(PropriedadeAddEditActivity.EXTRA_HECTARES, 0);
            String descricao = data.getStringExtra(PropriedadeAddEditActivity.EXTRA_DESCRICAO);

            Propriedade propriedade = new Propriedade(nome, hectares, descricao);
            propriedade.setId(id);
            propriedadeViewModel.update(propriedade);

            Toast.makeText(getContext(), "Registro atualizado", Toast.LENGTH_SHORT).show();
        } else if (resultCode == PropriedadeFragment.DELETE_REQUEST) {

            int id = data.getIntExtra(PropriedadeAddEditActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Não é possivel deletar!", Toast.LENGTH_SHORT).show();
                return;
            }

            Propriedade propriedade = new Propriedade();
            propriedade.setId(id);
            propriedadeViewModel.delete(propriedade);

            Toast.makeText(getContext(), "Registro deletado", Toast.LENGTH_SHORT).show();
        }
    }

}