package br.edu.farol.gadoplus.ui.propriedade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.EditText;
import android.widget.Toast;

import br.edu.farol.gadoplus.R;

public class PropriedadeAddEditActivity extends AppCompatActivity {
    public static final String EXTRA_ID="br.edu.farol.gadoplus.ui.propriedades.EXTRA_ID";
    public static final String EXTRA_NOME="br.edu.farol.gadoplus.ui.propriedades.EXTRA_NOME";
    public static final String EXTRA_HECTARES="br.edu.farol.gadoplus.ui.propriedades.EXTRA_HECTARES";
    public static final String EXTRA_DESCRICAO="br.edu.farol.gadoplus.ui.propriedades.EXTRA_DESCRICAO";


    private EditText editTextNome;
    private EditText editTextHectares;
    private EditText editTextDescricao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propriedades_add_edit);


        editTextNome = findViewById(R.id.et_propriedade_nome);
        editTextHectares = findViewById(R.id.et_propriedade_hectare);
        editTextDescricao = findViewById(R.id.et_propriedade_descricao);

        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();



        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Editar");
            editTextNome.setText(intent.getStringExtra(EXTRA_NOME));
            editTextHectares.setText(String.valueOf(intent.getDoubleExtra(EXTRA_HECTARES,0)));
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
                onDeletePropriedades();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void onSave() {
        String nome = editTextNome.getText().toString();

        double hectare = editTextHectares.getText().toString().trim().isEmpty()? 0: Double.parseDouble(editTextHectares.getText().toString());

        String descricao = editTextDescricao.getText().toString();


        if (!nome.trim().isEmpty() && !descricao.trim().isEmpty()) {

            Intent data = new Intent();
            data.putExtra(EXTRA_NOME, nome);
            data.putExtra(EXTRA_HECTARES, hectare);
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

    private void onDeletePropriedades() {
        Intent data = new Intent();
        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
            setResult(PropriedadeFragment.DELETE_REQUEST, data);
            finish();
        }
    }
}
