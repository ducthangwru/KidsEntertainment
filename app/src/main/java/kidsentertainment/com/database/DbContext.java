package kidsentertainment.com.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



import java.util.HashMap;

import kidsentertainment.com.network.models.ListVideoResponse;
import kidsentertainment.com.network.models.LoginResponse;
import kidsentertainment.com.network.models.TitleResponse;

/**
 * Created by hanhi on 11/22/2017.
 */

public class DbContext {
    private static final String Pref_String_DB = "DbContext";
    private static SharedPreferences DBaboutRespon, DBloginResponse, DBcontactResponse, DBlistContactTodaName;
    private SharedPreferences.Editor DBaboutResponEditor, DBloginResponseEditor, DBcontactResponseEditor, DBlistContactTodaNameEditor;
    private Context context;
    private static final DbContext instance = new DbContext();
    private LoginResponse loginResponse;
    private ListVideoResponse listVideoResponse;
    private TitleResponse titleResponse;

    public DbContext() {

    }

    public static String getPref_String_DB() {
        return Pref_String_DB;
    }

    public static SharedPreferences getDBaboutRespon() {
        return DBaboutRespon;
    }

    public static void setDBaboutRespon(SharedPreferences DBaboutRespon) {
        DbContext.DBaboutRespon = DBaboutRespon;
    }

    public static SharedPreferences getDBloginResponse() {
        return DBloginResponse;
    }

    public static void setDBloginResponse(SharedPreferences DBloginResponse) {
        DbContext.DBloginResponse = DBloginResponse;
    }

    public static SharedPreferences getDBcontactResponse() {
        return DBcontactResponse;
    }

    public static void setDBcontactResponse(SharedPreferences DBcontactResponse) {
        DbContext.DBcontactResponse = DBcontactResponse;
    }

    public static SharedPreferences getDBlistContactTodaName() {
        return DBlistContactTodaName;
    }

    public static void setDBlistContactTodaName(SharedPreferences DBlistContactTodaName) {
        DbContext.DBlistContactTodaName = DBlistContactTodaName;
    }

    public SharedPreferences.Editor getDBaboutResponEditor() {
        return DBaboutResponEditor;
    }

    public void setDBaboutResponEditor(SharedPreferences.Editor DBaboutResponEditor) {
        this.DBaboutResponEditor = DBaboutResponEditor;
    }

    public SharedPreferences.Editor getDBloginResponseEditor() {
        return DBloginResponseEditor;
    }

    public void setDBloginResponseEditor(SharedPreferences.Editor DBloginResponseEditor) {
        this.DBloginResponseEditor = DBloginResponseEditor;
    }

    public SharedPreferences.Editor getDBcontactResponseEditor() {
        return DBcontactResponseEditor;
    }

    public void setDBcontactResponseEditor(SharedPreferences.Editor DBcontactResponseEditor) {
        this.DBcontactResponseEditor = DBcontactResponseEditor;
    }

    public SharedPreferences.Editor getDBlistContactTodaNameEditor() {
        return DBlistContactTodaNameEditor;
    }

    public void setDBlistContactTodaNameEditor(SharedPreferences.Editor DBlistContactTodaNameEditor) {
        this.DBlistContactTodaNameEditor = DBlistContactTodaNameEditor;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static DbContext getInstance() {
        return instance;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public ListVideoResponse getListVideoResponse() {
        return listVideoResponse;
    }

    public void setListVideoResponse(ListVideoResponse listVideoResponse) {
        this.listVideoResponse = listVideoResponse;
    }

    public TitleResponse getTitleResponse() {
        return titleResponse;
    }

    public void setTitleResponse(TitleResponse titleResponse) {
        this.titleResponse = titleResponse;
    }
}
