package sanchez.daniel.dint04_pruebaslayouts2;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<String> elementArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ejemploSpinner();
        ejemploListView();
        //ejemploContextMenu();
        //ejemploListViewPersonalizado();
    }

    @Override //Método que entra por defecto, igual que el onCreate()
    public boolean onCreateOptionsMenu (Menu menu) { //Método creación del menú
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void ejemploContextMenu() {
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //getMenuInflater().inflate(R.menu.main_menu, menu); //Para main_menu.xml
        getMenuInflater().inflate(R.menu.context_menu, menu); //Para context_menu.xml
    }

    @Override //Método que entra por defecto, igual que el onCreate()
    public boolean onContextItemSelected (MenuItem item) { //Método utilización del menú
        switch (item.getItemId()) {
            case R.id.option1:
                Toast.makeText(this, "Opción 1 pulsada", Toast.LENGTH_SHORT).show(); //No sale como opción por poner en xml showAsAction="always"
                return true;
            case R.id.option2:
                Toast.makeText(this, "Opción 2 pulsada", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option3:
                finish(); //Para cerrar la aplicación
                return true;

            case R.id.option_delete:
                //borrado de un elemento del listview

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); //Da la celda que ha llamado a listview
                int position = info.position; //Se obtiene la posicion de la celda
                elementArray.remove(position);
                //adapterFromList.notifyDataSetChanged();
                listView.refreshDrawableState();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void ejemploSpinner() {
        //Opción 1: Inicializar con array de Strings
        String [] elements = new String[] {"España", "Francia", "Alemania", "Italia"};
        //Opción 2: Inicializar con ArrayList de Strings
        elementArray = new ArrayList<String>(); //Es lo mismo que el array de elements de arriba
        elementArray.add("Spain");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, elements);

        ArrayAdapter<?> adapterFromXML = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);

        Spinner spinner = (Spinner) findViewById(R.id.sp_spinner);
        //spinner.setAdapter(adapter); //ArrayAdapter<String> adapter
        spinner.setAdapter(adapterFromXML); //ArrayAdapter<?> adapterFromXML
        spinner.setSelection(2); //Para elegir por donde queremos que empiece el Spinner

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                TextView textView = (TextView) findViewById(R.id.tv_text);
                textView.setText(adapterView.getAdapter().getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                TextView textView = (TextView) findViewById(R.id.tv_text);
                textView.setText("No ");
            }
        });
    }

    public void ejemploListView() {
        Spinner spinner = (Spinner) findViewById(R.id.sp_spinner);
        spinner.setVisibility(View.GONE);//Para hacer invisible al spinner

        String [] elements = new String[] {"España", "Francia", "Alemania", "Italia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, elements);

        listView = (ListView) findViewById(R.id.lv_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TextView textView = (TextView) findViewById(R.id.tv_text); //Mismo código que ejemplo anterior
                textView.setText(adapterView.getAdapter().getItem(position).toString());
            }
        });
    }

    public void ejemploListViewPersonalizado() {
        List<Country> countriesList = new ArrayList<>();
        countriesList.add(new Country("España", R.drawable.iconoespana));

        MyCustomAdapter customAdapter = new MyCustomAdapter(this, countriesList);

        ListView listView = (ListView) findViewById(R.id.lv_list);
        listView.setAdapter(customAdapter);
    }

    public void addNewElement(View view) {

    }
}