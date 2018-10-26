package br.com.findmypet.findmypet;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Date;
import Models.Compra;
import Services.ServicesAPI;
import Utils.GPSTracker;
import br.com.findmypet.estaciona.R;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class PagamentosActivity extends AppCompatActivity {

    long userId = 0;
    private ProgressDialog progress;

    TextView quantidade;
    Button btpagar;
    TextView valor;
    long cartao;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamentos);

        gps = new GPSTracker(PagamentosActivity.this);

        quantidade = (TextView) findViewById(R.id.tvQtd);
        valor =  (TextView) findViewById(R.id.tvValor);

        ImageButton menos = (ImageButton)findViewById(R.id.imgMenos);
        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantidade.getText().toString().equals("0")){
                    quantidade.setText("0");
                    valor.setText("R$" + (0 * 5) + ",00");
                }else{
                    int value = Integer.parseInt(quantidade.getText().toString());
                    value --;
                    valor.setText("R$" + (value * 5) + ",00");
                    quantidade.setText(Integer.toString(value));
                }
            }
        });

        ImageButton mais = findViewById(R.id.imgMais);
        mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantidade.getText().toString().equals("0")){
                    quantidade.setText("1");
                    valor.setText("R$" + (1 * 5) + ",00");
                }else{
                    int value = Integer.parseInt(quantidade.getText().toString());
                    value ++;
                    valor.setText("R$" + (value * 5) + ",00");
                    quantidade.setText(Integer.toString(value));
                }

            }
        });

        Bundle extras = getIntent().getExtras();
        userId = extras.getLong("userId");
        cartao = extras.getLong("cartaoid");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btpagar = findViewById(R.id.btPagar);
        btpagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog(valor.getText().toString());
            }
        });
    }

    private AlertDialog alerta;

    private void Dialog(String total){


        if(gps.canGetLocation()) {

            final double latitude = gps.getLatitude();
            final double longitude = gps.getLongitude();

            //Cria o gerador do AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //define o titulo
            builder.setTitle("Estaciona App");
            //define a mensagem
            builder.setMessage("deseja confirmar a compra de" + total + "créditos?");
            //define um botão como positivo
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Comprar(userId, Double.toString(latitude), Double.toString(longitude));
                }
            });
            //define um botão como negativo.
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {


                }
            });
            //cria o AlertDialog
            alerta = builder.create();
            //Exibe
            alerta.show();

        } else {
            // Can't get location.
            // GPS or network is not enabled.
            // Ask user to enable GPS/network in settings.
            gps.showSettingsAlert();
        }

    }

    private void Comprar(long codigo, String latitude, String longitudde){

        progressBar("Efetuando pagamento...");

        Compra compra = new Compra();

        compra.setCartaoId(cartao);
        compra.setUsuarioId(codigo);
        compra.setData(new Date());
        compra.setlatitude(latitude);
        compra.setlongitude(longitudde);


        long value = Long.parseLong( valor.getText().toString().replace("R$","").replace(",00",""));
        compra.setValor((value * 100));

        final Call<Compra> callAPI = ServicesAPI.get().CadastrarCompra(compra);

        callAPI.enqueue(new Callback<Compra>() {
            @Override
            public void onResponse(Response<Compra> response,
                                   Retrofit retrofit) {
                progress.dismiss();
                Intent i = new Intent(PagamentosActivity.this, ExtratoActivity.class);
                i.putExtra("userId", userId);
                startActivity(i);
                finish();
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

    }

    private void progressBar(String mensagem) {
        progress = new ProgressDialog(PagamentosActivity.this);
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
