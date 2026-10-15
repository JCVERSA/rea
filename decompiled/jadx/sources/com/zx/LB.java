package com.zx;

import android.annotation.NonNull;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.zx.cmz.Base64Image;
import com.zx.cmz.Colors;
import com.zx.cmz.Strings;
import com.zx.cmz.dimens;

/* JADX INFO: loaded from: classes5.dex */
public class LB extends DialogFragment {
    private boolean dontShowAgainChecked = false;

    private int convertDpToPx(int i) {
        return Math.round(getResources().getDisplayMetrics().density * i);
    }

    private Button createButton(String str, View.OnClickListener onClickListener) {
        Button button = new Button(getActivity());
        button.setText(str);
        button.setTextSize(2, dimens.getButtonTextSize());
        button.setTextColor(Color.parseColor(Colors.getButtonTextColor()));
        button.setTypeface(Typeface.create("sans-serif-medium", 1));
        button.setBackground(createButtonBackground());
        button.setOnClickListener(onClickListener);
        return button;
    }

    private Drawable createButtonBackground() {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Color.parseColor(Colors.getButtonBgTopColor()), Color.parseColor(Colors.getButtonBgBottomColor())});
        gradientDrawable.setCornerRadius(convertDpToPx(dimens.getButtonCornerRadius()));
        return gradientDrawable;
    }

    private View createCustomTitle() {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        int iConvertDpToPx = convertDpToPx(dimens.getTitlePadding());
        linearLayout.setPadding(iConvertDpToPx, iConvertDpToPx, iConvertDpToPx, iConvertDpToPx);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{Color.parseColor(Colors.getTitleBgLeftColor()), Color.parseColor(Colors.getTitleBgRightColor())});
        gradientDrawable.setCornerRadius(convertDpToPx(dimens.getCornerRadius()));
        gradientDrawable.setStroke(convertDpToPx(dimens.getStrokeSize()), Color.parseColor(Colors.getStrokeColor()));
        linearLayout.setBackground(gradientDrawable);
        ImageView imageView = new ImageView(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(convertDpToPx(dimens.getIconSize()), convertDpToPx(dimens.getIconSize()));
        int iConvertDpToPx2 = convertDpToPx(dimens.getIconMargin());
        layoutParams.setMargins(iConvertDpToPx2, iConvertDpToPx2, iConvertDpToPx2, iConvertDpToPx2);
        imageView.setLayoutParams(layoutParams);
        try {
            imageView.setImageBitmap(getCircularBitmapWithBorder(decodeBase64Image(Base64Image.getBase64Image()), convertDpToPx(dimens.getIconBorder()), Color.parseColor(Colors.getIconStrokeColor())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        linearLayout.addView(imageView);
        View view = new View(getActivity());
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(convertDpToPx(dimens.getSeparatorSize()), -1);
        layoutParams2.setMargins(iConvertDpToPx2, iConvertDpToPx2, iConvertDpToPx2, iConvertDpToPx2);
        view.setLayoutParams(layoutParams2);
        view.setBackgroundColor(Color.parseColor(Colors.getSeparatorColor()));
        linearLayout.addView(view);
        LinearLayout linearLayout2 = new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(iConvertDpToPx2, iConvertDpToPx2, iConvertDpToPx2, iConvertDpToPx2);
        linearLayout2.setLayoutParams(layoutParams3);
        linearLayout2.setOrientation(1);
        TextView textView = new TextView(getActivity());
        textView.setText(Strings.getTitle());
        textView.setGravity(17);
        textView.setTextSize(2, dimens.getTitleTextSize());
        textView.setTextColor(Color.parseColor(Colors.getTitleTextColor()));
        textView.setTypeface(Typeface.create("sans-serif-medium", 1));
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(Color.parseColor(Colors.getTitleBoxBgColor()));
        gradientDrawable2.setCornerRadius(convertDpToPx(dimens.getCornerRadius()));
        gradientDrawable2.setStroke(convertDpToPx(dimens.getStrokeSize()), Color.parseColor(Colors.getStrokeColor()));
        textView.setPadding(convertDpToPx(5), convertDpToPx(5), convertDpToPx(5), convertDpToPx(5));
        textView.setBackground(gradientDrawable2);
        TextView textView2 = new TextView(getActivity());
        textView2.setText(Strings.getSubtitle());
        textView2.setTextSize(2, dimens.getSubtitleTextSize());
        textView2.setTextColor(Color.parseColor(Colors.getSubtitleTextColor()));
        textView2.setTypeface(Typeface.create("sans-serif-light", 2));
        linearLayout2.addView(textView);
        linearLayout2.addView(textView2);
        linearLayout.addView(linearLayout2);
        return linearLayout;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Drawable createGradientBackground() {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Color.parseColor(Colors.getDialogBgTopColor()), Color.parseColor(Colors.getDialogBgBottomColor())});
        gradientDrawable.setCornerRadius(convertDpToPx(dimens.getCornerRadius()));
        return gradientDrawable;
    }

    private Drawable createRoundedCornerDrawable(String str) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor(str));
        gradientDrawable.setCornerRadius(convertDpToPx(dimens.getCornerRadius()));
        return gradientDrawable;
    }

    private Bitmap decodeBase64Image(String str) {
        byte[] bArrDecode = Base64.decode(str, 0);
        return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
    }

    private Bitmap getCircularBitmapWithBorder(Bitmap bitmap, int i, int i2) {
        int iMin = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, iMin, iMin);
        float f = iMin / 2.0f;
        canvas.drawOval(new RectF(rect), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, (Rect) null, rect, paint);
        paint.setXfermode((Xfermode) null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i2);
        paint.setStrokeWidth(i);
        canvas.drawCircle(f, f, f - (i / 2.0f), paint);
        return bitmapCreateBitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSwitchPreference() {
        if (this.dontShowAgainChecked) {
            getActivity().getSharedPreferences("MyPreferences", 0).edit().putBoolean("show_dialog", false).apply();
        }
    }

    public static void ll(Activity activity) {
        if (activity.getSharedPreferences("MyPreferences", 0).getBoolean("show_dialog", true)) {
            new LB().show(activity.getFragmentManager(), "custom_dialog");
        }
    }

    private Drawable messageGradientBackground() {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Color.parseColor(Colors.getMessageBgTopColor()), Color.parseColor(Colors.getMessageBgBottomColor())});
        gradientDrawable.setCornerRadius(convertDpToPx(dimens.getCornerRadius()));
        gradientDrawable.setStroke(convertDpToPx(dimens.getStrokeSize()), Color.parseColor(Colors.getStrokeColor()));
        return gradientDrawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openURL(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void styleSwitch(Switch r5, boolean z) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(convertDpToPx(dimens.getSwitchCornerRadius()));
        if (z) {
            r5.getThumbDrawable().setColorFilter(Color.parseColor(Colors.getSwitchCheckedThumbColor()), PorterDuff.Mode.MULTIPLY);
            gradientDrawable.setColor(Color.parseColor(Colors.getSwitchCheckedTrackColor()));
        } else {
            r5.getThumbDrawable().setColorFilter(Color.parseColor(Colors.getSwitchThumbColor()), PorterDuff.Mode.MULTIPLY);
            gradientDrawable.setColor(Color.parseColor(Colors.getSwitchTrackColor()));
        }
        r5.setTrackDrawable(gradientDrawable);
    }

    @Override // android.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(1);
        linearLayout.setBackground(createGradientBackground());
        int iConvertDpToPx = convertDpToPx(dimens.getPadding());
        linearLayout.setPadding(iConvertDpToPx, iConvertDpToPx, iConvertDpToPx, convertDpToPx(4));
        View viewCreateCustomTitle = createCustomTitle();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(iConvertDpToPx, iConvertDpToPx, iConvertDpToPx, 0);
        viewCreateCustomTitle.setLayoutParams(layoutParams);
        linearLayout.addView(viewCreateCustomTitle);
        LinearLayout linearLayout2 = new LinearLayout(getActivity());
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(17);
        linearLayout2.setBackground(messageGradientBackground());
        linearLayout2.setPadding(convertDpToPx(dimens.getMessagePadding()), convertDpToPx(dimens.getMessagePadding()), convertDpToPx(dimens.getMessagePadding()), convertDpToPx(dimens.getMessagePadding()));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(iConvertDpToPx, convertDpToPx(8), iConvertDpToPx, 0);
        linearLayout2.setLayoutParams(layoutParams2);
        TextView textView = new TextView(getActivity());
        textView.setText(Strings.getMessage());
        textView.setTextSize(2, dimens.getMessageTextSize());
        textView.setTextColor(Color.parseColor(Colors.getMessageTextColor()));
        textView.setGravity(17);
        textView.setTypeface(Typeface.create("sans-serif-light", 1));
        linearLayout2.addView(textView);
        linearLayout.addView(linearLayout2);
        LinearLayout linearLayout3 = new LinearLayout(getActivity());
        linearLayout3.setOrientation(0);
        linearLayout3.setGravity(16);
        linearLayout3.setPadding(convertDpToPx(dimens.getSwitchPadding()), convertDpToPx(dimens.getSwitchPadding()), convertDpToPx(dimens.getSwitchPadding()), convertDpToPx(dimens.getSwitchPadding()));
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.setMargins(iConvertDpToPx, convertDpToPx(16), iConvertDpToPx, 0);
        linearLayout3.setLayoutParams(layoutParams3);
        linearLayout3.setBackground(createRoundedCornerDrawable(Colors.getSwitchBgColor()));
        Switch r4 = new Switch(getActivity());
        r4.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        r4.setContentDescription(" ");
        styleSwitch(r4, false);
        linearLayout3.addView(r4);
        TextView textView2 = new TextView(getActivity());
        textView2.setText(Strings.getSwitch());
        textView2.setTextColor(Color.parseColor(Colors.getSwitchTextColor()));
        textView2.setTextSize(2, dimens.getSwitchTextSize());
        textView2.setTypeface(Typeface.create("sans-serif-light", 0));
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams4.setMargins(convertDpToPx(8), 0, 0, 0);
        textView2.setLayoutParams(layoutParams4);
        linearLayout3.addView(textView2);
        linearLayout.addView(linearLayout3);
        LinearLayout linearLayout4 = new LinearLayout(getActivity());
        linearLayout4.setOrientation(0);
        linearLayout4.setGravity(17);
        linearLayout4.setPadding(0, convertDpToPx(8), 0, convertDpToPx(0));
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams5.setMargins(iConvertDpToPx, 16, iConvertDpToPx, iConvertDpToPx);
        linearLayout4.setLayoutParams(layoutParams5);
        Button buttonCreateButton = createButton(Strings.getNegativeButton(), new lbua6i(this));
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(0, -2, 1);
        layoutParams6.setMargins(12, 8, convertDpToPx(8), convertDpToPx(8));
        buttonCreateButton.setLayoutParams(layoutParams6);
        linearLayout4.addView(buttonCreateButton);
        Button buttonCreateButton2 = createButton(Strings.getPositiveButton(), new lbua76(this));
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(0, -2, 1);
        layoutParams7.setMargins(convertDpToPx(8), 8, 12, convertDpToPx(8));
        buttonCreateButton2.setLayoutParams(layoutParams7);
        linearLayout4.addView(buttonCreateButton2);
        linearLayout.addView(linearLayout4);
        builder.setView(linearLayout);
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.setOnKeyListener(new lbua7a(this));
        alertDialogCreate.setOnShowListener(new lbua80(this, alertDialogCreate));
        r4.setOnCheckedChangeListener(new lbua86(this, r4));
        return alertDialogCreate;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        getDialog().setCanceledOnTouchOutside(false);
        return viewOnCreateView;
    }
}
