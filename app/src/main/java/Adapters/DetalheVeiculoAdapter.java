package Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import Models.Veiculo;
import br.com.findmypet.estaciona.R;
import retrofit.Response;

public class DetalheVeiculoAdapter extends BaseAdapter {

    private final Response<List<Veiculo>> veiculo;
    private final Activity act;

    public DetalheVeiculoAdapter(Response<List<Veiculo>> veiculo, Activity  act) {
        this.veiculo = veiculo;
        this.act = act;
    }

    @Override
    public int getCount() {
        return veiculo.body().size();
    }

    @Override
    public Object getItem(int position) {
        return veiculo.body().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.activity_detalhes_veiculo, parent, false);
        Veiculo vei = veiculo.body().get(position);

        //pegando as referências das Views
        TextView placa = (TextView)
                view.findViewById(R.id.tvPlaca);
        TextView nomeVeiculo = (TextView)
                view.findViewById(R.id.tvNomeVeiculo);
        ImageView imagem = (ImageView)
                view.findViewById(R.id.imvVeiculo);

        //populando as Views
        placa.setText(vei.placa.toString());
        nomeVeiculo.setText(vei.NomeVeiculo);
        imagem.setImageResource(R.mipmap.ic_carro);

        return view;

    }
}
