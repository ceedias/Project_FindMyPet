package Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import Models.Bonus;
import br.com.findmypet.estaciona.R;

public class DetalheBonusAdapter extends BaseAdapter {

    private final List<Bonus> bonus;
    private final Activity act;

    public DetalheBonusAdapter(List<Bonus> bonus, Activity  act) {
        this.bonus = bonus;
        this.act = act;
    }

    @Override
    public int getCount() {
        return bonus.size();
    }

    @Override
    public Object getItem(int position) {
        return bonus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.activity_detalhes_bonus, parent, false);
        Bonus cred = bonus.get(position);

        TextView id = view.findViewById(R.id.tvBonus);
        TextView quantidade = view.findViewById(R.id.tvValor);

        long total = (cred.quantidade / 60);
        String quebrado = "";

        if((cred.quantidade % 60) != 0){
            quebrado = ".5";
        }

        id.setTag(cred.id);
        id.setText(Long.toString(total)  + quebrado);
        quantidade.setText("Bonus");

        return view;
    }
}
