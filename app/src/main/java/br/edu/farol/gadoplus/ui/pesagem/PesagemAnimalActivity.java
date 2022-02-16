package br.edu.farol.gadoplus.ui.pesagem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;
import javax.annotation.Nullable;
import br.edu.farol.gadoplus.R;
import br.edu.farol.gadoplus.adapter.PesagemAnimalAdapter;
import br.edu.farol.gadoplus.model.Animal;
import br.edu.farol.gadoplus.model.PesagemAnimal;
import br.edu.farol.gadoplus.ui.animais.AnimaisSearchActivity;
import br.edu.farol.gadoplus.ui.animais.AnimaisViewModel;


public class PesagemAnimalActivity extends AppCompatActivity {
    public static final int SEARCH_REQUEST = 4;
    public static final String EXTRA_PESAGEM_ID="br.edu.farol.gadoplus.ui.pesagem.EXTRA_PESAGEM_ID";

    private PesagemAnimalViewModel pesagemAnimalViewModel;
    private AnimaisViewModel animaisViewModel;

    private EditText editTextAnimal;
    private EditText editTextPeso;
    private Button buttonSelecionarAnimal;
    private Button buttonAdicionar;

    private int idAnimal=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesagem_animal);

        editTextAnimal = findViewById(R.id.et_pesagem_animal);
        editTextPeso = findViewById(R.id.et_pesagem_animal_peso);
        buttonSelecionarAnimal = findViewById(R.id.button_pesagem_animal_selecionar);
        buttonAdicionar = findViewById(R.id.button_pessagem_animal_adicionar);


        pesagemAnimalViewModel = ViewModelProviders.of(this).get(PesagemAnimalViewModel.class);
        animaisViewModel = ViewModelProviders.of(this).get(AnimaisViewModel.class);

        final RecyclerView recyclerView = findViewById(R.id.rv_pesagem_animal);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        final PesagemAnimalAdapter adapter = new PesagemAnimalAdapter();
        adapter.setApplication(getApplication());
        recyclerView.setAdapter(adapter);


        pesagemAnimalViewModel.getAll(getIntent().getIntExtra(EXTRA_PESAGEM_ID, 0)).observe(this, new Observer<List<PesagemAnimal>>() {
            @Override
            public void onChanged(@Nullable List<PesagemAnimal> pesagemAnimals) {
                adapter.setPesagemAnimal(pesagemAnimals);
            }
        });

        adapter.setOnClickListener(new PesagemAnimalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PesagemAnimal pesagemAnimal) {
                onDelete(pesagemAnimal);
            }
        });

        buttonSelecionarAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AnimaisSearchActivity.class);
                startActivityForResult(intent, SEARCH_REQUEST);
            }
        });

        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!editTextPeso.getText().toString().trim().isEmpty() && idAnimal > 0){

                    int id = getIntent().getIntExtra(EXTRA_PESAGEM_ID, -1);

                    PesagemAnimal pesagemAnimal = new PesagemAnimal();
                    pesagemAnimal.setPesagemId(id);
                    pesagemAnimal.setAnimalId(idAnimal);
                    pesagemAnimal.setPeso(Double.valueOf(editTextPeso.getText().toString()));

                    onSave(pesagemAnimal);

                    editTextPeso.setText("");
                    editTextAnimal.setText("...");

                }else{
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos antes de adicionar!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void onSave(PesagemAnimal pesagemAnimal){
        pesagemAnimalViewModel.insert(pesagemAnimal);
    }

    private void onDelete(final PesagemAnimal pesagemAnimal) {
        new AlertDialog.Builder(this)
                .setTitle("Excluir Registro")
                .setMessage("Tem certeza de que deseja excluir este registro?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pesagemAnimalViewModel.delete(pesagemAnimal);
                        Toast.makeText(getApplicationContext(),"Registro removido",Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //não faz nada
                    }
                })
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK || requestCode == SEARCH_REQUEST){
            assert data != null;
            idAnimal = data.getIntExtra(AnimaisSearchActivity.EXTRA_ID, 0);
            if (idAnimal > 0 ){
                Animal animal = animaisViewModel.getById(idAnimal);

                editTextAnimal.setText(animal.getNome());
            }else{
                editTextAnimal.setText("...");
            }
        }
    }

}
