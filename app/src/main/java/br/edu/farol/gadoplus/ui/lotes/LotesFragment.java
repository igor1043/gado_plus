package br.edu.farol.gadoplus.ui.lotes;

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
import br.edu.farol.gadoplus.adapter.LoteAdapter;
import br.edu.farol.gadoplus.model.Lote;

public class LotesFragment extends Fragment {
    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;
    public static final int DELETE_REQUEST = 3;

    private LotesViewModel lotesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lotesViewModel =
                ViewModelProviders.of(this).get(LotesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lotes, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.rv_lotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final LoteAdapter adapter = new LoteAdapter();
        recyclerView.setAdapter(adapter);

        final TextView tvVazio = root.findViewById(R.id.tv_lotes_nenhum_registro);


        lotesViewModel.getAll().observe(this, new Observer<List<Lote>>() {
            @Override
            public void onChanged(@Nullable List<Lote> lotes) {
                if (lotes.size()>0){
                    tvVazio.setVisibility(View.GONE);
                    adapter.setLote(lotes);
                }else{
                    tvVazio.setVisibility(View.VISIBLE);
                }

            }
        });

        FloatingActionButton buttonAdd = root.findViewById(R.id.button_add_lotes);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LotesAddEditActivity.class);
                startActivityForResult(intent, ADD_REQUEST);
            }
        });

        adapter.setOnClickListener(new LoteAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Lote lote) {
                Intent intent = new Intent(getContext(), LotesAddEditActivity.class);
                intent.putExtra(LotesAddEditActivity.EXTRA_ID, lote.getId());
                intent.putExtra(LotesAddEditActivity.EXTRA_NOME, lote.getNome());
                intent.putExtra(LotesAddEditActivity.EXTRA_PROPRIEDADE_ID, lote.getPropriedadeId());
                intent.putExtra(LotesAddEditActivity.EXTRA_DESCRICAO, lote.getDescricao());

                startActivityForResult(intent, EDIT_REQUEST);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((resultCode == Activity.RESULT_OK)&&(requestCode == ADD_REQUEST || requestCode == EDIT_REQUEST)){
            Lote lote = new Lote();

            lote.setNome(data.getStringExtra(LotesAddEditActivity.EXTRA_NOME));
            lote.setPropriedadeId(data.getIntExtra(LotesAddEditActivity.EXTRA_PROPRIEDADE_ID,0));
            lote.setDescricao(data.getStringExtra(LotesAddEditActivity.EXTRA_DESCRICAO));


            switch (requestCode){
                case ADD_REQUEST:
                    lotesViewModel.insert(lote);
                    Toast.makeText(getContext(), "Registro salvo", Toast.LENGTH_SHORT).show();
                    break;
                case EDIT_REQUEST:
                    int id = data.getIntExtra(LotesAddEditActivity.EXTRA_ID, -1);
                    if (id == -1) {
                        Toast.makeText(getContext(), "Não é possível salvar!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    lote.setId(id);
                    lotesViewModel.update(lote);
                    Toast.makeText(getContext(), "Registro atualizado", Toast.LENGTH_SHORT).show();
                    break;
            }
        }else if (resultCode == DELETE_REQUEST) {

            int id = data.getIntExtra(LotesAddEditActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Não é possivel deletar!", Toast.LENGTH_SHORT).show();
                return;
            }

            Lote lote = new Lote();
            lote.setId(id);
            lotesViewModel.delete(lote);

            Toast.makeText(getContext(), "Registro deletado", Toast.LENGTH_SHORT).show();
        }

    }

}