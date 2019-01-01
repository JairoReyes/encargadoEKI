package local.isaac.tt_2018_a031.controller;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;


import local.isaac.tt_2018_a031.Inicio;
import local.isaac.tt_2018_a031.R;
import local.isaac.tt_2018_a031.Registros;
import local.isaac.tt_2018_a031.SplashActivity;

import android.content.SharedPreferences;

@Layout(R.layout.drawer_item)
public class DrawerMenuItem {

    public static final int DRAWER_MENU_ITEM_REGISTROS = 1;
    /*public static final int DRAWER_MENU_ITEM_CONTACTOS = 2;
    public static final int DRAWER_MENU_ITEM_ACERCA = 3;
    /*
    public static final int DRAWER_MENU_ITEM_MESSAGE = 4;
    public static final int DRAWER_MENU_ITEM_NOTIFICATIONS = 5;
    public static final int DRAWER_MENU_ITEM_SETTINGS = 6;
    public static final int DRAWER_MENU_ITEM_TERMS = 7;
    */
    public static final int DRAWER_MENU_ITEM_LOGOUT = 8;

    private int mMenuPosition;
    private Context mContext;
    private DrawerCallBack mCallBack;

    @View(R.id.itemNameTxt)
    private TextView itemNameTxt;

    @View(R.id.itemIcon)
    private ImageView itemIcon;

    public DrawerMenuItem(Context context, int menuPosition) {
        mContext = context;
        mMenuPosition = menuPosition;
    }

    @Resolve
    private void onResolved() {
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_REGISTROS:
                itemNameTxt.setText("Registros");
                itemIcon.setImageDrawable(mContext.getDrawable(R.drawable.ic_trolelogogris));
                break;
            /*case DRAWER_MENU_ITEM_CONTACTOS:
                itemNameTxt.setText("Contactos de emergencia");
                itemIcon.setImageDrawable(mContext.getDrawable(R.drawable.baseline_contacts_24));
                break;
            /*case DRAWER_MENU_ITEM_GROUPS:
                itemNameTxt.setText("Groups");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_group_work_black_18dp));
                break;
            case DRAWER_MENU_ITEM_MESSAGE:
                itemNameTxt.setText("Messages");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_email_black_18dp));
                break;
            case DRAWER_MENU_ITEM_NOTIFICATIONS:
                itemNameTxt.setText("Notifications");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_notifications_black_18dp));
                break;
            case DRAWER_MENU_ITEM_SETTINGS:
                itemNameTxt.setText("Settings");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_settings_black_18dp));
                break;
            case DRAWER_MENU_ITEM_TERMS:
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_book_black_18dp));
                itemNameTxt.setText("Terms");
                break;*/
            case DRAWER_MENU_ITEM_LOGOUT:
                itemIcon.setImageDrawable(mContext.getDrawable(R.drawable.baseline_exit_to_app_24));
                itemNameTxt.setText("Cerrar sesión");
                break;
        }
    }

    @Click(R.id.mainView)
    private void onMenuItemClick(){
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_REGISTROS:
                Intent intAdd= new Intent(mContext,Registros.class);
                mContext.startActivity(intAdd);
                if(mCallBack != null)mCallBack.onRequestMenuSelected();
                break;
            /*case DRAWER_MENU_ITEM_CONTACTOS:
                //Toast.makeText(mContext, "Contactos de emergencia", Toast.LENGTH_SHORT).show();
                Intent intAdd= new Intent("tt-2018_a031.listaContacts");
                mContext.startActivity(intAdd);
                if(mCallBack != null)mCallBack.onRequestMenuSelected();
                break;
            /*case DRAWER_MENU_ITEM_GROUPS:
                Toast.makeText(mContext, "Groups", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onGroupsMenuSelected();
                break;
            case DRAWER_MENU_ITEM_MESSAGE:
                Toast.makeText(mContext, "Messages", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onMessagesMenuSelected();
                break;
            case DRAWER_MENU_ITEM_NOTIFICATIONS:
                Toast.makeText(mContext, "Notifications", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onNotificationsMenuSelected();
                break;
            case DRAWER_MENU_ITEM_SETTINGS:
                Toast.makeText(mContext, "Settings", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onSettingsMenuSelected();
                break;
            case DRAWER_MENU_ITEM_TERMS:
                Toast.makeText(mContext, "Terms", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onTermsMenuSelected();
                break;*/
            case DRAWER_MENU_ITEM_LOGOUT:
                Toast.makeText(mContext, "Sesión terminada", Toast.LENGTH_SHORT).show();
                SharedPreferences pref = mContext.getSharedPreferences(Inicio.preferencias, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                if(mCallBack != null)mCallBack.onLogoutMenuSelected();
                Intent intent = new Intent(mContext, SplashActivity.class);
                mContext.startActivity(intent);
                break;
        }
    }

    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }

    public interface DrawerCallBack{
        void onProfileMenuSelected();
        void onRequestMenuSelected();
        /*void onGroupsMenuSelected();
        void onMessagesMenuSelected();
        void onNotificationsMenuSelected();
        void onSettingsMenuSelected();
        void onTermsMenuSelected();*/
        void onLogoutMenuSelected();
    }

}
