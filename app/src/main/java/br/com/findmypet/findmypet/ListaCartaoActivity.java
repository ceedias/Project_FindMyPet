package br.com.findmypet.findmypet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

import Adapters.CustomAdapter;
import Adapters.DetalheCartaoAdapter;
import Models.Perdido;
import Services.ServicesAPI;
import br.com.findmypet.estaciona.R;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ListaCartaoActivity extends AppCompatActivity {

    private ProgressDialog progress;
    ListView listaDeCartoes;
    long userId = 0;
    int tipo;

    ListView simpleList;
    String countryList[] = {"cachorro 1", "cachorro 2", "cachorro 3"};
    int flags[] = {R.mipmap.ic_cachorro_lista1, R.mipmap.ic_cachorro_lista2, R.mipmap.ic_cachorro_lista3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cartao);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        simpleList = (ListView) findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), countryList, flags);
        simpleList.setAdapter(customAdapter);

    }

    private void listarCartoes(long codigo){

        progressBar();

        Call<List<Perdido>> call = ServicesAPI.get().buscaCartao(codigo);
        call.enqueue(new Callback<List<Perdido>>() {
            @Override
            public void onResponse(Response<List<Perdido>> response,
                                   Retrofit retrofit) {
                if (response.body() != null) {

                    DetalheCartaoAdapter adapter = new DetalheCartaoAdapter(response, ListaCartaoActivity.this);
                    listaDeCartoes.setAdapter(adapter);

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
        progress = new ProgressDialog(ListaCartaoActivity.this);
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
