package local.isaac.tt_2018_a031.controller;

import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import org.w3c.dom.Text;

import local.isaac.tt_2018_a031.R;

@NonReusable
@Layout(R.layout.drawer_header)
public class DrawerHeader {

    public DrawerHeader(String nombreUsuario,String correoUsuario){
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
    }

    private String nombreUsuario;
    private String correoUsuario;

    @View(R.id.profileImageView)
    private ImageView profileImage;

    @View(R.id.nameTxt)
    private TextView nombreUsuarioTextView;

    @View(R.id.emailTxt)
    private TextView correoUsuarioTextView;

    @Resolve
    private void onResolved(){
        nombreUsuarioTextView.setText(this.correoUsuario);
        correoUsuarioTextView.setText(this.nombreUsuario);
    }

}
