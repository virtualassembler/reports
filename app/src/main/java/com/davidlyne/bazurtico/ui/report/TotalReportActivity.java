package com.davidlyne.bazurtico.ui.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import androidx.core.content.FileProvider;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.davidlyne.bazurtico.R;
import com.davidlyne.bazurtico.data.local.SelectedVegetableDataType;
import com.davidlyne.bazurtico.data.local.TotalizerDatabase;
import com.davidlyne.bazurtico.data.local.VegetableDataType;
import com.davidlyne.bazurtico.util.DateHelper;

import static android.widget.Toast.LENGTH_LONG;

public class TotalReportActivity extends Activity implements Runnable {

    private Intent mShareIntent;

    private OutputStream os;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_report);
        DateHelper dateHelper = new DateHelper();
        TextView textViewTitle = (TextView)findViewById(R.id.textViewTitle);
        textViewTitle.setText("Pedidos del dia "+dateHelper.getDayName().toUpperCase()+", "+ dateHelper.getDay()+" "+dateHelper.getMonth()+" "+dateHelper.getYear()+" ");
        setLinearLayout(dateHelper.getYear(),dateHelper.getMonth(),dateHelper.getDay());
        EditText editTextYear = (EditText) findViewById(R.id.editTextYear);
        EditText editTextMonth = (EditText) findViewById(R.id.editTextMonth);
        EditText editTextDay = (EditText) findViewById(R.id.editTextDay);
        Button buttonGenerateReport = (Button)findViewById(R.id.buttonGenerateReport);
        buttonGenerateReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(editTextYear.getText().toString().isEmpty() && editTextMonth.getText().toString().isEmpty() && editTextDay.getText().toString().isEmpty()){
                    Toast.makeText(TotalReportActivity.this,"Faltan Datos Importantes ",Toast.LENGTH_LONG).show();
                }else{
                    int year = new Integer(editTextYear.getText().toString());
                    int month = new Integer(editTextMonth.getText().toString());
                    int day = new Integer(editTextDay.getText().toString());
                    if(year>0 && month> 0 && day>0){
                        setLinearLayout(year,month,day);
                        textViewTitle.setText("Pedidos del dia: "+ day+" "+month+" "+year+" ");
                    }
                }
            }
        });
    }

    private void setLinearLayout(int year, int month, int day) {
        LinearLayout linearLayout = findViewById(R.id.linearLayoutVegetableList2);
        List<SelectedVegetableDataType> todaySelectedVegetableList = new ArrayList<>();
        todaySelectedVegetableList = TotalizerDatabase.Companion.getInstance(this).getBillVegetableDAO().getTodaySelectedVegetableList(year,month,day);
        if(todaySelectedVegetableList.isEmpty()){Toast.makeText(this,"No hay reportes", LENGTH_LONG).show(); finish(); }
        Log.e("#21","Cantidad de items vegetable_list de hoy: "+todaySelectedVegetableList.size());
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.LEFT);
        textView.setTextSize(8);
        String items = "";
        int vegetableId;
        int vegetableAmmount = 0;
        List<Integer> deatomizedVegetable = new ArrayList<Integer>();
        List<VegetableDataType> vegetableList = new ArrayList<>();
        vegetableList = TotalizerDatabase.Companion.getInstance(this).getVegetableDAO().getVegetableList();
        for(VegetableDataType vegetable : vegetableList) {
            vegetableId = vegetable.getId();
            for (SelectedVegetableDataType selectedVegetable : todaySelectedVegetableList) {
                if(vegetableId == selectedVegetable.getVegetableId()){
                    vegetableAmmount = vegetableAmmount+selectedVegetable.getGrams();
                    deatomizedVegetable.add(selectedVegetable.getGrams());
                    items = items +"\u0020\u0020 "+vegetableAmmount+"\u0020\u0020\u0020"+selectedVegetable.getName()+"\u0020\u0020\u0020\u0020"+deatomizedVegetable.toString()+"\n";
                }
            }
        }
        deatomizedVegetable.clear();
        if (vegetableAmmount > 0) {
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
                setResolution(new Resolution("zooey", PRINT_SERVICE, 400, 600)).
                setMinMargins(Margins.NO_MARGINS).
                build();
        PdfDocument document = new PrintedPdfDocument(this, printAttrs);

        // crate a page description
        PageInfo pageInfo = new PageInfo.Builder(400, 600, 1).create();

        // create a new page from the PageInfo
        Page page = document.startPage(pageInfo);

        // repaint the user's text into the page
        View content = findViewById(R.id.textArea2);

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
            //Print Uri provider
            Uri uri = contentUri;
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