package br.com.findmypet.findmypet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import Models.Veiculo;
import Services.ServicesAPI;
import br.com.findmypet.estaciona.R;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AdicionarVeiculoActivity extends AppCompatActivity {

    private ProgressDialog progress;
    private EditText nomeVeiculo;
    private EditText placa;
    private Button adicionarVeiculo;
    long userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_veiculo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        userId = bundle.getLong("userId");

        placa = (AutoCompleteTextView) findViewById(R.id.placaVeiculo);
        placa.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        adicionarVeiculo = (Button) findViewById(R.id.btAdicionarVeiculo);
        adicionarVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarVeiculo();
            }
        });
    }

    private void adicionarVeiculo(){

        progressBar("Salvando Veiculo...");

        nomeVeiculo = (AutoCompleteTextView) findViewById(R.id.nomeVeiculo);
        placa = (AutoCompleteTextView) findViewById(R.id.placaVeiculo);

        Veiculo veiculo = new Veiculo();

        veiculo.setNomeVeiculo(nomeVeiculo.getText().toString().toUpperCase() + "|" + userId);
        veiculo.setplaca(placa.getText().toString().toUpperCase());

        final Call<Veiculo> callAPI = ServicesAPI.get().CadastrarVeiculo(veiculo);

        callAPI.enqueue(new Callback<Veiculo>() {
            @Override
            public void onResponse(Response<Veiculo> response,
                                   Retrofit retrofit) {
                progress.dismiss();
                Intent i = new Intent(AdicionarVeiculoActivity.this, ListaVeiculosActivity.class);
                i.putExtra("userId", userId);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Throwable t) {
                progress.dismiss();
                Intent i = new Intent(AdicionarVeiculoActivity.this, ListaVeiculosActivity.class);
                i.putExtra("userId", userId);
                startActivity(i);
                finish();
            }
        });

    }

    private void progressBar(String mensagem) {
        progress = new ProgressDialog(AdicionarVeiculoActivity.this);
        progress.setMessage(mensagem);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCanceledOnTouchOutside(false);
        progress.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
