package br.edu.farol.gadoplus.ui.pastos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.edu.farol.gadoplus.R;
import br.edu.farol.gadoplus.adapter.PastoAdapter;
import br.edu.farol.gadoplus.model.Pasto;



public class PastoFragment extends Fragment {
    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;
    public static final int DELETE_REQUEST = 3;

    private PastosViewModel pastoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pastoViewModel =
                ViewModelProviders.of(this).get(PastosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pastos, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.rv_pastos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final PastoAdapter adapter = new PastoAdapter();
        recyclerView.setAdapter(adapter);

        final TextView tvVazio = root.findViewById(R.id.tv_pastos_nenhum_registro);


        pastoViewModel.getAll().observe(this, new Observer<List<Pasto>>() {
            @Override
            public void onChanged(@Nullable List<Pasto> pastos) {
                if (pastos.size()>0){
                    tvVazio.setVisibility(View.GONE);
                    adapter.setPasto(pastos);
                }else{
                    tvVazio.setVisibility(View.VISIBLE);
                }

            }
        });

        FloatingActionButton buttonAdd = root.findViewById(R.id.button_add_pastos);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PastosAddEditActivity.class);
                startActivityForResult(intent, ADD_REQUEST);
            }
        });

        adapter.setOnClickListener(new PastoAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Pasto pasto) {
                Intent intent = new Intent(getContext(), PastosAddEditActivity.class);
                intent.putExtra(PastosAddEditActivity.EXTRA_ID, pasto.getId());
                intent.putExtra(PastosAddEditActivity.EXTRA_NOME, pasto.getNome());
                intent.putExtra(PastosAddEditActivity.EXTRA_TIPO_PASTO, pasto.getTipoPasto());
                intent.putExtra(PastosAddEditActivity.EXTRA_DESCRICAO, pasto.getDescricao());

                startActivityForResult(intent, EDIT_REQUEST);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((resultCode == Activity.RESULT_OK)&&(requestCode == ADD_REQUEST || requestCode == EDIT_REQUEST)){
            Pasto pasto = new Pasto();

            pasto.setNome(data.getStringExtra(PastosAddEditActivity.EXTRA_NOME));
            pasto.setTipoPasto(data.getStringExtra(PastosAddEditActivity.EXTRA_TIPO_PASTO));
            pasto.setDescricao(data.getStringExtra(PastosAddEditActivity.EXTRA_DESCRICAO));


            switch (requestCode){
                case ADD_REQUEST:
                    pastoViewModel.insert(pasto);
                    Toast.makeText(getContext(), "Registro salvo", Toast.LENGTH_SHORT).show();
                    break;
                case EDIT_REQUEST:
                    int id = data.getIntExtra(PastosAddEditActivity.EXTRA_ID, -1);
                    if (id == -1) {
                        Toast.makeText(getContext(), "Não é possível salvar!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    pasto.setId(id);
                    pastoViewModel.update(pasto);
                    Toast.makeText(getContext(), "Registro atualizado", Toast.LENGTH_SHORT).show();
                    break;
            }
        }else if (resultCode == DELETE_REQUEST) {

            int id = data.getIntExtra(PastosAddEditActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Não é possivel deletar!", Toast.LENGTH_SHORT).show();
                return;
            }

            Pasto pasto = new Pasto();
            pasto.setId(id);
            pastoViewModel.delete(pasto);

            Toast.makeText(getContext(), "Registro deletado", Toast.LENGTH_SHORT).show();
        }

    }

}