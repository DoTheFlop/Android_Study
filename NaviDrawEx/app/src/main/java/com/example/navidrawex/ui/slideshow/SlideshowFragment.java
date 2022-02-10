package com.example.navidrawex.ui.slideshow;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.navidrawex.R;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.stream.Stream;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    View dialogView, header;
    TextView id, mail;
    ImageView headerView;
    Menu menu;
    MenuItem slide;
    AlertDialog.Builder dlg;
    String check;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);

        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        header = navigationView.getHeaderView(0);
        menu = navigationView.getMenu();
        slide = menu.findItem(R.id.nav_slideshow);

        id = header.findViewById(R.id.nav_id);
        mail = header.findViewById(R.id.nav_id2);
        headerView = header.findViewById(R.id.imageView);

        dlg = new AlertDialog.Builder(getActivity());
        final String[] versionArray = new String[]{"dog", "cat", "horse"};
        dialogView = (View) View.inflate(getActivity(), R.layout.dialogview, null);
        dlg.setView(dialogView);

        dlg.setSingleChoiceItems(versionArray, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0)
                    headerView.setImageResource(R.drawable.dog);
                else if (which == 1)
                    headerView.setImageResource(R.drawable.cat);
                else if (which == 2)
                    headerView.setImageResource(R.drawable.horse);
            }
        });
        dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText name = dialogView.findViewById(R.id.editView);
                id.setText(name.getText().toString());
                EditText email = dialogView.findViewById(R.id.editView2);
                mail.setText(email.getText().toString());
                slide.setTitle(R.string.nav_slideshow2);
            }
        });
        dlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Cancel Action", Toast.LENGTH_SHORT).show();
            }
        });
        check = slide.getTitle().toString();
        if(check == getString(R.string.nav_slideshow)){
            dlg.show();
            slide.setTitle(R.string.nav_slideshow2);
        }
        else if(check == getString(R.string.nav_slideshow2)){
            id.setText(R.string.nav_header_title);
            mail.setText(R.string.nav_header_subtitle);
            slide.setTitle(R.string.nav_slideshow);
            headerView.setImageResource(R.mipmap.ic_launcher_round);
        }
        return root;
    }
}