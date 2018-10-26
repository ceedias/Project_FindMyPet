package Interfaces;

import java.util.List;

import Models.Bonus;
import Models.Perdido;
import Models.Compra;
import Models.Credito;
import Models.Extrato;
import Models.Usuario;
import Models.Veiculo;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

public interface ServicesREST {

    @Headers({
            "Accept: application/json"
    })
    @GET("/soa-infra/resources/pagamentos/SCA_Cielo/Cielo/listarReservasPendentes")
    Call<Usuario> buscaUsuario(@Query("codigo") long codigo);

    @GET("/webapi/api/cartao/ConsultarCartoesPorUsuario")
    Call<List<Perdido>> buscaCartao(@Query("codigo") long codigo);

    @GET("/webapi/api/veiculo/ConsultarVeiculoPorUsuario")
    Call<List<Veiculo>> buscaVeiculo(@Query("codigo") long codigo);

    @GET("/webapi/api/extrato/saldo")
    Call<Extrato> saldo(@Query("codigo") long codigo);

    @GET("/webapi/api/endereco/ConsultarEnderecoPorUsuario")
    Call<Usuario> buscaEndereco(@Query("codigo") long codigo);

    @GET("/webapi/api/usuario/login")
    Call<Usuario> login(@Query("email") String email,
                        @Query("senha") String senha);

    @GET("/webapi/api/habilitar/liberarBonus")
    Call<Bonus> liberarBonus(@Query("codigo") long codigo,
                             @Query("value") long value);

    @GET("/webapi/api/habilitar/liberarCredito")
    Call<Credito> liberarCredito(@Query("codigo") long codigo,
                                 @Query("value") long value);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @POST("/webapi/api/usuario/CadastrarUsuario")
    Call<Usuario> CadastrarUsuario(@Body Usuario usuario);

    @POST("/webapi/api/veiculo/CadastrarVeiculo")
    Call<Veiculo> CadastrarVeiculo(@Body Veiculo veiculo);

    @POST("/webapi/api/cartao/CadastrarCartao")
    Call<Perdido> CadastrarCartao(@Body Perdido cartao);

    @POST("/webapi/api/compra/CadastrarCompra")
    Call<Compra> CadastrarCompra(@Body Compra compra);

}

