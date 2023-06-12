package com.example.meowaddopt;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavFragment extends Fragment{

//    private GridView gridView;
//    private ArrayAdapter<String> adapter;
//    int [] gambar = {R.drawable.cat, R.drawable.cat, R.drawable.cat,
//            R.drawable.cat, R.drawable.cat, R.drawable.cat,
//            R.drawable.cat, R.drawable.cat};
//    String [] namanya = {"Cita", "Domba", "Gajah", "Gorila", "Harimau", "Hiu",
//            "Kuda", "Puma"};

    TextView userName;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private GridView gridView;
    private ImageTextAdapter adapter;
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

    public FavFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavFragment newInstance(String param1, String param2) {
        FavFragment fragment = new FavFragment();
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
        View view = inflater.inflate(R.layout.fragment_fav, container, false);

        gridView = view.findViewById(R.id.gridView);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getActivity(),gso);

        userName = view.findViewById(R.id.textView2);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        if(acct != null){
//            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();

            String finalUsername = personEmail.split("@")[0];
            userName.setText(finalUsername);
//            email.setText(personEmail);
        }

        adapter = new ImageTextAdapter(requireContext(), images, texts);
        gridView.setAdapter(adapter);

        return view;
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

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);

//        String[] cat = {"Ciki","Kitkat","Oci","Lulu","Ciki","Kitkat","Oci","Lulu"};
//
//        ListView listView = (ListView)  view.findViewById(R.id.listfavcat);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.list_item, cat);
//
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(this);
//    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////        if(position==0 || position==4){
////            Toast.makeText(getActivity(), "Ciki", Toast.LENGTH_SHORT).show();
////        }
////        if(position==1 || position==5){
////            Toast.makeText(getActivity(), "Kitkat", Toast.LENGTH_SHORT).show();
////        }
////        if(position==2 || position==6){
////            Toast.makeText(getActivity(), "Oci", Toast.LENGTH_SHORT).show();
////        }
////        if(position==3 || position==7){
////            Toast.makeText(getActivity(), "Lulu", Toast.LENGTH_SHORT).show();
////        }
//    }
}