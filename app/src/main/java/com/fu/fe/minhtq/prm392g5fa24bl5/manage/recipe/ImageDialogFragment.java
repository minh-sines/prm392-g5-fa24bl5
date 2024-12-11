package com.fu.fe.minhtq.prm392g5fa24bl5.manage.recipe;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageDialogFragment extends DialogFragment {

    private static final String ARG_IMAGE_PATH = "image_path";
    private void openImageDialog(String imagePath) {
        // Mở một Dialog để hiển thị hình ảnh phóng to
        ImageDialogFragment dialog = ImageDialogFragment.newInstance(imagePath);
        dialog.show(getChildFragmentManager(), "ImageDialog");
    }


    public static ImageDialogFragment newInstance(String imagePath) {
        ImageDialogFragment fragment = new ImageDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE_PATH, imagePath);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_dialog, container, false);

        ImageView imageView = view.findViewById(R.id.image_view);
        String imagePath = getArguments().getString(ARG_IMAGE_PATH);

        // Tải Bitmap từ file và hiển thị vào ImageView
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            Toast.makeText(getContext(), "Không thể tải ảnh", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }
}