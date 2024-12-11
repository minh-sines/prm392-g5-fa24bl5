package com.fu.fe.minhtq.prm392g5fa24bl5.manage.recipe;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fu.fe.minhtq.prm392g5fa24bl5.HomePage.HomePage;
import com.fu.fe.minhtq.prm392g5fa24bl5.MainActivity;
import com.fu.fe.minhtq.prm392g5fa24bl5.R;
import com.fu.fe.minhtq.prm392g5fa24bl5.database.AppDatabase;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Account;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Ingredient;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Recipe_image;
import com.fu.fe.minhtq.prm392g5fa24bl5.model.Step;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UpdateRecipe extends AppCompatActivity {

    private int recipeId;
    private List<String> imgDescList;
    private AppDatabase db;
    private Recipe updatedRecipe;
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

    private Button btnDeleteDish;
    private int selectedImageStepIndex = -1;
    private int countDeleteStep = 0;
    private Button btnAddDish;
    private Button btnDishName;
    private Button btnDescription;
    private Button btnCookingTime;
    private Button btnIngredients;
    private Button btnSteps;

    private EditText edtStep1;

    private EditText edtIngre1;

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
    private RecyclerView selectedImagesContainer;

    private LinearLayout linearIngre1;
    private LinearLayout imageContainerForStep;
    private int maxWidth;

    private List<Bitmap> listImgDescBitmap;

    private int stepCount = 1;

    private void bindingView() {
        btnAddDish = findViewById(R.id.btnCreateDish);
        db = AppDatabase.getInstance(this);
        updatedRecipe = db.recipeDAO().getRecipeById(recipeId);

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
        linearIngre1 = findViewById(R.id.linearIngre1);
        btnAddStep = findViewById(R.id.btnAddStep);
        listLayoutStep = findViewById(R.id.listLayoutStep);
        edtStep1 = findViewById(R.id.edtStep1);
        btnDeleteDish = findViewById(R.id.btnDeleteDish);

        selectedImagesContainer = findViewById(R.id.selectedImagesContainer);
        btnAddImageStep = findViewById(R.id.btnAddImageStep);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels; // Chiều rộng màn hình
        listImgDescBitmap = new ArrayList<>();
        imgDescList = db.recipe_imageDAO().getImagePathsByRecipeId(recipeId);
        edtIngre1 = findViewById(R.id.edtIngr1);
        for (int i = 0; i < imgDescList.size(); i++) {
            // Lấy thư mục private của ứng dụng
            File directory = this.getFilesDir();

            // Tạo đường dẫn tới file ảnh
            File file = new File(directory, imgDescList.get(i));

            // Kiểm tra xem file có tồn tại không
            if (file.exists()) {
                // Đọc ảnh từ file và chuyển thành Bitmap
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                if (bitmap != null) {
                    listImgDescBitmap.add(bitmap); // Thêm vào danh sách Bitmap
                }
            }
        }
        // Tính toán chiều rộng tối đa của ảnh (1/4 chiều rộng màn hình)
        maxWidth = screenWidth / 4;

    }

    private void bindingAction() {
        btnAddIngredient.setOnClickListener(this::onButtonAddIngredients);
        imgAddDish.setOnClickListener(this::onButtonAddImage);
        btnAddStep.setOnClickListener(this::onButtonAddStep);
        imgAddDescriptionImage.setOnClickListener(this::onButtonImageDescription);
        btnAddImageStep.setOnClickListener(this::onButtonImageStep);
        btnAddDish.setOnClickListener(this::onButtonCreateDish);
        btnDeleteDish.setOnClickListener(this::onDeleteDish);
    }

    private void onDeleteDish(View view) {
        // Tạo một AlertDialog để xác nhận việc xóa món ăn
        new AlertDialog.Builder(this)
                .setMessage("Bạn có chắc chắn muốn xóa món ăn này?")
                .setCancelable(false) // Không cho phép đóng dialog bằng cách chạm ngoài vùng dialog
                .setPositiveButton("Có", (dialog, which) -> {
                    // Nếu người dùng chọn "Có", thực hiện xóa món ăn
                    Recipe deleteRecipe = db.recipeDAO().getRecipeById(recipeId);
                    deleteRecipe.setDelete(true);  // Đánh dấu món ăn là đã bị xóa (hoặc bạn có thể xóa trực tiếp trong DB)
                    db.recipeDAO().updateRecipe(deleteRecipe);

                    Toast.makeText(this, "Xóa món ăn thành công", Toast.LENGTH_SHORT).show();
                    recreate(); // Tái tạo lại activity để cập nhật giao diện
                })
                .setNegativeButton("Không", (dialog, which) -> {
                    // Nếu người dùng chọn "Không", chỉ cần đóng dialog và không làm gì
                    dialog.dismiss();
                })
                .show();
    }

    private void onButtonCreateDish(View view) {
        if(edtDishName.getText().toString().isEmpty()){
            Toast.makeText(this, "Vui lòng nhập tên món ăn", Toast.LENGTH_SHORT).show();
            return;
        }
        if(edtDescription.getText().toString().isEmpty()){
            Toast.makeText(this, "Vui lòng nhập mô tả", Toast.LENGTH_SHORT).show();
            return;
        }

        if(edtCookingTime.getText().toString().isEmpty()){
            Toast.makeText(this, "Vui lòng nhập thời gian", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < listLayoutStep.getChildCount(); i++) {
            View child = listLayoutStep.getChildAt(i);

            if (child instanceof LinearLayout) {
                LinearLayout stepRow = (LinearLayout) child;
                for (int j = 0; j < stepRow.getChildCount(); j++) {
                    View stepChild = stepRow.getChildAt(j);
                    if (stepChild instanceof EditText) {
                        EditText stepInput = (EditText) stepChild;
                        if (stepInput.getText().toString().trim().isEmpty()) {
                            Toast.makeText(this, "Vui lòng nhập bước " + (i + 1), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < ingredientsContainer.getChildCount(); i++) {
            View child = ingredientsContainer.getChildAt(i);

            if (child instanceof LinearLayout) {
                LinearLayout ingredientRow = (LinearLayout) child;
                for (int j = 0; j < ingredientRow.getChildCount(); j++) {
                    View ingredientChild = ingredientRow.getChildAt(j);
                    if (ingredientChild instanceof EditText) {
                        EditText ingredientInput = (EditText) ingredientChild;
                        if (ingredientInput.getText().toString().trim().isEmpty()) {
                            Toast.makeText(this, "Vui lòng nhập nguyên liệu " + (i + 1), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }


//        Recipe recipe = new Recipe(
//                edtDishName.getText().toString(),
//                edtDescription.getText().toString(),
//                edtCookingTime.getText().toString(),
//                /* created_by */ 1, // ID người tạo (cần thay bằng ID thực tế nếu có)
//                System.currentTimeMillis(), // Thời gian tạo
//                System.currentTimeMillis(),  // Thời gian cập nhật
//                false // delete
//        );


        // Thêm Recipe vào DB
        AppDatabase db = AppDatabase.getInstance(this);
//        db.accountDAO().insertAccount(new Account("Logan", "logan@gmail.com", "123",0));
//        db.accountDAO().insertAccount(new Account("Jim", "jim@gmail.com", "123", 0));
//        db.accountDAO().insertAccount(new Account("Will", "will@gmail.com", "123", 0));
//        long recipeId = db.recipeDAO().insertRecipe(recipe);
        String fileName = "";

        Bitmap bitmap = ((BitmapDrawable) imgAddDish.getDrawable()).getBitmap();
        if (bitmap != null && !isDefaultImage(imgAddDish)) {
            // Tạo tên file cho ảnh
            fileName = "recipe_" + recipeId + ".png";

            // Lưu ảnh vào bộ nhớ trong
            saveImageToFile(bitmap, UpdateRecipe.this, fileName);

            updatedRecipe = db.recipeDAO().getRecipeById((int) recipeId);

            updatedRecipe.setMainImage(fileName);
            updatedRecipe.setUpdated_at(System.currentTimeMillis());
            db.recipeDAO().updateRecipe(updatedRecipe);
            Log.d("UpdateRecipe", "Updated recipe with ID: " + updatedRecipe.getMainImage());
        }
        List<Recipe_image> oldRecipeImages = db.recipe_imageDAO().getRecipe_imagesByRecipeId((int) recipeId);
        for (Recipe_image oldRecipeImage : oldRecipeImages) {
            db.recipe_imageDAO().deleteRecipe_image(oldRecipeImage);
        }

        if (listImgDescBitmap != null && !listImgDescBitmap.isEmpty()) {
            for (int i = 0; i < listImgDescBitmap.size(); i++) {
                Bitmap bitmapDesc = listImgDescBitmap.get(i);

                // Tạo tên file
                String fileNameDesc = "recipe_" + recipeId + "_description_" + i + ".png";

                // Lưu ảnh vào file
                saveImageToFile(bitmapDesc, UpdateRecipe.this, fileNameDesc);

                // Tạo đối tượng Recipe_image
                Recipe_image recipe_image = new Recipe_image(fileNameDesc, (int) recipeId);

                // Lưu đối tượng vào cơ sở dữ liệu
                db.recipe_imageDAO().insertRecipe_image(recipe_image);
            }
        }



//        if(selectedImagesContainer.getChildCount() > 0){
//            for (int i = 0; i < selectedImagesContainer.getChildCount(); i++) {
//                ImageView imageView = (ImageView) selectedImagesContainer.getChildAt(i);
//                bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//                fileName = "recipe_" + recipeId + "_description_" + i + ".png";
//                saveImageToFile(bitmap, CreateRecipe.this, fileName);
//                Recipe_image recipe_image = new Recipe_image(fileName, (int) recipeId);
//                db.recipe_imageDAO().insertRecipe_image(recipe_image);
//            }
//        };


        List<Ingredient> oldIngredients = db.ingredientDAO().getIngredientsByRecipeId((int) recipeId);
        List<Ingredient> newIngredients = new ArrayList<>();
        // Lưu danh sách nguyên liệu
        for (int i = 0; i < ingredientsContainer.getChildCount(); i++) {
            LinearLayout ingredientRow = (LinearLayout) ingredientsContainer.getChildAt(i);
            EditText ingredientInput = (EditText) ingredientRow.getChildAt(0);
            Ingredient ingredient = new Ingredient(
                    (int)recipeId,
                    ingredientInput.getText().toString()
            );
            newIngredients.add(ingredient);
//            db.ingredientDAO().insertIngredient(ingredient);
        }
        List<Ingredient> deletedIngredients = new ArrayList<>(oldIngredients);
        for (Ingredient oldIngredient : oldIngredients) {
            for (Ingredient newIngredient : newIngredients) {
                if (oldIngredient.getName().equals(newIngredient.getName())) {
                    deletedIngredients.remove(oldIngredient); // Nếu tồn tại trong danh sách mới, không bị xóa
                    break;
                }
            }
        }
        List<Ingredient> addedIngredients = new ArrayList<>();
        for (Ingredient newIngredient : newIngredients) {
            boolean exists = false;
            for (Ingredient oldIngredient : oldIngredients) {
                if (newIngredient.getName().equals(oldIngredient.getName())) {
                    exists = true; // Nếu đã tồn tại, không cần thêm
                    break;
                }
            }
            if (!exists) {
                addedIngredients.add(newIngredient);
            }
        }
        for (Ingredient ingredient : deletedIngredients) {
            db.ingredientDAO().deleteIngredient(ingredient);
        }

        // Thêm nguyên liệu mới
        for (Ingredient ingredient : addedIngredients) {
            db.ingredientDAO().insertIngredient(ingredient);
        }


        List<Step> oldSteps = db.stepDAO().getStepsByRecipeId((int) recipeId);
        for (Step oldStep : oldSteps) {
            db.stepDAO().deleteStep(oldStep);
        }

        List<Step> newSteps = new ArrayList<>();

        // Lưu danh sách bước
        for (int i = 0; i < listLayoutStep.getChildCount(); i++) {
            LinearLayout stepRow = (LinearLayout) listLayoutStep.getChildAt(i);
            EditText stepInput = (EditText) stepRow.getChildAt(1);  // Text mô tả bước
            ImageButton imageButton = (ImageButton) stepRow.getChildAt(2); // Nút để chọn ảnh

            // Lấy URI của ảnh từ ImageButton
//            Uri imageUri = getImageUriFromImageButton(imageButton);
            final int stepIndex = i;

            // Lấy Bitmap từ ImageButton
            bitmap = getBitmapFromImageButton(imageButton);
            String fileNameStep = "";
            if (bitmap != null && !isDefaultImage(imageButton)) {
                // Tạo tên file cho ảnh
                fileNameStep = "recipe_" + recipeId + "_step_" + stepIndex + ".png";

                ExecutorService executor = Executors.newSingleThreadExecutor();
                String fileNameStepFinal = fileNameStep;
                // Truyền Bitmap vào Runnable thay vì lấy nó trong run()
                Bitmap finalBitmap = bitmap;
                Runnable saveImageTask = new Runnable() {
                    @Override
                    public void run() {
                        // Lưu ảnh vào bộ nhớ trong
                        saveImageToFile(finalBitmap, UpdateRecipe.this, fileNameStepFinal);
                    }
                };

                // Thực thi công việc lưu ảnh trong background
                executor.execute(saveImageTask);


            }
            Step step = new Step();
            step.setRecipe_id((int) recipeId);
            step.setDetail(stepInput.getText().toString());
            step.setNumber(stepIndex);

            // Lưu tên file vào database, thay vì URL từ Firebase nếu bạn lưu ảnh trong bộ nhớ trong
            step.setImage(fileNameStep);  // Lưu tên file ảnh
//            newSteps.add(step);




            // Gọi phương thức insert vào cơ sở dữ liệu
            db.stepDAO().insertStep(step);

        }

        Toast.makeText(this, "Sửa món ăn thành công", Toast.LENGTH_SHORT).show();
//        Log.d("UpdateRecipe", "Updated recipe with ID: " + recipe.getMainImage());
//
//        Recipe recipe1 = db.recipeDAO().getRecipeById((int) recipeId);
//        Log.d("UpdateRecipe", "Updated recipe with ID: " + recipe1.getMainImage());
        recreate();


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
        setContentView(R.layout.activity_update_recipe);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        recipeId = getIntent().getIntExtra("recipeId", 2);
        bindingView();
        bindingAction();
        initData();

    }

    private void initData() {
        initMainImage();
        initRecipeInfo();
        initIngredient();
        initStep();
        initRecyclerView();
    }



    private void initStep() {
        List<Step> steps = db.stepDAO().getStepsByRecipeId(recipeId);
        if (steps != null && !steps.isEmpty()) {
            for (int i = 0; i < steps.size(); i++) {
                Step step = steps.get(i);

                // Nếu không phải bước đầu tiên, thêm vào giao diện
                if (i > 0) {
                    addNewStepRow(step);
                } else {
                    edtStep1.setText(step.getDetail());
                    if (step != null && step.getImage() != null && !step.getImage().isEmpty()) {
                        loadImageToImageView(step.getImage(), this, btnAddImageStep);
                    }
                }
            }
        }

    }

    private void initIngredient() {
        List<Ingredient> ingredients = db.ingredientDAO().getIngredientsByRecipeId(recipeId);
        if (ingredients != null && !ingredients.isEmpty()) {
            for (int i = 0; i < ingredients.size(); i++) {
                Ingredient ingredient = ingredients.get(i);

                // Nếu không phải nguyên liệu đầu tiên, thêm vào giao diện
                if (i > 0) {
                    addNewIngredientRow(ingredient.getName());
                } else {
                    edtIngre1.setText(ingredient.getName());
                }
            }
        }

    }

    private void initRecipeInfo() {
        edtDishName.setText(updatedRecipe.getTitle());
        edtDescription.setText(updatedRecipe.getDescription());
        edtCookingTime.setText(updatedRecipe.getTime());
    }

    private void initMainImage() {
        loadImageToImageView(updatedRecipe.getMainImage(), this, imgAddDish);
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

    private void addNewIngredientRow(String content) {
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
        if (content != null && !content.trim().isEmpty()) {
            newIngredient.setText(content);
        }
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
                listImgDescBitmap.add(photo);
                initRecyclerView();
//                addImageToDescriptionList(photo);
            } else if (requestCode == REQUEST_GALLERY_DESCRIPTION && data != null) {
                // Lấy ảnh từ thư viện
                Uri selectedImage = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    listImgDescBitmap.add(bitmap);
                    initRecyclerView();
//                    addImageToDescriptionList(bitmap);
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
            selectedImageStepIndex = (int) v.getTag() - countDeleteStep - 1;
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

    private void addNewStepRow(Step step) {
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
        if (step != null) {
            stepInput.setText(step.getDetail()); // Gán nội dung bước
        }
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
        if (step != null && step.getImage() != null && !step.getImage().isEmpty()) {
            loadImageToImageView(step.getImage(), this, addImageButton);
        }
        addImageButton.setOnClickListener(v -> {
            selectedImageStepIndex = (int) v.getTag() - countDeleteStep - 1;
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
        for (int i = 0; i < stepCount; i++) {
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
        for (int i = 0; i < stepCount; i++) {
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

        new Thread(() -> {
            Bitmap resizedBitmap = resizeBitmap(bitmap, maxWidth);

            runOnUiThread(() -> {
                // Tạo ImageView mới
                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(maxWidth, maxWidth);
                params.setMargins(8, 8, 8, 8);
                imageView.setLayoutParams(params);
                imageView.setImageBitmap(resizedBitmap);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                // Thêm vào container
                selectedImagesContainer.addView(imageView);
            });
        }).start();
    }



    private Bitmap resizeBitmap(Bitmap bitmap, int maxWidth) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // Tỷ lệ thu nhỏ
        float scale = (float) maxWidth / width;

        // Tạo bitmap mới với kích thước nhỏ hơn
        return Bitmap.createScaledBitmap(bitmap, maxWidth, (int) (height * scale), true);
    }

    private void uploadImageToFirebase(Uri fileUri, final OnImageUploadCompleteListener listener) {
        if (fileUri != null) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();

            // Tạo một tên file duy nhất cho ảnh
            String fileName = "steps_images/" + UUID.randomUUID().toString();

            // Tham chiếu tới vị trí lưu ảnh trong Firebase Storage
            StorageReference fileRef = storageRef.child(fileName);

            fileRef.putFile(fileUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Khi tải ảnh lên thành công, lấy URL của ảnh
                        fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String downloadUrl = uri.toString();
                            listener.onImageUploadComplete(downloadUrl); // Trả về URL cho listener
                        });
                    })
                    .addOnFailureListener(e -> {
                        Log.e("Firebase", "Failed to upload image", e);
                        listener.onImageUploadComplete(null); // Nếu lỗi thì trả về null
                    });
        }
    }

    public interface OnImageUploadCompleteListener {
        void onImageUploadComplete(String imageUrl);
    }

    private Uri getImageUriFromImageButton(ImageButton imageButton) {
        // Lấy URI từ Tag của ImageButton
        String uriString = (String) imageButton.getTag();
        return uriString != null ? Uri.parse(uriString) : null;
    }

    private Bitmap getBitmapFromImageButton(ImageButton imageButton) {
        Drawable drawable = imageButton.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return null;
    }

    private void saveImageToFile(Bitmap bitmap, Context context, String fileName) {
        ExecutorService executor = Executors.newSingleThreadExecutor();  // Tạo một Executor với một thread đơn
        executor.execute(new Runnable() {
            @Override
            public void run() {
                FileOutputStream fos = null;
                try {
                    // Tạo file trong thư mục riêng của ứng dụng
                    File directory = context.getFilesDir();  // Thư mục này là private của app
                    File file = new File(directory, fileName);  // Tạo file với tên tùy chọn

                    // Mở FileOutputStream để ghi dữ liệu vào file
                    fos = new FileOutputStream(file);

                    // Chuyển Bitmap thành định dạng PNG và lưu vào file
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

                    // Đảm bảo ghi toàn bộ dữ liệu
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private boolean isDefaultImage(ImageButton imageButton) {
        return imageButton.getDrawable().getConstantState().equals(
                getResources().getDrawable(R.drawable.add_image).getConstantState()
        );
    }

    private boolean isDefaultImage(ImageView imageView) {
        return imageView.getDrawable() != null &&
                imageView.getDrawable().getConstantState() != null &&
                imageView.getDrawable().getConstantState().equals(
                        getResources().getDrawable(R.drawable.ic_image_add, null).getConstantState()
                );
    }

    private void initRecyclerView() {
        ImgDescBitmapAdapter adapter = new ImgDescBitmapAdapter(listImgDescBitmap);
        selectedImagesContainer.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        selectedImagesContainer.setLayoutManager(layoutManager);
    }

    private void loadImageToImageView(String fileName, Context context, ImageView imageView) {
        try {
            // Lấy thư mục private của ứng dụng
            File directory = context.getFilesDir();

            // Tạo đường dẫn tới file ảnh
            File file = new File(directory, fileName);

            // Kiểm tra xem file có tồn tại không
            if (file.exists()) {
                // Đọc ảnh từ file và chuyển thành Bitmap
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                // Hiển thị ảnh trên ImageView
                imageView.setImageBitmap(bitmap);
            } else {
                // Nếu file không tồn tại, đặt ảnh mặc định
                imageView.setImageResource(R.drawable.default_recipe); // Đổi R.drawable.default_image thành ảnh mặc định của bạn

            }
        } catch (Exception e) {
            e.printStackTrace();
            // Đặt ảnh mặc định nếu có lỗi
            imageView.setImageResource(R.drawable.default_recipe);
        }
    }


}