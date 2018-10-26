package Models;

public class Usuario {

    public long id;
    public String nome;
    public String email;
    public long cpf;
    public String senha;
    public String confirmarSenha;

    public String cep;
    public String endereco;
    public String numero;

    public void setId(long id){
        this.id = id;
    }
    public long getId(){
        return this.id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }

    public void setCpf(long cpf){
        this.cpf = cpf;
    }
    public long getCpf(){
        return this.cpf;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }
    public String getSenha(){
        return this.senha;
    }

    public void setConfirmarSenha(String confirmarSenha){
        this.confirmarSenha = confirmarSenha;
    }
    public String getConfirmarSenha(){
        return this.confirmarSenha;
    }

    public void setCep(String cep){
        this.cep = cep;
    }
    public String getCep(){
        return this.cep;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    public String getEndereco(){
        return this.endereco;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }
    public String getNumero(){
        return this.numero;
    }

}
