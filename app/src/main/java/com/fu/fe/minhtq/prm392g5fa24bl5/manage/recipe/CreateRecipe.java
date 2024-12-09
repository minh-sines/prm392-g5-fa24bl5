package com.fu.fe.minhtq.prm392g5fa24bl5.manage.recipe;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.fu.fe.minhtq.prm392g5fa24bl5.R;

import java.io.IOException;

public class CreateRecipe extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_GALLERY = 2;

    private static final int REQUEST_CAMERA_DESCRIPTION = 3;
    private static final int REQUEST_GALLERY_DESCRIPTION = 4;
    private static final int REQUEST_CAMERA_STEP = 5;
    private static final int REQUEST_GALLERY_STEP = 6;

    private static final int REQUEST_CAMERA_PERMISSION = 101;
    private static final int REQUEST_GALLERY_PERMISSION = 102;
    private static final int REQUEST_CAMERA_DESCRIPTION_PERMISSION = 103;
    private static final int REQUEST_GALLERY_DESCRIPTION_PERMISSION = 104;
    private static final int REQUEST_CAMERA_STEP_PERMISSION = 105;
    private static final int REQUEST_GALLERY_STEP_PERMISSION = 106;
    private static final int REQUEST_PERMISSIONS = 100;

    private int selectedImageStepIndex = -1;
    private int countDeleteStep = 0;
    private Button btnAddDish;
    private Button btnDishName;
    private Button btnDescription;
    private Button btnCookingTime;
    private Button btnIngredients;
    private Button btnSteps;

    private EditText edtDishName;
    private EditText edtDescription;
    private EditText edtCookingTime;
    private EditText edtIngredients;
    private EditText edtSteps;

    private ImageView imgAddDescriptionImage;
    private ImageView imgAddDish;

    private ImageButton btnAddIngredient;
    private Button btnAddStep;
    private ImageButton btnDeleteStep;
    private ImageButton btnAddImageStep;

    private LinearLayout ingredientsContainer;
    private LinearLayout listLayoutStep;
    private LinearLayout selectedImagesContainer;

    private LinearLayout imageContainerForStep;


    private int stepCount = 1;

    private void bindingView() {
        btnAddDish = findViewById(R.id.btnCreateDish);

        edtCookingTime = findViewById(R.id.edtCookingTime);
        edtDescription = findViewById(R.id.edtDescription);
        edtDishName = findViewById(R.id.edtDishName);
//        edtIngredients = findViewById(R.id.edtIngredients);
//        edtSteps = findViewById(R.id.edtSteps);
        imgAddDescriptionImage = findViewById(R.id.imgAddDescriptionImage);
        imgAddDish = findViewById(R.id.imgAddDish);

        btnAddIngredient = findViewById(R.id.btnAddIngredient);
//        btnAddStep = findViewById(R.id.btnAddStep);
//        btnDeleteStep = findViewById(R.id.btnDeleteStep);
        ingredientsContainer = findViewById(R.id.ingredientsContainer);
        btnAddStep = findViewById(R.id.btnAddStep);
        listLayoutStep = findViewById(R.id.listLayoutStep);

        selectedImagesContainer = findViewById(R.id.selectedImagesContainer);
        btnAddImageStep = findViewById(R.id.btnAddImageStep);

    }

    private void bindingAction() {
        btnAddIngredient.setOnClickListener(this::onButtonAddIngredients);
        imgAddDish.setOnClickListener(this::onButtonAddImage);
        btnAddStep.setOnClickListener(this::onButtonAddStep);
        imgAddDescriptionImage.setOnClickListener(this::onButtonImageDescription);
        btnAddImageStep.setOnClickListener(this::onButtonImageStep);
    }

    private void onButtonImageStep(View view) {
        selectedImageStepIndex = 0;
        openImagePickerDialog(REQUEST_CAMERA_STEP, REQUEST_GALLERY_STEP, REQUEST_CAMERA_STEP_PERMISSION, REQUEST_GALLERY_STEP_PERMISSION);
    }

    private void onButtonImageDescription(View view) {
        openImagePickerDialog(REQUEST_CAMERA_DESCRIPTION, REQUEST_GALLERY_DESCRIPTION,
                REQUEST_CAMERA_DESCRIPTION_PERMISSION, REQUEST_GALLERY_DESCRIPTION_PERMISSION);
    }

    private void onButtonAddStep(View view) {
        addNewStepRow();
    }

    private void onButtonAddImage(View view) {
        openImagePickerDialog(REQUEST_CAMERA, REQUEST_GALLERY, REQUEST_CAMERA_PERMISSION, REQUEST_GALLERY_PERMISSION);
    }

    private void onButtonAddIngredients(View view) {
        addNewIngredientRow();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_recipe);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        bindingView();
        bindingAction();

    }

    private void addNewIngredientRow() {
        // Create a new LinearLayout for the row
        LinearLayout newRow = new LinearLayout(this);
        newRow.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        newRow.setOrientation(LinearLayout.HORIZONTAL);
        newRow.setGravity(View.TEXT_ALIGNMENT_CENTER);
        newRow.setPadding(0, 8, 0, 8);

        // Create a new EditText for ingredient input
        EditText newIngredient = new EditText(this);
        newIngredient.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1 // Weight to fill remaining space
        ));
        newIngredient.setHint("Nhập nguyên liệu");
        newIngredient.setBackgroundResource(android.R.drawable.edit_text);
        newIngredient.setPadding(16, 16, 16, 16);

        // Create a delete button for the row
        ImageButton deleteButton = new ImageButton(this);
        deleteButton.setLayoutParams(new LinearLayout.LayoutParams(
                80,
                80
        ));
        deleteButton.setImageResource(R.drawable.ic_delete);
        deleteButton.setBackgroundResource(0);
        deleteButton.setContentDescription("Xóa nguyên liệu");

        // Add the EditText and DeleteButton to the row
        newRow.addView(newIngredient);
        newRow.addView(deleteButton);

        // Add the new row to the ingredients container
        ingredientsContainer.addView(newRow);

        // Set delete button action
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredientsContainer.removeView(newRow);
            }
        });
    }


    private void openImagePickerDialog(int cameraCode, int galleryCode, int cameraPermissionCode, int galleryPermissionCode) {
        // Hiển thị tùy chọn chụp ảnh hoặc chọn từ thư viện
        String[] options = {"Chụp ảnh", "Chọn từ thư viện"};
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Thêm ảnh")
                .setItems(options, (dialog, which) -> {
                    if (which == 0) {
                        openCamera(cameraCode, cameraPermissionCode);
                    } else if (which == 1) {
                        openGallery(galleryCode);
                    }
                })
                .show();
    }

    private void openCamera(int requestCode, int permissionCode) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, requestCode);
            } else {
                Toast.makeText(this, "Không thể mở camera", Toast.LENGTH_SHORT).show();
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, permissionCode);

        }
    }

    private void openGallery(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA && data != null) {
                // Lấy ảnh từ camera
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imgAddDish.setImageBitmap(photo);
            } else if (requestCode == REQUEST_GALLERY && data != null) {
                // Lấy ảnh từ thư viện
                Uri selectedImage = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    imgAddDish.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CAMERA_DESCRIPTION && data != null) {
                // Lấy ảnh từ camera
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                addImageToDescriptionList(photo);
            } else if (requestCode == REQUEST_GALLERY_DESCRIPTION && data != null) {
                // Lấy ảnh từ thư viện
                Uri selectedImage = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    addImageToDescriptionList(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CAMERA_STEP && data != null) {
                // Lấy ảnh từ camera
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                LinearLayout stepLayout = (LinearLayout) listLayoutStep.getChildAt(selectedImageStepIndex);
                ImageButton imageButton = (ImageButton) stepLayout.getChildAt(2);
                imageButton.setImageBitmap(photo);
            } else if (requestCode == REQUEST_GALLERY_STEP && data != null) {
                // Lấy ảnh từ thư viện
                Uri selectedImage = data.getData();
                try{
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    LinearLayout stepLayout = (LinearLayout) listLayoutStep.getChildAt(selectedImageStepIndex);
                    ImageButton imageButton = (ImageButton) stepLayout.getChildAt(2);
                    imageButton.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
    }

    private void setImageForStep(Bitmap photo) {
        btnAddImageStep.setImageBitmap(photo);
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, REQUEST_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền camera được cấp, mở camera
                openCamera(REQUEST_CAMERA, REQUEST_CAMERA_PERMISSION);
            } else {
                Toast.makeText(this, "Cần cấp quyền camera để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_GALLERY_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền thư viện được cấp, mở thư viện
                openGallery(REQUEST_GALLERY);
            } else {
                Toast.makeText(this, "Cần cấp quyền thư viện để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CAMERA_DESCRIPTION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền camera được cấp, mở camera
                openCamera(REQUEST_CAMERA_DESCRIPTION, REQUEST_CAMERA_DESCRIPTION_PERMISSION);
            } else {
                Toast.makeText(this, "Cần cấp quyền camera để sử dụng tính năng này", Toast.LENGTH_SHORT).show();

            }
        } else if (requestCode == REQUEST_GALLERY_DESCRIPTION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền thư viện được cấp, mở thư viện
                openGallery(REQUEST_GALLERY_DESCRIPTION);
            } else {
                Toast.makeText(this, "Cần cấp quyền thư viện để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CAMERA_STEP_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền camera được cấp, mở camera
                openCamera(REQUEST_CAMERA_STEP, REQUEST_CAMERA_STEP_PERMISSION);
            } else{
                Toast.makeText(this, "Cần cấp quyền camera để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_GALLERY_STEP_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền thư viện được cấp, mở thư viện
                openGallery(REQUEST_GALLERY_STEP);
            }else{
                Toast.makeText(this, "Cần cấp quyền thư viện để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void addNewStepRow() {
        // Tăng số bước
        stepCount++;


        // Tạo LinearLayout ngang cho bước mới
        LinearLayout newRow = new LinearLayout(this);
        newRow.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        newRow.setOrientation(LinearLayout.HORIZONTAL);
        newRow.setGravity(Gravity.CENTER_VERTICAL);
//        newRow.setPadding(8, 8, 8, 8); // Đặt padding cho mỗi dòng

        // TextView: Số bước
        TextView stepLabel = new TextView(this);
        stepLabel.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                0.8F // Trọng số nhỏ hơn để nhường chỗ cho EditText
        ));
        stepLabel.setText("Bước " + stepCount);
        stepLabel.setTextSize(14);

        // EditText: Nội dung bước
        EditText stepInput = new EditText(this);
        stepInput.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                2 // Tăng trọng số cho EditText
        ));
        stepInput.setHint("Nhập bước " + stepCount);
        stepInput.setBackgroundResource(android.R.drawable.edit_text);
        stepInput.setPadding(16, 8, 8, 8);

        // ImageButton: Nút thêm hình ảnh
        ImageButton addImageButton = new ImageButton(this);
        addImageButton.setLayoutParams(new LinearLayout.LayoutParams(
                80,
                80
        ));
        addImageButton.setImageResource(R.drawable.add_image);
        addImageButton.setBackgroundResource(0);
        addImageButton.setContentDescription("Thêm ảnh cho bước " + stepCount);
        addImageButton.setPadding(4, 0, 0, 0);
        addImageButton.setTag(stepCount);
        addImageButton.setOnClickListener(v -> {
            selectedImageStepIndex = (int) v.getTag() - countDeleteStep;
            openImagePickerDialog(REQUEST_CAMERA_STEP,REQUEST_GALLERY_STEP,
                    REQUEST_CAMERA_STEP_PERMISSION,REQUEST_GALLERY_STEP_PERMISSION);
        });

        // ImageButton: Nút xóa bước
        ImageButton deleteButton = new ImageButton(this);
        deleteButton.setLayoutParams(new LinearLayout.LayoutParams(
                80,
                80
        ));
        deleteButton.setImageResource(R.drawable.ic_delete);
        deleteButton.setBackgroundResource(0);
        deleteButton.setContentDescription("Xóa bước " + stepCount);
        deleteButton.setPadding(4, 0, 0, 0);
        // Xử lý sự kiện xóa bước
        deleteButton.setOnClickListener(v -> {
            listLayoutStep.removeView(newRow); // Xóa dòng khỏi container
//            countDeleteStep++;

            stepCount--;
            updateStepNumbers(listLayoutStep); // Cập nhật lại số bước
            updateStepTags(listLayoutStep);
        });

        // Thêm các thành phần vào hàng
        newRow.addView(stepLabel);
        newRow.addView(stepInput);
        newRow.addView(addImageButton);
        newRow.addView(deleteButton);

        // Thêm hàng vào container chính
        listLayoutStep.addView(newRow);
    }

    private void updateStepTags(LinearLayout listLayoutStep) {
        int minusStep = 0;
        for (int i = 0; i <= stepCount; i++) {
            LinearLayout stepRow = (LinearLayout) listLayoutStep.getChildAt(i);
            ImageButton addImageButton = (ImageButton) stepRow.getChildAt(2); // Giả định nút addImageButton ở vị trí thứ 2
            if(addImageButton != null){
                addImageButton.setTag(i + 1 - minusStep); // Gán lại tag theo thứ tự thực tế
            }else{
                minusStep++;
            }

        }
    }

    // Hàm cập nhật số thứ tự của các bước sau khi xóa
    private void updateStepNumbers(LinearLayout stepsContainer) {
        int minusStep = 0;
//        int childCount = stepsContainer.getChildCount();
        for (int i = 0; i <= stepCount; i++) {
            LinearLayout stepRow = (LinearLayout) stepsContainer.getChildAt(i);
            TextView stepLabel = (TextView) stepRow.getChildAt(0);

            if (stepLabel != null){
                String t = stepLabel.getText().toString();
                stepLabel.setText("Bước " + (i + 1 - minusStep));
                t = stepLabel.getText().toString();
                EditText edtStep = (EditText) stepRow.getChildAt(1);
                edtStep.setHint("Nhập bước " + (i + 1 - minusStep) );
            }else{
                minusStep++;
            }

        }
    }

//    private void openImagePickerDialogForDescription() {
//        // Hiển thị tùy chọn chụp ảnh hoặc chọn từ thư viện
//        String[] options = {"Chụp ảnh", "Chọn từ thư viện"};
//        new androidx.appcompat.app.AlertDialog.Builder(this)
//                .setTitle("Thêm ảnh mô tả")
//                .setItems(options, (dialog, which) -> {
//                    if (which == 0) {
//                        openCamera(REQUEST_CAMERA_DESCRIPTION);
//                    } else if (which == 1) {
//                        openGallery(REQUEST_GALLERY_DESCRIPTION);
//                    }
//                })
//                .show();
//    }

//    private void openCameraForDescription() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            if (intent.resolveActivity(getPackageManager()) != null) {
//                startActivityForResult(intent, REQUEST_CAMERA_DESCRIPTION);
//            } else {
//                Toast.makeText(this, "Không thể mở camera", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
//        }
//    }
//
//    private void openGalleryForDescription() {
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, REQUEST_GALLERY_DESCRIPTION);
//    }


    private void addImageToDescriptionList(Bitmap bitmap) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels; // Chiều rộng màn hình

        // Tính toán chiều rộng tối đa của ảnh (1/4 chiều rộng màn hình)
        int maxWidth = screenWidth / 4;
        // Tạo một ImageView mới để hiển thị ảnh
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                maxWidth,
                maxWidth
        ));
        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setMaxWidth(40);
        imageView.setMaxHeight(40);

        // Thêm ảnh vào LinearLayout chứa ảnh mô tả
        selectedImagesContainer.addView(imageView);
    }
}