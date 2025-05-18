package com.example.lr5;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lr1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/** Лабораторна 5 – переглядач зображень (без зуму) */
public class LR5Activity extends AppCompatActivity {

    private static final Pattern IMAGE_MIME = Pattern.compile("^image/.*");

    private final List<Uri> images = new ArrayList<>();
    private int index = 0;

    private ImageView imageView;

    /** SAF – вибір папки */
    private final ActivityResultLauncher<Uri> dirPicker =
            registerForActivityResult(new ActivityResultContracts.OpenDocumentTree(),
                    uri -> { if (uri != null) loadImages(uri); });

    @Override protected void onCreate(@Nullable Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.lr5);

        imageView = findViewById(R.id.imageView);
        Button prev = findViewById(R.id.btnPrev);
        Button next = findViewById(R.id.btnNext);
        Button pick = findViewById(R.id.btnPick);
        ImageButton info = findViewById(R.id.btnInfo);

        prev.setOnClickListener(v -> shift(-1));
        next.setOnClickListener(v -> shift(+1));
        pick.setOnClickListener(v -> dirPicker.launch(null));
        info.setOnClickListener(this::showAuthor);
    }

    /* ---------- читання зображень ---------- */

    private void loadImages(@NonNull Uri dirTree) {
        images.clear();
        ContentResolver cr = getContentResolver();

        Uri children = DocumentsContract.buildChildDocumentsUriUsingTree(
                dirTree, DocumentsContract.getTreeDocumentId(dirTree));

        try (var c = cr.query(children,
                new String[]{DocumentsContract.Document.COLUMN_DOCUMENT_ID,
                        DocumentsContract.Document.COLUMN_MIME_TYPE},
                null, null, null)) {

            if (c != null) {
                while (c.moveToNext()) {
                    String docId = c.getString(0);
                    String mime  = c.getString(1);
                    if (mime != null && IMAGE_MIME.matcher(mime).matches()) {
                        Uri uri = DocumentsContract.buildDocumentUriUsingTree(dirTree, docId);
                        images.add(uri);
                    }
                }
            }
        }

        if (images.isEmpty()) {
            Toast.makeText(this, "У вибраній папці немає зображень", Toast.LENGTH_SHORT).show();
        } else {
            index = 0;
            showImage();
        }
    }

    private void shift(int delta) {
        if (images.isEmpty()) return;
        index = (index + delta + images.size()) % images.size();
        showImage();
    }

    private void showImage() {
        imageView.setImageURI(images.get(index));
    }

    /* ---------- діалог «про автора» ---------- */

    private void showAuthor(View v) {
        View dlg = getLayoutInflater().inflate(R.layout.dialog_author, null);
        new AlertDialog.Builder(this)
                .setTitle("Про автора")
                .setView(dlg)
                .setPositiveButton("OK", null)
                .show();
    }
}
