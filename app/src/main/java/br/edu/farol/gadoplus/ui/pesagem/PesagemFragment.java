package br.edu.farol.gadoplus.ui.pesagem;

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
import br.edu.farol.gadoplus.adapter.PesagemAdapter;
import br.edu.farol.gadoplus.adapter.PesagemAnimalAdapter;
import br.edu.farol.gadoplus.model.Pesagem;

public class PesagemFragment extends Fragment {
    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;
    public static final int DELETE_REQUEST = 3;

    private PesagemViewModel pesagemViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pesagemViewModel =
                ViewModelProviders.of(this).get(PesagemViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pesagem, container, false);

        final RecyclerView recyclerView = root.findViewById(R.id.rv_pesagem);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final PesagemAdapter adapter = new PesagemAdapter();
        recyclerView.setAdapter(adapter);

        final TextView tvVazio = root.findViewById(R.id.tv_pesagem_nenhum_registro);


        pesagemViewModel.getAll().observe(this, new Observer<List<Pesagem>>() {
            @Override
            public void onChanged(@Nullable List<Pesagem> pesagems) {
                if (pesagems.size()>0){
                    tvVazio.setVisibility(View.GONE);
                    adapter.setPesagem(pesagems);
                }else{
                    tvVazio.setVisibility(View.VISIBLE);
                }

            }
        });

        FloatingActionButton buttonAdd = root.findViewById(R.id.button_add_pesagem);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PesagemAddEditActivity.class);
                startActivityForResult(intent, ADD_REQUEST);
            }
        });

        adapter.setOnClickListener(new PesagemAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Pesagem pesagem) {
                Intent intent = new Intent(getContext(), PesagemAddEditActivity.class);

                intent.putExtra(PesagemAddEditActivity.EXTRA_ID, pesagem.getId());
                intent.putExtra(PesagemAddEditActivity.EXTRA_LOTE_ID, pesagem.getLoteId());
                intent.putExtra(PesagemAddEditActivity.EXTRA_DATA, pesagem.getData());
                intent.putExtra(PesagemAddEditActivity.EXTRA_DESCRICAO, pesagem.getDescricao());
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });

        adapter.setOnLongClickListener(new PesagemAdapter.OnItemLongClickListener(){
            @Override
            public void onItemLongClick(Pesagem pesagem) {
                Intent intent = new Intent(getContext(), PesagemAnimalActivity.class);
                intent.putExtra(PesagemAnimalActivity.EXTRA_PESAGEM_ID, pesagem.getId());
                startActivity(intent);
            }
        });


        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((resultCode == Activity.RESULT_OK)&&(requestCode == ADD_REQUEST || requestCode == EDIT_REQUEST)){
            Pesagem pesagem = new Pesagem();


            pesagem.setLoteId(data.getIntExtra(PesagemAddEditActivity.EXTRA_LOTE_ID,0));
            pesagem.setData(data.getStringExtra(PesagemAddEditActivity.EXTRA_DATA));
            pesagem.setDescricao(data.getStringExtra(PesagemAddEditActivity.EXTRA_DESCRICAO));


            switch (requestCode){
                case ADD_REQUEST:
                    pesagemViewModel.insert(pesagem);
                    Toast.makeText(getContext(), "Registro salvo", Toast.LENGTH_SHORT).show();
                    break;
                case EDIT_REQUEST:
                    int id = data.getIntExtra(PesagemAddEditActivity.EXTRA_ID, -1);
                    if (id == -1) {
                        Toast.makeText(getContext(), "Não é possível salvar!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    pesagem.setId(id);
                    pesagemViewModel.update(pesagem);
                    Toast.makeText(getContext(), "Registro atualizado", Toast.LENGTH_SHORT).show();
                    break;
            }
        }else if (resultCode == PesagemFragment.DELETE_REQUEST) {

            int id = data.getIntExtra(PesagemAddEditActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Não é possivel deletar!", Toast.LENGTH_SHORT).show();
                return;
            }

            Pesagem pesagem = new Pesagem();
            pesagem.setId(id);
            pesagemViewModel.delete(pesagem);

            Toast.makeText(getContext(), "Registro deletado", Toast.LENGTH_SHORT).show();
        }

    }
}