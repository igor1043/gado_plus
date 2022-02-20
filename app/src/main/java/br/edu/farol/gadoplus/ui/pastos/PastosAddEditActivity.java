package br.edu.farol.gadoplus.ui.pastos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import br.edu.farol.gadoplus.R;
import br.edu.farol.gadoplus.model.Propriedade;
import br.edu.farol.gadoplus.ui.animais.AnimaisFragment;
import br.edu.farol.gadoplus.ui.propriedade.PropriedadeViewModel;


public class PastosAddEditActivity extends AppCompatActivity {
    public static final String EXTRA_ID="br.edu.farol.gadoplus.ui.lotes.EXTRA_ID";
    public static final String EXTRA_NOME="br.edu.farol.gadoplus.ui.lotes.EXTRA_NOME";
    public static final String EXTRA_TIPO_PASTO="br.edu.farol.gadoplus.ui.lotes.EXTRA_TIPO_PASTO";
    public static final String EXTRA_DESCRICAO="br.edu.farol.gadoplus.ui.lotes.EXTRA_DESCRICAO";


    private EditText editTextNome;
    private EditText editTextTipoPasto ;
    private EditText editTextDescricao;


    private PropriedadeViewModel propriedadeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastos_add_edit);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        editTextNome       = findViewById(R.id.et_pasto_nome);
        editTextTipoPasto = findViewById(R.id.et_tipo_pasto);
        editTextDescricao  = findViewById(R.id.et_pasto_descricao);

        final ArrayAdapter<Propriedade> adapterPropriedadeSpinner = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item);
        adapterPropriedadeSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        propriedadeViewModel = ViewModelProviders.of(this).get(PropriedadeViewModel.class);
        propriedadeViewModel.getAll().observe(this, new Observer<List<Propriedade>>() {
            @Override
            public void onChanged(@Nullable List<Propriedade> propriedades) {
                adapterPropriedadeSpinner.clear();
                assert propriedades != null;
                adapterPropriedadeSpinner.addAll(propriedades);

            }
        });



        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Editar");
            editTextNome.setText(intent.getStringExtra(EXTRA_NOME));
            editTextTipoPasto.setText(intent.getStringExtra(EXTRA_TIPO_PASTO));
            editTextDescricao.setText(intent.getStringExtra(EXTRA_DESCRICAO));
        } else {
            setTitle("Cadastar");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_edit, menu);
        MenuItem itemDelete = menu.findItem(R.id.action_item_delete);
        itemDelete.setVisible(getIntent().hasExtra(EXTRA_ID));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_item_edit_save:
                onSave();
                return true;
            case R.id.action_item_delete:
                onDelete();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void onSave() {


        String nome = editTextNome.getText().toString();
        String tipoPasto = editTextTipoPasto.getText().toString();
        String descricao =  editTextDescricao.getText().toString();


        if (!nome.trim().isEmpty() && !tipoPasto.trim().isEmpty() && !descricao.trim().isEmpty()) {
            Intent data = new Intent();

            data.putExtra(EXTRA_NOME, nome);
            data.putExtra(EXTRA_TIPO_PASTO, tipoPasto);
            data.putExtra(EXTRA_DESCRICAO, descricao);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id != -1) {
                data.putExtra(EXTRA_ID, id);
            }

            setResult(RESULT_OK, data);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }
    }

    private void onDelete() {
        Intent data = new Intent();
        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
            setResult(AnimaisFragment.DELETE_REQUEST, data);
            finish();
        }
    }


}
