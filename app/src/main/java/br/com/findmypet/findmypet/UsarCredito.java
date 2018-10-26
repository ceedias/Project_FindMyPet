package br.com.findmypet.findmypet;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import Models.Bonus;
import Models.Credito;
import Services.ServicesAPI;
import br.com.findmypet.estaciona.R;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class UsarCredito extends AppCompatActivity {

    private ProgressDialog progress;
    long userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usar_credito);
    }

    private void liberarCredito(long codigo, long value){

        progressBar("=Habilitando Crédito...");

        final Call<Credito> callAPI = ServicesAPI.get().liberarCredito(codigo,value);

        callAPI.enqueue(new Callback<Credito>() {
            @Override
            public void onResponse(Response<Credito> response,
                                   Retrofit retrofit) {
                progress.dismiss();

                if (response.body() != null){
                }else{
                }
            }

            @Override
            public void onFailure(Throwable t) {
                progress.dismiss();
            }
        });
    }

    private void liberarBonus(long codigo, long value){

        progressBar("=Habilitando Bonus...");

        final Call<Bonus> callAPI = ServicesAPI.get().liberarBonus(codigo,value);

        callAPI.enqueue(new Callback<Bonus>() {
            @Override
            public void onResponse(Response<Bonus> response,
                                   Retrofit retrofit) {
                progress.dismiss();

                if (response.body() != null){
                }else{
                }
            }

            @Override
            public void onFailure(Throwable t) {
                progress.dismiss();
            }
        });
    }

    private void progressBar(String mensagem) {
        progress = new ProgressDialog(UsarCredito.this);
        progress.setMessage(mensagem);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCanceledOnTouchOutside(false);
        progress.show();
    }

}
