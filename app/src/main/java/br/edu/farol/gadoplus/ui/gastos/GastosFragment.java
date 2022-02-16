package br.edu.farol.gadoplus.ui.gastos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import br.edu.farol.gadoplus.adapter.GastoAdapter;
import br.edu.farol.gadoplus.model.Gasto;
import br.edu.farol.gadoplus.model.TipoGasto;

public class GastosFragment extends Fragment {
    public static final int ADD_REQUEST = 1;
    public static final int EDIT_REQUEST = 2;
    public static final int DELETE_REQUEST = 3;

    private GastosViewModel gastosViewModel;

    private String m_Text = "";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gastosViewModel = ViewModelProviders.of(this).get(GastosViewModel.class);

        View root = inflater.inflate(R.layout.fragment_gastos, container, false);

        setHasOptionsMenu(true);

        final RecyclerView recyclerView = root.findViewById(R.id.rv_gastos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final GastoAdapter adapter = new GastoAdapter();
        recyclerView.setAdapter(adapter);

        final TextView tvVazio = root.findViewById(R.id.tv_gastos_nenhum_registro);


        gastosViewModel.getAll().observe(this, new Observer<List<Gasto>>() {
            @Override
            public void onChanged(@Nullable List<Gasto> gastos) {
                assert gastos != null;
                if (gastos.size()>0){
                    tvVazio.setVisibility(View.GONE);
                    adapter.setGasto(gastos);
                }else{
                    tvVazio.setVisibility(View.VISIBLE);
                }

            }
        });

        FloatingActionButton buttonAdd = root.findViewById(R.id.button_add_gastos);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), GastosAddEditActivity.class);
                startActivityForResult(intent, ADD_REQUEST);
            }
        });

        adapter.setOnClickListener(new GastoAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Gasto gasto) {
                Intent intent = new Intent(getContext(), GastosAddEditActivity.class);
                intent.putExtra(GastosAddEditActivity.EXTRA_ID, gasto.getId());
                intent.putExtra(GastosAddEditActivity.EXTRA_TIPO_GASTO_ID, gasto.getTipoGastoId());
                intent.putExtra(GastosAddEditActivity.EXTRA_ANIMAL_ID, gasto.getAnimalId());
                intent.putExtra(GastosAddEditActivity.EXTRA_DATA, gasto.getData());
                intent.putExtra(GastosAddEditActivity.EXTRA_VALOR, gasto.getValor());
                intent.putExtra(GastosAddEditActivity.EXTRA_DESCRICAO, gasto.getDescricao());

                startActivityForResult(intent, EDIT_REQUEST);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((resultCode == Activity.RESULT_OK)&&(requestCode == ADD_REQUEST || requestCode == EDIT_REQUEST)){
            Gasto gasto = new Gasto();

            gasto.setTipoGastoId(data.getIntExtra(GastosAddEditActivity.EXTRA_TIPO_GASTO_ID,0));
            gasto.setAnimalId(data.getIntExtra(GastosAddEditActivity.EXTRA_ANIMAL_ID,0));
            gasto.setData(data.getStringExtra(GastosAddEditActivity.EXTRA_DATA));
            gasto.setValor(data.getDoubleExtra(GastosAddEditActivity.EXTRA_VALOR,0));
            gasto.setDescricao(data.getStringExtra(GastosAddEditActivity.EXTRA_DESCRICAO));


            switch (requestCode){
                case ADD_REQUEST:
                    gastosViewModel.insert(gasto);
                    Toast.makeText(getContext(), "Registro salvo", Toast.LENGTH_SHORT).show();
                    break;
                case EDIT_REQUEST:
                    int id = data.getIntExtra(GastosAddEditActivity.EXTRA_ID, -1);
                    if (id == -1) {
                        Toast.makeText(getContext(), "Não é possível salvar!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    gasto.setId(id);
                    gastosViewModel.update(gasto);
                    Toast.makeText(getContext(), "Registro atualizado", Toast.LENGTH_SHORT).show();
                    break;
            }
        }else if (resultCode == DELETE_REQUEST) {

            int id = data.getIntExtra(GastosAddEditActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Não é possivel deletar!", Toast.LENGTH_SHORT).show();
                return;
            }

            Gasto gasto = new Gasto();
            gasto.setId(id);
            gastosViewModel.delete(gasto);

            Toast.makeText(getContext(), "Registro deletado", Toast.LENGTH_SHORT).show();
        }

    }

    public void dialogNewTipoGasto(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        final EditText input = new EditText(getContext());

        input.setSingleLine();
        FrameLayout container = new FrameLayout(getActivity());
        FrameLayout.LayoutParams params = new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.dialog_margin);
        input.setLayoutParams(params);
        container.addView(input);

        builder.setTitle("Cadastrar Tipo de Gasto");
        builder.setMessage("Qual o nome do tipo de gasto que vocêdeseja cadastrar?");
        builder.setView(container);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(),"Escreva alguma Descrição", Toast.LENGTH_LONG).show();
                    dialogNewTipoGasto();
                }else{
                    newTipoGasto(input.getText().toString().trim());
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void newTipoGasto(String nome){
        TipoGastosViewModel tipoGastosViewModel;
        tipoGastosViewModel = ViewModelProviders.of(this).get(TipoGastosViewModel.class);

        TipoGasto tipoGasto = new TipoGasto(nome);
        tipoGastosViewModel.insert(tipoGasto);
        Toast.makeText(getContext(), "Cadastrado com Sucesso!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item = menu.findItem(R.id.action_new_tipo_gasto);
        item.setVisible(true);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_new_tipo_gasto:
                dialogNewTipoGasto();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}