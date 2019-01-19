package com.jhinmugen.afoisourla;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class SendMail {
    private String emailAdress;
    private String subect;
    private String content;
    private Context context;

    public SendMail(Context context, String content, String subject) {
        this.context = context;
        this.emailAdress = "spanosvagos@gmail.com";
        this.subect = subject;
        this.content = content;
    }

    public void send() {
        Intent sendMail = new Intent(Intent.ACTION_SENDTO);
        String uriText = "mailto:" + Uri.encode(emailAdress) +
                "?subject=" + Uri.encode(subect) +
                "&body=" + Uri.encode(content);
        Uri uri = Uri.parse(uriText);
        sendMail.setData(uri);
        context.startActivity(Intent.createChooser(sendMail, "Send Mail...."));
    }
}
