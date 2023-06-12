package com.example.meowaddopt;

import android.content.Context;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private TextView locationText,ageText;
    private Button btnTerapkan, btnReset;
    private DrawerLayout drawerLayout;
    private LinearLayout drawer;
    private GridView gridView;
    private SearchFragment.ImageTextAdapter adapter;
    private int[] images = {R.drawable.cat1, R.drawable.cat2, R.drawable.cat3, R.drawable.cat4,
            R.drawable.cat5, R.drawable.cat6, R.drawable.cat7, R.drawable.cat9, R.drawable.cat10,
            R.drawable.cat11, R.drawable.cat12, R.drawable.cat1, R.drawable.cat2, R.drawable.cat3,
            R.drawable.cat4, R.drawable.cat5, R.drawable.cat6, R.drawable.cat7, R.drawable.cat8,
            R.drawable.cat9};
    private String[] texts = {"Bella",
            "Max",
            "Luna",
            "Oliver",
            "Chloe",
            "Leo",
            "Lucy",
            "Milo",
            "Lily",
            "Charlie",
            "Sophie",
            "Simba",
            "Coco",
            "Oscar",
            "Nala",
            "Jasper",
            "Mia",
            "Felix",
            "Stella",
            "Rocky"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        gridView = view.findViewById(R.id.gridView);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        drawer = view.findViewById(R.id.drawerSlide);
        locationText = view.findViewById(R.id.location);
        ageText = view.findViewById(R.id.age);
        btnTerapkan = view.findViewById(R.id.terapkan);
        btnReset = view.findViewById(R.id.reset);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationText.setText("Not Set");
                ageText.setText("Not Set");
                drawerLayout.closeDrawer(drawer);
            }
        });

        btnTerapkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationText.setText("Surabaya Timur");
                ageText.setText("2-5 Tahun");
                drawerLayout.closeDrawer(drawer);
            }
        });

        adapter = new SearchFragment.ImageTextAdapter(requireContext(), images, texts);
        gridView.setAdapter(adapter);

        ImageView filterIcon = view.findViewById(R.id.filter);
        filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawer);
            }
        });

        return view;
    }

    private void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private class ImageTextAdapter extends BaseAdapter {
        private Context context;
        private int[] images;
        private String[] texts;

        public ImageTextAdapter(Context context, int[] images, String[] texts) {
            this.context = context;
            this.images = images;
            this.texts = texts;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView;
            if (convertView == null) {
                itemView = LayoutInflater.from(context).inflate(R.layout.custom_grid, parent, false);
            } else {
                itemView = convertView;
            }

            ImageView imageView = itemView.findViewById(R.id.imageView);
            TextView textView = itemView.findViewById(R.id.textView);

            imageView.setImageResource(images[position]);
            textView.setText(texts[position]);

            return itemView;
        }
    }

}