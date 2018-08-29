package widyanto.fauzan.roompersistance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActionActivity extends AppCompatActivity {

    @BindView(R.id.edtNama)
    EditText edtNama;
    @BindView(R.id.edtAlamat)
    EditText edtAlamat;
    @BindView(R.id.imageEdit)
    ImageView imageView;
    @BindView(R.id.btnEdit)
    Button btnEdit;
    @BindView(R.id.btnDelete)
    Button btnDelete;

    private SlidrInterface slidrInterface;
    private SiswaModel siswaModel;
    private ArrayList<Image> imageArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        ButterKnife.bind(this);
        int primary = getResources().getColor(R.color.colorPrimary);
        int secondary = getResources().getColor(R.color.colorPrimaryDark);
        slidrInterface = Slidr.attach(this, primary, secondary);

        Bundle bundle = getIntent().getExtras();
        siswaModel = bundle.getParcelable(MainAdapter.EXTRA_SISWA);

        edtNama.setText(siswaModel.getNama());
        edtAlamat.setText(siswaModel.getAlamat());

        Glide.with(this)
                .load(siswaModel.getPathFoto())
                .into(imageView);
    }

    @OnClick(R.id.imageEdit)
    public void onImageViewClicked() {
        ImagePicker.with(this)
                .setCameraOnly(false)
                .setKeepScreenOn(true)
                .setFolderMode(true)
                .setMaxSize(10)
                .setMultipleMode(false)
                .setFolderTitle("Albums")
                .setSelectedImages(imageArrayList)
                .setAlwaysShowDoneButton(true)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            imageArrayList = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            siswaModel.setPathFoto(imageArrayList.get(0).getPath());

            Glide.with(this)
                    .load(imageArrayList.get(0).getPath())
                    .into(imageView);
        }
    }

    @OnClick(R.id.btnEdit)
    public void onBtnEditClicked() {
        siswaModel.setNama(edtNama.getText().toString());
        siswaModel.setAlamat(edtAlamat.getText().toString());
        siswaModel.setPathFoto(siswaModel.getPathFoto());

        SiswaApp.db.userDao().update(siswaModel);
        Intent i = new Intent(ActionActivity.this, MainActivity.class);
        i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
        i.setFlags(i.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @OnClick(R.id.btnDelete)
    public void onBtnDeleteClicked() {
        SiswaApp.db.userDao().deleteUsers(siswaModel);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
