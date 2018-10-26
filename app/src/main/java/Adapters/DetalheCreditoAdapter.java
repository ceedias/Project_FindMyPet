package Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import Models.Credito;
import br.com.findmypet.estaciona.R;

public class DetalheCreditoAdapter extends BaseAdapter {

    private final List<Credito> credito;
    private final Activity act;

    public DetalheCreditoAdapter(List<Credito> credito, Activity  act) {
        this.credito = credito;
        this.act = act;
    }

    @Override
    public int getCount() {
        return credito.size();
    }

    @Override
    public Object getItem(int position) {
        return credito.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.activity_detalhes_credito, parent, false);
        Credito cred = credito.get(position);

        //pegando as referências das Views
        TextView id = (TextView)
                view.findViewById(R.id.tvCredito);
        TextView quantidade = (TextView)
                view.findViewById(R.id.tvValor);

        //populando as Views
        id.setTag(cred.id);
        id.setText(Long.toString( (cred.quantidade / 60)));
        quantidade.setText("Créditos");

        return view;

    }
}
