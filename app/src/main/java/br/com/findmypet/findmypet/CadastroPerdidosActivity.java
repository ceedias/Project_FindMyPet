package br.com.findmypet.findmypet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import Models.Perdido;
import Services.ServicesAPI;
import br.com.findmypet.estaciona.R;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class CadastroPerdidosActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ProgressDialog progress;

    AutoCompleteTextView raca;
    AutoCompleteTextView cor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_perdidos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton btTirarFoto = findViewById(R.id.btTirarFoto);
        btTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ImageView mImageView = findViewById(R.id.ibt_foto);

            mImageView.setImageBitmap(imageBitmap);
        }
    }

    private boolean validate(){

        return  true;
    }

    private void adicionarPerdidos(){

        progressBar("Salvando Cachorro Perdido...");

        Perdido cartao = new Perdido();

        final Call<Perdido> callAPI = ServicesAPI.get().CadastrarCartao(cartao);

        callAPI.enqueue(new Callback<Perdido>() {
            @Override
            public void onResponse(Response<Perdido> response,
                                   Retrofit retrofit) {
                progress.dismiss();
                Intent i = new Intent(CadastroPerdidosActivity.this, ListaCartaoActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Throwable t) {
                progress.dismiss();
                Intent i = new Intent(CadastroPerdidosActivity.this, ListaCartaoActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void progressBar(String mensagem) {
        progress = new ProgressDialog(CadastroPerdidosActivity.this);
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
