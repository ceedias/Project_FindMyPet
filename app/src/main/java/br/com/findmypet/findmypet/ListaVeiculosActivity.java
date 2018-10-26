package br.com.findmypet.findmypet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import java.util.List;
import Adapters.DetalheVeiculoAdapter;
import Models.Veiculo;
import Services.ServicesAPI;
import br.com.findmypet.estaciona.R;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ListaVeiculosActivity extends AppCompatActivity {

    private ProgressDialog progress;
    ListView listaVeiculos;
    long userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_veiculos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListaVeiculosActivity.this, AdicionarVeiculoActivity.class);
                i.putExtra("userId", userId);
                startActivity(i);
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        userId = extras.getLong("userId");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaVeiculos = findViewById(R.id.lvVeiculos);
        listarVeiculos(userId);
    }

    private void listarVeiculos(long codigo){

        progressBar();

        Call<List<Veiculo>> call = ServicesAPI.get().buscaVeiculo(codigo);
        call.enqueue(new Callback<List<Veiculo>>() {
            @Override
            public void onResponse(Response<List<Veiculo>> response,
                                   Retrofit retrofit) {
                if (response.body() != null) {
                    DetalheVeiculoAdapter adapter = new DetalheVeiculoAdapter(response, ListaVeiculosActivity.this);
                    listaVeiculos.setAdapter(adapter);
                } else {
                }
                progress.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                progress.dismiss();

            }
        });
    }

    private void progressBar() {
        progress = new ProgressDialog(ListaVeiculosActivity.this);
        progress.setMessage("Aguarde carregando...");
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
