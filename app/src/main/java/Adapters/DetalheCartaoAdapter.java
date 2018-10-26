package Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import Models.Perdido;
import br.com.findmypet.estaciona.R;
import retrofit.Response;

public class DetalheCartaoAdapter extends BaseAdapter {

    private final Response<List<Perdido>> cartao;
    private final Activity act;

    public DetalheCartaoAdapter(Response<List<Perdido>> cartao, Activity  act) {
        this.cartao = cartao;
        this.act = act;
    }

    @Override
    public int getCount() {
        return cartao.body().size();
    }

    @Override
    public Object getItem(int position) {
        return cartao.body().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.activity_detalhes_cartao, parent, false);
        Perdido cartoes = cartao.body().get(position);

        //pegando as referências das Views
        TextView nome = (TextView)
                view.findViewById(R.id.tvTitular);
        TextView descricao = (TextView)
                view.findViewById(R.id.tvCartao);
        ImageView imagem = (ImageView)
                view.findViewById(R.id.imvCartao);

        //populando as Views
        descricao.setTag( cartoes.id);
        imagem.setImageResource(R.mipmap.ic_cartoes);

        return view;
    }
}
