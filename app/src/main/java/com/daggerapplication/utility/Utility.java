package com.daggerapplication.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

import static android.os.Environment.MEDIA_MOUNTED;

public class Utility {

    private static final String EMPTY_REGEX = "^(?=\\s*\\S).*$";
    private static final String USER_NAME = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
    private static final String EMAIL_REGEX = "^([0-9a-zA-Z]([-\\.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
    private static final String MIN_TWO_REGEX = "^.{2,}$";
    private static final String MIN_SIX_REGEX = "^.{8,}$";
    private static final String MIN_SEVEN_REGEX = "^.{7,}$";
    private static final String MAX_TWELVE_REGEX = "^.{,12}$";
    private static final String MIN_SIXTEEN_REGEX = "^[0-9]{16,}$";
    private static final String PHONE_REGEX = "^[+#*\\(\\)\\[\\]]*([0-9][ ext+-pw#*\\(\\)\\[\\]]*){7,16}$";
    private static final Pattern ALPHANUMERIC_PSWD_REGEX = Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            // "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$");
//    private static AlertDialog mAlertDialog;



    /*public static void showAlertDialog(Context mContext, String text) {
        if (text == null)
            text = "";
        AlertDialog mAlertDialog = new AlertDialog.Builder(mContext, android.app.AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).
                setMessage(text)
                .setTitle(mContext.getString(R.string.app_name)).setCancelable(false)
                .setPositiveButton(mContext.getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        *//*mContext.startActivity(new Intent(mContext,SubscritionFragment.class));
                        ((SignUpPicActivity) mContext).finish();*//*
                        mContext.startActivity(
                                new Intent(
                                        mContext,
                                        LoginActivity.class
                                ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        dialog.dismiss();
                    }
                }).create();

        mAlertDialog.show();
        mAlertDialog.show();
        Button button = mAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setTextColor(mContext.getResources().getColor(R.color.view_all_color));
    }*/

    /*public static void showOKAlertDialog(Context mContext, String text) {
        if (text == null)
            text = "";
        View dialogView = View.inflate(mContext, R.layout.dialog_ok, null);
        AlertDialog mAlertDialog = new AlertDialog.Builder(mContext)
                .setView(dialogView)
                .create();
        mAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mAlertDialog.show();
        TextView txt_title = (TextView) dialogView.findViewById(R.id.txt_title);
        ImageView close_img = (ImageView) dialogView.findViewById(R.id.close_img);
        TextView btn_yes = (TextView) dialogView.findViewById(R.id.btn_yes);
        TextView btn_no = (TextView) dialogView.findViewById(R.id.btn_no);

        String sourceString =  "<b>" + text + "</b> ";
        txt_title.setText(Html.fromHtml(sourceString));

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
                ((Activity) mContext).finish();
            }
        });
        close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
                ((Activity) mContext).finish();
            }
        });
    }*/

    /*public static void showOKWothoutCloseAlertDialog(Context mContext, String text) {
        if (text == null)
            text = "";
        View dialogView = View.inflate(mContext, R.layout.dialog_ok, null);
        AlertDialog mAlertDialog = new AlertDialog.Builder(mContext)
                .setView(dialogView)
                .create();
        mAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mAlertDialog.show();
        TextView txt_title = (TextView) dialogView.findViewById(R.id.txt_title);
        ImageView close_img = (ImageView) dialogView.findViewById(R.id.close_img);
        TextView btn_yes = (TextView) dialogView.findViewById(R.id.btn_yes);
        TextView btn_no = (TextView) dialogView.findViewById(R.id.btn_no);

        String sourceString =  "<b>" + text + "</b> ";
        txt_title.setText(Html.fromHtml(sourceString));

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
            }
        });
        close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
            }
        });
    }*/

    /*public static void showProgressDialog(Context mContext, String text) {
        if (text == null)
            text = "";
        View dialogView = View.inflate(mContext, R.layout.dialog_progress, null);
        AlertDialog mAlertDialog = new AlertDialog.Builder(mContext)
                .setView(dialogView)
                .create();
        mAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mAlertDialog.show();
        TextView txt_title = (TextView) dialogView.findViewById(R.id.txt_title);
        ImageView close_img = (ImageView) dialogView.findViewById(R.id.close_img);

        String sourceString =  "<b>" + text + "</b> ";
        txt_title.setText(Html.fromHtml(sourceString));

        close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
            }
        });
    }*/


    /*public static void showDeleteAlertDialog(Context mContext, String text){
        if (text == null)
            text = "";
        View dialogView = View.inflate(mContext, R.layout.dialog, null);
        AlertDialog mAlertDialog = new AlertDialog.Builder(mContext)
                .setView(dialogView)
                .create();
        mAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mAlertDialog.show();
        TextView txt_title = (TextView) dialogView.findViewById(R.id.txt_title);
        ImageView close_img = (ImageView) dialogView.findViewById(R.id.close_img);
        TextView btn_yes = (TextView) dialogView.findViewById(R.id.btn_yes);
        TextView btn_no = (TextView) dialogView.findViewById(R.id.btn_no);

        String sourceString = "Are you sure you want to remove "+ "<b>" + text + "</b> " + " from your connection";
        txt_title.setText(Html.fromHtml(sourceString));

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
            }
        });
        close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAlertDialog.dismiss();
            }
        });
    }*/


    /*public static void showLogoutAlertDialog(Context mContext, String text) {
        if (text == null)
            text = "";
        AlertDialog mAlertDialog = new AlertDialog.Builder(mContext, android.app.AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).setMessage(text)
                .setTitle(mContext.getString(R.string.app_name)).setCancelable(false)
                .setPositiveButton(mContext.getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AppUtilities().startHome(mContext, LoginScreen.class);
                        dialog.dismiss();
                    }
                }).create();

        mAlertDialog.show();
        Button button = mAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
    }



    public static void showLogoutAlertDialogClick(Context mContext, String text , MainActivityVM mViewModel) {
        if (text == null)
            text = "";
        AlertDialog mAlertDialog = new AlertDialog.Builder(mContext, android.app.AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).setMessage(text)
                .setTitle(mContext.getString(R.string.app_name)).setCancelable(false)
                .setPositiveButton(mContext.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton(mContext.getString(R.string.logout), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mViewModel.logoutUser();
                        dialog.dismiss();
                    }
                }).create();
        mAlertDialog.show();
        Button button = mAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button button1 = mAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        button1.setTextColor(mContext.getResources().getColor(R.color.blue_color_logout));
        button.setTextColor(mContext.getResources().getColor(R.color.blue_color_logout));
        button1.setAllCaps(false);
        button.setAllCaps(false);
    }

    public static void showUserStatusDialog(Context mContext, String text) {
        PreferenceManager mPrefsManager=new PreferenceManager(mContext);
        if (text == null)
            text = "";
        AlertDialog mAlertDialog = new AlertDialog.Builder(mContext, android.app.AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).setMessage(text)
                .setTitle(mContext.getString(R.string.app_name)).setCancelable(false)
                .setPositiveButton(mContext.getString(R.string.contact_to_admin), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/html");
                        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"vip@mobilewallet.com"});
                        i.putExtra(Intent.EXTRA_SUBJECT, "Account Suspended Query "+"("+mPrefsManager.getName()+")");
                       // i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                        i.setPackage("com.google.android.gm");
                        try {

                            new AppUtilities().startHome(mContext,LoginScreen.class);
                            mContext.startActivity(Intent.createChooser(i, "Send mail..."));

                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(mContext, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton(mContext.getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AppUtilities().startHome(mContext, LoginScreen.class);
                        dialog.dismiss();
                    }
                }).create();

        mAlertDialog.show();
//        mAlertDialog.show();
        Button button = mAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button button1 = mAlertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        button1.setAllCaps(false);
        button.setAllCaps(false);
        button.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
    }
*/

    @Deprecated
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED || connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTING || connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTING;
    }

    /**
     * Static method to check network availability
     *
     * @param context Context of the calling class
     */

    public static boolean getNetworkState(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }


    public static void sendSms(Context mContext, String phoneNumber) {
        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber)));
    }


    public static void sendEmail(Context mContext, String email) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
        mContext.startActivity(Intent.createChooser(emailIntent, "Send Email..."));
    }


/*
    public static boolean validateEmailFields(EditText editText, Context context, int messageId) {

        String expression = EMAIL_REGEX;
        Pattern p = Pattern.compile(expression, Pattern.CASE_INSENSITIVE); // pattern=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;

        Matcher m = p.matcher(editText.getText());
        if (m.matches() && !editText.getText().toString().isEmpty()) {
            return true;
        } else {
            showAlertDialog(context, context.getResources().getString(messageId));

            return false;
        }
    }

    */

    /**
     * Method to check two edit text of same text
     * <p>
     * //     * @param editText  First object of edittext
     * //     * @param context   Context of the calling activity
     * //     * @param messageId Resource id of the string to show if both doesn't match
     *
     * @return status of the matching
     *//*

    public static boolean validateInputFields(EditText editText, Context context, int messageId) {
        // boolean status = false;
        if (editText.getText() != null && isFieldEmpty(editText.getText().toString().trim())) {
            showAlertDialog(context, context.getResources().getString(messageId));
            return false;
        } else {
            return true;
        }
    }


    public static boolean validateInputFields(String text, Context context, int messageId) {
        if (text != null && isFieldEmpty(text)) {
            showAlertDialog(context, context.getResources().getString(messageId));
            return false;
        } else {
            return true;
        }
    }


    public static boolean validateMinNumberFieldLength(CharSequence editTextString,
                                                       Context context, int messageId,
                                                       int lengthMin) {

        if (editTextString.toString().trim().length() > lengthMin) {
            return true;
        } else {
            showAlertDialog(context, context.getResources().getString(messageId));
            return false;
        }
    }


    public static boolean validatePasswordSameFields(EditText password, EditText confPassword) {
        // boolean status = false;
        return password.getText().toString().equals(confPassword.getText().toString());
        // return status;
    }

    public static boolean isFieldEmpty(String field) {
        return field.trim().length() <= 0;
    }


    public static void generateFBKeyHash(Context mContext) {
        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getStateLocationFromLocation(Context mContext, double latitude, double longitude)

            throws IOException {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(mContext, Locale.getDefault());

        addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();
        return state;
    }

    public static String getCityStateLocationFromLocation(Context mContext, double latitude, double longitude)
            throws IOException {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(mContext, Locale.getDefault());

        addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();
        return city + ", " + state;
    }
*/
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void hideKeyboard(Context mContext) {
        try {
            InputMethodManager inputManager = (InputMethodManager) mContext
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus().getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void hideKeyboard(Context context, View myEditText) {
        hideKeyboard(context);
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }


    public static String parseDateTimeUtc(String date, String sourceFormat, String targetFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(sourceFormat, Locale.US);
            Date strDate = sdf.parse(date);
            SimpleDateFormat sdf2 = new SimpleDateFormat(targetFormat.trim(), Locale.US);
            return sdf2.format(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }


    private static int getTimeDistanceInMinutes(long time) {
        long timeDistance = currentDate().getTime() - time;
        return Math.round((Math.abs(timeDistance) / 1000) / 60);
    }

    public static Date currentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        return calendar.getTime();
    }


    public static Uri createImageFile(Context mContext) throws IOException {

        File image;

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            image = File.createTempFile(imageFileName, /* prefix */
                    ".jpg", /* suffix */
                    storageDir /* directory */
            );

        } else {

            File storageDir = mContext.getFilesDir();
            image = File.createTempFile(imageFileName, /* prefix */
                    ".jpg", /* suffix */
                    storageDir /* directory */
            );

        }

        return Uri.fromFile(image);
    }


    public static String validateEmptyFieldRegex() {
        return EMPTY_REGEX;
    }

    public static String validateForgotEmptyFieldRegex() {
        return EMPTY_REGEX;
    }

    public static String validateUserNameRegex() {
        return USER_NAME;
    }

    public static String validateEmailFieldRegex() {
        return EMAIL_REGEX;
    }

    public static String validateForgotEmailFieldRegex() {
        return EMAIL_REGEX;
    }

    public static String validatePhoneFieldRegex() {
        return PHONE_REGEX;
    }

    public static String validatePhoneMinNumber() {
        return MIN_SEVEN_REGEX;
    }

    public static String validatePhoneMaxNumber() {
        return MAX_TWELVE_REGEX;
    }

    public static String validateMinSixLengthRegex() {
        return MIN_SIX_REGEX;
    }

    public static Pattern validateAlphanumericPswdRegex() {
        return ALPHANUMERIC_PSWD_REGEX;
    }

    public static String validateMinSixteenLengthRegex() {
        return MIN_SIXTEEN_REGEX;
    }


    public static boolean checkGPS(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    public static String getFormatedName(String str) {
        boolean prevWasWhiteSp = true;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])) {
                if (prevWasWhiteSp) {
                    chars[i] = Character.toUpperCase(chars[i]);
                }
                prevWasWhiteSp = false;
            } else {
                prevWasWhiteSp = Character.isWhitespace(chars[i]);
            }
        }
        return new String(chars);
    }


    public static void hideKeyboardOnClickingScreen(View view, final Context context) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                @RequiresApi(api = Build.VERSION_CODES.M)
                public boolean onTouch(View v, MotionEvent event) {
                    Utility.hideKeyboard(context);
                    Utility.hideKeyboard(context, v);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                hideKeyboardOnClickingScreen(innerView, context);
            }
        }
    }

    /*public static boolean verificationCode(String text, Context context) {
        if (text.length() < 6 || text.length() > 6) {
            Toast.makeText(context, context.getResources().getString(R.string.enter_valid_code), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static Snackbar showNoNetworkSnackBar(@NonNull View anyView) {
        return showSnackBar(anyView, anyView.getResources().getString(R.string.no_internet_connection));
    }*/

    public static Snackbar showSnackBar(View anyView, String msg) {
        final Snackbar snackBar = Snackbar.make(anyView, msg, Snackbar.LENGTH_LONG);
        snackBar.setActionTextColor(Color.WHITE);
        snackBar.show();
        return snackBar;
    }

    /*public static void showNoNetworkToast(Context context) {
        showToast(context, context.getResources().getString(R.string.no_internet_connection));
    }*/

    public static void showToast(@NonNull Context context, String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static String dateConverter(String getDate) {
        String finaleDate = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date newDate = format.parse(getDate);
            format = new SimpleDateFormat("d");
            String date = format.format(newDate);

            if (date.endsWith("1") && !date.endsWith("11"))
                format = new SimpleDateFormat("d'st' MMMM, yyyy");
            else if (date.endsWith("2") && !date.endsWith("12"))
                format = new SimpleDateFormat("d'nd' MMMM, yyyy");
            else if (date.endsWith("3") && !date.endsWith("13"))
                format = new SimpleDateFormat("d'rd' MMMM, yyyy");
            else
                format = new SimpleDateFormat("d'th' MMMM, yyyy");

            finaleDate = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finaleDate;
    }

    public static String DefaultDateConvert(String getDate) {
        String finaleDate = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date newDate = format.parse(getDate);
            format = new SimpleDateFormat("d");
            String date = format.format(newDate);
            format = new SimpleDateFormat("dd/MM/yyyy");

            finaleDate = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finaleDate;
    }


    public static String defaultDateTimeConvert(String getDate) {
        String finaleDate = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date newDate = format.parse(getDate);
            format = new SimpleDateFormat("d");
            String date = format.format(newDate);
            format = new SimpleDateFormat("dd/MM/yyyy | hh:mm a");

            finaleDate = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finaleDate;
    }

    public static String transactionDateTimeConvert(String getDate) {
        String finaleDate = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date newDate = format.parse(getDate);
            format = new SimpleDateFormat("d");
            String date = format.format(newDate);
            format = new SimpleDateFormat("dd/MM/yyyy \n hh:mm a");

            finaleDate = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finaleDate;
    }

    public static String DefaultTimeConvert(String getDate) {
        String finaleDate = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date newDate = format.parse(getDate);
            format = new SimpleDateFormat("d");
            String date = format.format(newDate);
            format = new SimpleDateFormat("hh:mm a");

            finaleDate = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finaleDate;
    }

    public String parseDateToddMMyyyy(String getDate) {
        String finaleDate = "";
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date newDate = format.parse(getDate);
            format = new SimpleDateFormat("d");
            String date = format.format(newDate);

            format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            finaleDate = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finaleDate;
    }

    public String parseUserProfiledateddMMyyyy(String getDate) {
        String finaleDate = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date newDate = format.parse(getDate);
            format = new SimpleDateFormat("d");
            String date = format.format(newDate);

            format = new SimpleDateFormat("dd-MM-yyyy");

            finaleDate = format.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finaleDate;
    }

    public static String sImageName = "";
    public static String sDirectory = "";
    public static String sRawDirectory = "";


    public static String getsDirectory() {
        return sDirectory;
    }

    public static String getsRawDirectory() {
        return sRawDirectory;
    }

    public static String getsImageName() {
        return sImageName;
    }


    public String getAddress(Context context, double latitude, double longitude) {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                // result.append(address.getLocality()).append("\n");
                result.append(address.getCountryName());
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();
    }


    /*public static String getCountryCode(String countryName) {
        List<CCPCountry> isoCountryCodes = CCPCountry.getLoadedLibraryMaterList();
        for (int i = 0; i < isoCountryCodes.size(); i++) {
            if (countryName.equalsIgnoreCase(isoCountryCodes.get(i).getName())) {
                return isoCountryCodes.get(i).getNameCode();
            }
        }
        return "";
    }

    public static String getCountryIsoCode(String countryName) {
        String countryCode = getPhoneCode(countryName);
        List<CCPCountry> isoCountryCodes = CCPCountry.getLibraryMasterCountriesEnglish();
        for (int i = 0; i < isoCountryCodes.size(); i++) {
            if (countryCode.equalsIgnoreCase(isoCountryCodes.get(i).getPhoneCode())) {
                return isoCountryCodes.get(i).getIsoCode();
            }
        }
        return "";
    }

    public static String getPhoneCode(String countryName) {
        List<CCPCountry> isoCountryCodes = CCPCountry.getLoadedLibraryMaterList();
        for (int i = 0; i < isoCountryCodes.size(); i++) {
            if (countryName.equalsIgnoreCase(isoCountryCodes.get(i).getName())) {
                return isoCountryCodes.get(i).getPhoneCode();
            }
        }
        return "";
    }*/

}