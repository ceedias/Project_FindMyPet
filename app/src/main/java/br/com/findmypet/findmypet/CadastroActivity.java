package br.com.findmypet.findmypet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import Models.Usuario;
import Services.ServicesAPI;
import br.com.findmypet.estaciona.R;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class CadastroActivity extends AppCompatActivity {

    private ProgressDialog progress;

    AutoCompleteTextView nome;
    AutoCompleteTextView email;
    AutoCompleteTextView cpf;
    AutoCompleteTextView senha;
    AutoCompleteTextView confirmarSenha;
    AutoCompleteTextView cep;
    AutoCompleteTextView endereco;
    AutoCompleteTextView numero;

    Button adicionarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adicionarUsuario = (Button) findViewById(R.id.btadicionarUsuario);
        adicionarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate())
                    adicionarUsuario();
            }
        });
    }

    private boolean validate(){

        nome = findViewById(R.id.nome);
        email = findViewById(R.id.email);
        cpf = findViewById(R.id.cpf);
        senha = findViewById(R.id.senha);
        confirmarSenha = findViewById(R.id.confirmsenha);
        cep = findViewById(R.id.cep);
        endereco = findViewById(R.id.endereco);
        numero = findViewById(R.id.numero);

        if (!email.getText().toString().contains("@")){
            Toast.makeText(getApplicationContext(),"Email Inválido", Toast.LENGTH_LONG).show();
            return false;
        }

        if(!Utils.Utils.isValidCPF(cpf.getText().toString())){
            Toast.makeText(getApplicationContext(),"Email Inválido", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void adicionarUsuario(){

        progressBar("Salvando Usuário...");

        try {
            nome = findViewById(R.id.nome);
            email = findViewById(R.id.email);
            cpf = findViewById(R.id.cpf);
            senha = findViewById(R.id.senha);
            confirmarSenha = findViewById(R.id.confirmsenha);
            cep = findViewById(R.id.cep);
            endereco = findViewById(R.id.endereco);
            numero = findViewById(R.id.numero);

            Usuario usuario = new Usuario();

            usuario.setNome(nome.getText().toString().toUpperCase());
            usuario.setEmail(email.getText().toString());
            usuario.setCpf(Long.parseLong(cpf.getText().toString()));
            usuario.setSenha(senha.getText().toString());
            usuario.setConfirmarSenha(confirmarSenha.getText().toString());
            usuario.setCep(cep.getText().toString());
            usuario.setEndereco(endereco.getText().toString());
            usuario.setNumero(numero.getText().toString());

            final Call<Usuario> callAPI = ServicesAPI.get().CadastrarUsuario(usuario);

            callAPI.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Response<Usuario> response,
                                       Retrofit retrofit) {
                    progress.dismiss();
                    Intent i = new Intent(CadastroActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

                @Override
                public void onFailure(Throwable t) {
                    progress.dismiss();
                    Intent i = new Intent(CadastroActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }catch (Exception e){
            String erro =  e.getMessage();
            progress.dismiss();
        }

    }

    private void progressBar(String mensagem) {
        progress = new ProgressDialog(CadastroActivity.this);
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
