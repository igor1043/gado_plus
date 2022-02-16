package br.edu.farol.gadoplus.ui.pesagem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.Calendar;
import java.util.List;

import br.edu.farol.gadoplus.R;
import br.edu.farol.gadoplus.model.Lote;
import br.edu.farol.gadoplus.ui.animais.AnimaisAddEditActivity;
import br.edu.farol.gadoplus.ui.lotes.LotesViewModel;
import br.edu.farol.gadoplus.util.Util;


public class PesagemAddEditActivity extends AppCompatActivity {
    public static final String EXTRA_ID="br.edu.farol.gadoplus.ui.lotes.EXTRA_ID";
    public static final String EXTRA_LOTE_ID="br.edu.farol.gadoplus.ui.lotes.EXTRA_LOTE_ID";
    public static final String EXTRA_DATA="br.edu.farol.gadoplus.ui.lotes.EXTRA_DATA";
    public static final String EXTRA_DESCRICAO="br.edu.farol.gadoplus.ui.lotes.EXTRA_DESCRICAO";

    DatePickerDialog picker;

    private Spinner  spinnerLote;
    private EditText editTextData;
    private EditText editTextDescricao;

    int idLote = 0;

    private LotesViewModel lotesViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesagem_add_edit);

       // getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        editTextData      = findViewById(R.id.et_pesagem_data);
        editTextDescricao = findViewById(R.id.et_pesagem_descricao);

        editTextData.setInputType(InputType.TYPE_NULL);
        editTextData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(PesagemAddEditActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                //editTextData.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                editTextData.setText(getString(R.string.format_data,dayOfMonth, (monthOfYear+1),year));
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        spinnerLote = findViewById(R.id.spinner_pesagem_lote);
        final ArrayAdapter<Lote> adapterLoteSpinner = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item);
        adapterLoteSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLote.setAdapter(adapterLoteSpinner);
        lotesViewModel = ViewModelProviders.of(this).get(LotesViewModel.class);
        lotesViewModel.getAll().observe(this, new Observer<List<Lote>>() {
            @Override
            public void onChanged(@Nullable List<Lote> lotes) {
                adapterLoteSpinner.clear();
                assert lotes != null;
                adapterLoteSpinner.addAll(lotes);

            }
        });


        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Editar");
            editTextData.setText(intent.getStringExtra(EXTRA_DATA));
            editTextDescricao.setText(intent.getStringExtra(EXTRA_DESCRICAO));
            idLote= intent.getIntExtra(EXTRA_LOTE_ID, 0);
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

        Lote lote = (Lote) spinnerLote.getSelectedItem();
        int loteId = lote!=null ? lote.getId() : 0;

        String sData = editTextData.getText().toString();
        String descricao =  editTextDescricao.getText().toString();

        if (loteId > 0 && Util.dateValidation(sData) && !descricao.trim().isEmpty()) {
            Intent data = new Intent();

            data.putExtra(EXTRA_LOTE_ID, loteId);
            data.putExtra(EXTRA_DATA, sData.trim());
            data.putExtra(EXTRA_DESCRICAO, descricao.trim());

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
            setResult(PesagemFragment.DELETE_REQUEST, data);
            finish();
        }
    }


}
