package com.danielifg.aa2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getPreferences(MODE_PRIVATE);

        String[] descriptions = getResources().getStringArray(R.array.dinosaursNames);
        int[] dinos = {R.drawable.achelousaurus_1, R.drawable.allosaurus_2, R.drawable.centrosaurus_3,
                R.drawable.spinosaurus_4, R.drawable.utahraptor_5, R.drawable.rajasaurus_6,
                R.drawable.albertosaurus_7, R.drawable.avaceratops_8, R.drawable.anchiceratops_9,
                R.drawable.nipponosaurus_10};

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new dinoAdapter(this, descriptions, dinos));

    }


//**************  Handling Action Bar Events ************  //

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.actionmenu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;

        switch (item.getItemId()) {
            case R.id.www:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.sciencekids.co.nz/pictures/dinosaurs.html"));
                startActivity(intent);
                return true;
            case R.id.about:
                intent = new Intent(this, About.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


// ***************************************************  //
// ***************************************************  //


    public class dinoAdapter extends BaseAdapter {
        private Context context;
        String[] dinoList;
        int[] dinoIds;
        LayoutInflater inflater;

        public dinoAdapter(Context c, String[] list, int[] imageId) {
            context = c;
            dinoList = list;
            dinoIds = imageId;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return dinoList.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }


        public class ViewHolder {
            TextView desc;
            ImageView img;

        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder vh = new ViewHolder();
            View row = convertView;

            if (convertView == null) {
                row = inflater.inflate(R.layout.custom_item, null);

                vh.desc = (TextView) row.findViewById(R.id.itemTV);
                vh.img = (ImageView) row.findViewById(R.id.itemIV);
                vh.desc.setText(dinoList[position]);
                vh.img.setImageResource(dinoIds[position]);
                row.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
                vh.desc.setText(dinoList[position]);
                vh.img.setImageResource(dinoIds[position]);
            }
            row.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context,Description.class);
                    intent.putExtra("dinos_img", dinoList[position]);
                    intent.putExtra("dinos_ids", dinoIds[position]);
                    context.startActivity(intent);

                }
            });
            return row;


        }
    }

}
