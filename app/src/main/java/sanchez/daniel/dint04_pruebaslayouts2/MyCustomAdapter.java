package sanchez.daniel.dint04_pruebaslayouts2;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by daniel.rodriguez on 21/12/2016.
 */
public class MyCustomAdapter extends ArrayAdapter<Country>{
    private Context context;
    private List<Country> countries;

    public MyCustomAdapter(Context context, List<Country> countries) {
        super(context, 0, countries);
        this.context = context;
        this.countries = countries;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_item, parent, false);
        }
        Country country = countries.get(position);

        TextView tv = (TextView) convertView.findViewById(R.id.tv_text_list);
        tv.setText(country.getName());

        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image);
        imageView.setImageResource(country.getFlag());
        return convertView;
    }
}
