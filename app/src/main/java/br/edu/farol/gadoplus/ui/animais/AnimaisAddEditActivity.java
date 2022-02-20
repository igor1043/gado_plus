package br.edu.farol.gadoplus.ui.animais;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;
import br.edu.farol.gadoplus.R;
import br.edu.farol.gadoplus.model.Lote;
import br.edu.farol.gadoplus.model.Raca;
import br.edu.farol.gadoplus.ui.lotes.LotesViewModel;
import br.edu.farol.gadoplus.ui.raca.RacaViewModel;
import br.edu.farol.gadoplus.util.Util;


public class AnimaisAddEditActivity extends AppCompatActivity {
    public static final String EXTRA_ID="br.edu.farol.gadoplus.ui.propriedades.EXTRA_ID";
    public static final String EXTRA_NOME="br.edu.farol.gadoplus.ui.propriedades.EXTRA_NOME";
    public static final String EXTRA_LOTE_ID="br.edu.farol.gadoplus.ui.propriedades.EXTRA_LOTE_ID";
    public static final String EXTRA_SEXO="br.edu.farol.gadoplus.ui.propriedades.EXTRA_SEXO";
    public static final String EXTRA_DT_ENTRADA="br.edu.farol.gadoplus.ui.propriedades.EXTRA_DT_ENTRADA";
    public static final String EXTRA_DT_PRIMEIRA_PESAGEM="br.edu.farol.gadoplus.ui.propriedades.EXTRA_DT_PRIMEIRA_PESAGEM";
    public static final String EXTRA_PRIMEIRO_PESO="br.edu.farol.gadoplus.ui.propriedades.EXTRA_PRIMEIRO_PESO";
    public static final String EXTRA_RACA_ID="br.edu.farol.gadoplus.ui.propriedades.EXTRA_RACA_ID";
    public static final String EXTRA_PRECO_COMPRA="br.edu.farol.gadoplus.ui.propriedades.EXTRA_PRECO_COMPRA";
    public static final String EXTRA_DT_NASCIMENTO="br.edu.farol.gadoplus.ui.propriedades.EXTRA_DT_NASCIMENTO";
    public static final String EXTRA_DT_DESMAME="br.edu.farol.gadoplus.ui.propriedades.EXTRA_DT_DESMAME";
    public static final String EXTRA_OBSERVACOES="br.edu.farol.gadoplus.ui.propriedades.EXTRA_OBSERVACOES";

    DatePickerDialog picker;

    private EditText    editTextNome;
    private Spinner     spinnerLote;
    private RadioButton radioButtonMacho;
    private RadioButton radioButtonFemea;
    private EditText    editTextDtEntrada;
    private EditText    editTextDtPrimeiraPesagem;
    private EditText    editTextPrimeiroPeso;
    private Spinner     spinnerRaca;
    private EditText    editTextPrecoCompra;
    private EditText    editTextDtNascimento;
    private EditText    editTextDtDesmame;
    private EditText    editTextObservacoes;

    private TextInputLayout textInputDtEntrada;

    int idLote = 0;
    int idRaca = 0;

    private LotesViewModel lotesViewModel;
    private RacaViewModel racaViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animais_add_edit);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        editTextNome              = findViewById(R.id.et_animais_nome);
        radioButtonMacho          = findViewById(R.id.rb_animais_macho);
        radioButtonFemea          = findViewById(R.id.rb_animais_femea);
        editTextDtEntrada         = findViewById(R.id.et_animais_dt_entrada);
        editTextDtPrimeiraPesagem = findViewById(R.id.et_animais_dt_primeira_pesagem);
        editTextPrimeiroPeso      = findViewById(R.id.et_animais_primeiro_peso);
        editTextPrecoCompra       = findViewById(R.id.et_animais_preco_compra);
        editTextDtNascimento      = findViewById(R.id.et_animais_dt_nascimento);
        editTextDtDesmame         = findViewById(R.id.et_animais_dt_desmame);
        editTextObservacoes       = findViewById(R.id.et_animais_observacoes);


        textInputDtEntrada       = findViewById(R.id.tv_animais_dt_entrada);



        editTextDtEntrada.setInputType(InputType.TYPE_NULL);
        editTextDtEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AnimaisAddEditActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                //editTextDtEntrada.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                editTextDtEntrada.setText(getString(R.string.format_data,dayOfMonth, (monthOfYear+1),year));
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        editTextDtPrimeiraPesagem.setInputType(InputType.TYPE_NULL);
        editTextDtPrimeiraPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AnimaisAddEditActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                //editTextDtPrimeiraPesagem.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                editTextDtPrimeiraPesagem.setText(getString(R.string.format_data,dayOfMonth, (monthOfYear+1),year));
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        editTextDtNascimento.setInputType(InputType.TYPE_NULL);
        editTextDtNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AnimaisAddEditActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                //editTextDtNascimento.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                editTextDtNascimento.setText(getString(R.string.format_data,dayOfMonth, (monthOfYear+1),year));
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        editTextDtDesmame.setInputType(InputType.TYPE_NULL);

        editTextDtDesmame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeyboard();
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AnimaisAddEditActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                //editTextDtDesmame.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                editTextDtDesmame.setText(getString(R.string.format_data,dayOfMonth, (monthOfYear+1),year));
                            }
                        }, year, month, day);
                picker.show();
            }
        });





        spinnerLote = findViewById(R.id.spinner_animais_lote);
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

        spinnerRaca = findViewById(R.id.spinner_animais_raca);
        final ArrayAdapter<Raca> adapterRacaSpinner = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item);
        adapterRacaSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRaca.setAdapter(adapterRacaSpinner);
        racaViewModel = ViewModelProviders.of(this).get(RacaViewModel.class);
        racaViewModel.getAll().observe(this, new Observer<List<Raca>>() {
            @Override
            public void onChanged(@Nullable List<Raca> racas) {
                adapterRacaSpinner.clear();
                assert racas != null;
                adapterRacaSpinner.addAll(racas);

            }
        });




        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Editar");
            editTextNome.setText(intent.getStringExtra(EXTRA_NOME));
            editTextDtEntrada.setText(intent.getStringExtra(EXTRA_DT_ENTRADA));
            editTextDtPrimeiraPesagem.setText(intent.getStringExtra(EXTRA_DT_PRIMEIRA_PESAGEM));
            editTextPrimeiroPeso.setText(String.valueOf(intent.getDoubleExtra(EXTRA_PRIMEIRO_PESO,0)));
            editTextPrecoCompra.setText(String.valueOf(intent.getDoubleExtra(EXTRA_PRECO_COMPRA,0)));
            editTextDtNascimento.setText(intent.getStringExtra(EXTRA_DT_NASCIMENTO));
            editTextDtDesmame.setText(intent.getStringExtra(EXTRA_DT_DESMAME));
            editTextObservacoes.setText(intent.getStringExtra(EXTRA_OBSERVACOES));

            String sexo = intent.getStringExtra(EXTRA_SEXO);

            assert sexo != null;
            if (sexo.contains("Macho")){
                radioButtonMacho.setChecked(true);
            }else{
                radioButtonFemea.setChecked(true);
            }

            //spinnerLote
            //spinnerRaca
            idLote= intent.getIntExtra(EXTRA_LOTE_ID, 0);
            idRaca = intent.getIntExtra(EXTRA_RACA_ID, 0);


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
        Raca raca = (Raca) spinnerRaca.getSelectedItem();

        int loteId = lote!=null? lote.getId(): 0;
        int racaId = raca!=null? raca.getId(): 0;



        String nome = editTextNome.getText().toString();
        String sexo = radioButtonMacho.isChecked() ? "Macho": "Fêmea";
        String dtEntrada = editTextDtEntrada.getText().toString();
        String dtPrimeiraPesagem =  editTextDtPrimeiraPesagem.getText().toString();
        double primeiroPeso =  editTextPrimeiroPeso.getText().toString().trim().isEmpty()? 0 : Double.parseDouble(editTextPrimeiroPeso.getText().toString());
        double precoCompra =  editTextPrecoCompra.getText().toString().trim().isEmpty()? 0 : Double.parseDouble(editTextPrecoCompra.getText().toString());
        String dtNascimento =  editTextDtNascimento.getText().toString();
        String dtDesmame  =  editTextDtDesmame.getText().toString();
        String observacoes =  editTextObservacoes.getText().toString();




        if (!nome.trim().isEmpty() && !sexo.trim().isEmpty() && loteId > 0 && racaId > 0 &&
                Util.dateValidation(dtEntrada) && Util.dateValidation(dtPrimeiraPesagem) &&
                Util.dateValidation(dtNascimento) && Util.dateValidation(dtDesmame)){

            Intent data = new Intent();

            data.putExtra(EXTRA_NOME, nome);
            data.putExtra(EXTRA_LOTE_ID, loteId);
            data.putExtra(EXTRA_SEXO, sexo);
            data.putExtra(EXTRA_DT_ENTRADA, dtEntrada);
            data.putExtra(EXTRA_DT_PRIMEIRA_PESAGEM, dtPrimeiraPesagem);
            data.putExtra(EXTRA_PRIMEIRO_PESO, primeiroPeso);
            data.putExtra(EXTRA_RACA_ID, racaId);
            data.putExtra(EXTRA_PRECO_COMPRA, precoCompra);
            data.putExtra(EXTRA_DT_NASCIMENTO, dtNascimento);
            data.putExtra(EXTRA_DT_DESMAME, dtDesmame);
            data.putExtra(EXTRA_OBSERVACOES, observacoes);

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

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}