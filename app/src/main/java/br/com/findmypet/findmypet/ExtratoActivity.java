package br.com.findmypet.findmypet;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import Adapters.DetalheBonusAdapter;
import Adapters.DetalheCreditoAdapter;
import Models.Bonus;
import Models.Credito;
import Models.Extrato;
import Services.ServicesAPI;
import br.com.findmypet.estaciona.R;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ExtratoActivity extends AppCompatActivity {

    private ProgressDialog progress;
    long userId = 0;
    TextView tvCredito;
    TextView tvBonus;
    TextView tvValor;

    Button btUsarCredito;
    Button btUsarBonus;

    ListView lvCredito;
    ListView lvBonus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        Bundle extras = getIntent().getExtras();
        userId = extras.getLong("userId");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saldo(userId);

    }

    private void saldo(long codigo){

        progressBar("Carregando Saldos!");

        Call<Extrato> call = ServicesAPI.get().saldo(codigo);
        call.enqueue(new Callback<Extrato>() {
            @Override
            public void onResponse(Response<Extrato> response,
                                   Retrofit retrofit) {
                if (response.body() != null) {

                    List<Credito> credito = response.body().credito;
                    List<Bonus> bonus = response.body().bonus;

                    lvCredito = findViewById(R.id.lvCreditos);
                    lvBonus = findViewById(R.id.lvBonus);

                    DetalheCreditoAdapter adapterCredito = new DetalheCreditoAdapter(credito, ExtratoActivity.this);
                    lvCredito.setAdapter(adapterCredito);

                    DetalheBonusAdapter adapterBonus = new DetalheBonusAdapter(bonus, ExtratoActivity.this);
                    lvBonus.setAdapter(adapterBonus);

                    long totalCredito = 0;
                    long totalBonus = 0;
                    long valor = 0;

                    for(int i = 0; i < credito.size(); i++){
                        totalCredito += credito.get(i).getQuantidade();
                    }

                    totalCredito = (totalCredito / 60);

                    for(int i = 0; i < bonus.size(); i++){
                        totalBonus += bonus.get(i).getQuantidade();
                    }

                    totalBonus = (totalBonus / 60) ;

                    tvCredito = findViewById(R.id.tvCredito);
                    tvBonus = findViewById(R.id.tvBonus);
                    tvValor = findViewById(R.id.tvValor);

                    valor = (totalCredito * 5);

                    tvCredito.setText(Long.toString(totalCredito));
                    tvBonus.setText(Long.toString(totalBonus));
                    tvValor.setText("R$" + valor + ",00");

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

    private void progressBar(String mensagem) {
        progress = new ProgressDialog(ExtratoActivity.this);
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
