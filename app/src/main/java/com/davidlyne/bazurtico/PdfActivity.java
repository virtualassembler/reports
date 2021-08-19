package com.davidlyne.bazurtico;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import androidx.core.content.FileProvider;
//import android.content.FileProvider;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.Page;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Margins;
import android.print.PrintAttributes.Resolution;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.davidlyne.bazurtico.data.local.ClientDataType;
import com.davidlyne.bazurtico.data.local.ClientNameDataType;
import com.davidlyne.bazurtico.data.local.TotalizerDatabase;
import com.davidlyne.bazurtico.data.local.VegetableDataType;
import com.davidlyne.bazurtico.data.local.VegetableListKt;


public class PdfActivity extends Activity implements Runnable {

    private Intent mShareIntent;

    private OutputStream os;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Bundle bundle = getIntent().getExtras();
        TextView textViewConsecutive = (TextView)findViewById(R.id.textViewConsecutive);
        TextView textViewClientName = (TextView)findViewById(R.id.textViewClientName);
        TextView textViewTotal = (TextView)findViewById(R.id.textViewTotal);

        if(bundle.getString("billId")!= null) {
            int billId = bundle.getInt("billId");
            Toast.makeText(this,"id bill: "+bundle.getString("billId"),Toast.LENGTH_LONG).show();
            textViewConsecutive.setText(""+bundle.getString("billId"));
            textViewTotal.setText(""+bundle.getString("billId"));
            String clientId = TotalizerDatabase.Companion.getInstance(this).getBillDAO().getClientNameByBillId();
            String clientId2 = TotalizerDatabase.Companion.getInstance(this).getBillDAO().getClientNameByBillId();
            //String d = TotalizerDatabase.Companion.getInstance(this).getVegetableDAO().getVegetableList().toString();
            //String d2 = TotalizerDatabase.Companion.getInstance(this).getVegetableDAO().getVegetableList().toString();
        }

        LinearLayout linearLayout = findViewById(R.id.textArea);
        //Create list
        List<VegetableDataType> vegetableList = new ArrayList<>();
        vegetableList = VegetableListKt.getDefaultVegetableList();
        // Create TextView programmatically.
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.LEFT);
        textView.setTextSize(8);
        String items = "";
        for(VegetableDataType vegetable : vegetableList){
            items = items+vegetable.getId()+" \u0020\u0020 "+vegetable.getName().toString()+" \u0020\u0020\u0020\u0020\u0020 "+vegetable.getPrice()+"\n";
        }
        textView.setText(items);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Sociales","sociales");
            }
        });
        // Add TextView to LinearLayout
        if (linearLayout != null) {
            linearLayout.addView(textView);
        }
    }

    /** PDF Gen should run in own thread to not slow the GUI */
    public void makeAndSharePDF(View buttonSource) {
        new Thread(this).start();
    }

    public void run() {
        // Create a shiny new (but blank) PDF document in memory
        // We want it to optionally be printable, so add PrintAttributes
        // and use a PrintedPdfDocument. Simpler: new PdfDocument().
        PrintAttributes printAttrs = new PrintAttributes.Builder().
                setColorMode(PrintAttributes.COLOR_MODE_COLOR).
                setMediaSize(PrintAttributes.MediaSize.NA_LETTER).
                setResolution(new Resolution("zooey", PRINT_SERVICE, 300, 500)).
                setMinMargins(Margins.NO_MARGINS).
                build();
        PdfDocument document = new PrintedPdfDocument(this, printAttrs);

        // crate a page description
        PageInfo pageInfo = new PageInfo.Builder(300, 300, 1).create();

        // create a new page from the PageInfo
        Page page = document.startPage(pageInfo);

        // repaint the user's text into the page
        View content = findViewById(R.id.textArea);

        content.draw(page.getCanvas());

        // do final processing of the page
        document.finishPage(page);

        // Here you could add more pages in a longer doc app, but you'd have
        // to handle page-breaking yourself in e.g., write your own word processor...

        // Now write the PDF document to a file; it actually needs to be a file
        // since the Share mechanism can't accept a byte[]. though it can
        // accept a String/CharSequence. Meh.
        try {
            File pdfDirPath = new File(getFilesDir(), "pdfs");
            pdfDirPath.mkdirs();
            File file = new File(pdfDirPath, "pdfsend.pdf");
            Uri contentUri = FileProvider.getUriForFile(this, "com.davidlyne.provider", file);
            os = new FileOutputStream(file);
            document.writeTo(os);
            document.close();
            os.close();

            Uri uri = contentUri;
            String uriString = contentUri.toString();

            shareDocument(contentUri);
        } catch (IOException e) {
            throw new RuntimeException("Error generating file", e);
        }
    }

    private void shareDocument(Uri uri) {
        mShareIntent = new Intent();
        mShareIntent.setAction(Intent.ACTION_SEND);
        mShareIntent.setType("application/pdf");
        // Assuming it may go via eMail:
        mShareIntent.putExtra(Intent.EXTRA_SUBJECT, "Here is a PDF from PdfSend");
        // Attach the PDf as a Uri, since Android can't take it as bytes yet.
        mShareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(mShareIntent);
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}


/*
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PdfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
    }
}
*/