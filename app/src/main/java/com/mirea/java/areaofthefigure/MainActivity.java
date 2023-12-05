package com.mirea.java.areaofthefigure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Spinner spinner;
    EditText editTextNumber;
    EditText editTextNumber2;
    EditText editTextNumber3;
    EditText editTextNumber4;
    EditText editTextNumber5;
    ImageView imageView;
    ImageView imageViewS;
    ImageView imageViewP;
    TextView textP;
    TextView textS;

    ImageButton buttonTelegram;
    ImageButton buttonWhatsapp;
    ImageButton buttonEmail;

    View.OnClickListener onClickListenerTelegram = null;
    View.OnClickListener onClickListenerWhatsapp = null;
    View.OnClickListener onClickListenerEmail = null;

    String msg = null;
    TextWatcher watcher = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        spinner = findViewById(R.id.spinner);
        imageView = findViewById(R.id.imageView);
        imageViewS = findViewById(R.id.imageViewSFormula);
        imageViewP = findViewById(R.id.imageViewPFormula);
        editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        editTextNumber3 = findViewById(R.id.editTextNumber3);
        editTextNumber4 = findViewById(R.id.editTextNumber4);
        editTextNumber5 = findViewById(R.id.editTextNumber5);
        buttonTelegram = findViewById(R.id.imageButtonTelegram);
        buttonWhatsapp = findViewById(R.id.imageButtonWhatsapp);
        buttonEmail = findViewById(R.id.imageButtonEmail);
        textP = findViewById(R.id.textP);
        textS = findViewById(R.id.textS);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.figures,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                msg = null;
                buttonTelegram.setVisibility(View.INVISIBLE);
                buttonWhatsapp.setVisibility(View.INVISIBLE);
                buttonEmail.setVisibility(View.INVISIBLE);


                String item = (String)parent.getItemAtPosition(position);
                List<EditText> editTexts = Arrays.asList(editTextNumber, editTextNumber2, editTextNumber3, editTextNumber4, editTextNumber5);
                editTexts.forEach(editText -> {
                    editText.removeTextChangedListener(watcher);
                });

                switch (item) {
                    case "Square":
                        textP.setText(null);
                        textS.setText(null);

                        editTexts.forEach(editText -> {
                            editText.setText(null);
                        });

                        imageView.setImageResource(R.drawable.square);
                        imageViewP.setImageResource(R.drawable.squarep);
                        imageViewS.setImageResource(R.drawable.squares);

                        editTextNumber2.setVisibility(View.INVISIBLE);
                        editTextNumber3.setVisibility(View.INVISIBLE);
                        editTextNumber4.setVisibility(View.INVISIBLE);
                        editTextNumber5.setVisibility(View.INVISIBLE);

                        editTextNumber.setHint("Длина стороны");
                        watcher = new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                                if( editTextNumber.getText().toString().length() > 0)
                                {   buttonTelegram.setVisibility(View.VISIBLE);
                                    buttonWhatsapp.setVisibility(View.VISIBLE);
                                    buttonEmail.setVisibility(View.VISIBLE);

                                    double p = Double.parseDouble(editTextNumber.getText().toString()) * 4;
                                    textP.setText(p + "");
                                    double s = Math.pow(Integer.parseInt(editTextNumber.getText().toString()), 2);
                                    textS.setText(s + "");
                                    msg = "Площадь квадрата (Сторона равна "+ editTextNumber.getText().toString() +") равна " + textS.getText().toString() + " и периметр равен " + textP.getText().toString();

                                    onClickListenerTelegram = v2 -> {
                                        intentMessageTelegram(msg);
                                    };
                                    onClickListenerWhatsapp = v2 -> {
                                        intentMessageWhatsapp(msg);
                                    };
                                    onClickListenerEmail = v2 -> {
                                        intentMessageEmail(msg);
                                    };
                                    buttonTelegram.setOnClickListener(onClickListenerTelegram);
                                    buttonWhatsapp.setOnClickListener(onClickListenerWhatsapp);
                                    buttonEmail.setOnClickListener(onClickListenerEmail);
                                } else {
                                    buttonTelegram.setVisibility(View.INVISIBLE);
                                    buttonWhatsapp.setVisibility(View.INVISIBLE);
                                    buttonEmail.setVisibility(View.INVISIBLE);
                                }
                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                textP.setText(null);
                                textS.setText(null);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }

                        };
                        break;
                    case "Rectangle":
                        textP.setText(null);
                        textS.setText(null);

                        editTexts.forEach(editText -> {
                            editText.setText(null);
                        });

                        imageView.setImageResource(R.drawable.rectangle);
                        imageViewP.setImageResource(R.drawable.rectanglep);
                        imageViewS.setImageResource(R.drawable.rectangles);

                        editTextNumber2.setVisibility(View.VISIBLE);
                        editTextNumber3.setVisibility(View.INVISIBLE);
                        editTextNumber4.setVisibility(View.INVISIBLE);
                        editTextNumber5.setVisibility(View.INVISIBLE);

                        editTextNumber.setHint("Длина");
                        editTextNumber2.setHint("Ширина");
                        watcher = new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                                if(editTextNumber.getText().toString().length() > 0 && editTextNumber2.getText().toString().length() > 0)
                                {   buttonTelegram.setVisibility(View.VISIBLE);
                                    buttonWhatsapp.setVisibility(View.VISIBLE);
                                    buttonEmail.setVisibility(View.VISIBLE);
                                    double p = (Double.parseDouble(editTextNumber.getText().toString()) + Double.parseDouble(editTextNumber2.getText().toString())) * 2;
                                    textP.setText(p + "");
                                    double s = Double.parseDouble(editTextNumber.getText().toString()) * Double.parseDouble(editTextNumber2.getText().toString());
                                    textS.setText(s + "");
                                    msg = "Площадь прямоугольника (Длина равна "+ editTextNumber.getText().toString() +" и ширина равна " + editTextNumber2.getText().toString() + " ) равна " + textS.getText().toString() + " и периметр равен " + textP.getText().toString();
                                    onClickListenerTelegram = v2 -> {
                                        intentMessageTelegram(msg);
                                    };
                                    onClickListenerWhatsapp = v2 -> {
                                        intentMessageWhatsapp(msg);
                                    };
                                    onClickListenerEmail = v2 -> {
                                        intentMessageEmail(msg);
                                    };
                                    buttonTelegram.setOnClickListener(onClickListenerTelegram);
                                    buttonWhatsapp.setOnClickListener(onClickListenerWhatsapp);
                                    buttonEmail.setOnClickListener(onClickListenerEmail);
                                }else {
                                    buttonTelegram.setVisibility(View.INVISIBLE);
                                    buttonWhatsapp.setVisibility(View.INVISIBLE);
                                    buttonEmail.setVisibility(View.INVISIBLE);
                                }
                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                textP.setText(null);
                                textS.setText(null);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }

                        };
                        break;
                    case "Rhombus":
                        textP.setText(null);
                        textS.setText(null);

                        editTexts.forEach(editText -> {
                            editText.setText(null);
                        });

                        imageView.setImageResource(R.drawable.rhombus);
                        imageViewP.setImageResource(R.drawable.rhombusp);
                        imageViewS.setImageResource(R.drawable.rhombuss);

                        editTextNumber2.setVisibility(View.VISIBLE);
                        editTextNumber3.setVisibility(View.VISIBLE);
                        editTextNumber4.setVisibility(View.INVISIBLE);
                        editTextNumber5.setVisibility(View.INVISIBLE);

                        editTextNumber.setHint("Длина стороны");
                        editTextNumber2.setHint("Диаметр 1");
                        editTextNumber3.setHint("Диаметр 2");
                        watcher = new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                                if (editTextNumber.getText().toString().length() > 0) {
                                    double p = Double.parseDouble(editTextNumber.getText().toString()) * 4 ;
                                    textP.setText(p + "");

                                    if (editTextNumber2.getText().toString().length() > 0 && editTextNumber3.getText().toString().length() > 0) {
                                        buttonTelegram.setVisibility(View.VISIBLE);
                                        buttonWhatsapp.setVisibility(View.VISIBLE);
                                        buttonEmail.setVisibility(View.VISIBLE);
                                        double s = Double.parseDouble(editTextNumber2.getText().toString()) * Double.parseDouble(editTextNumber3.getText().toString()) * 0.5;
                                        textS.setText(s + "");
                                        msg = "Площадь ромба (Длина стороны равна "+ editTextNumber.getText().toString() +", диаметр 1 равен " + editTextNumber2.getText().toString() + " и диаметр 2 равен " + editTextNumber3.getText().toString() + " ) равна " + textS.getText().toString() + " и периметр равен " + textP.getText().toString();
                                        onClickListenerTelegram = v2 -> {
                                            intentMessageTelegram(msg);
                                        };
                                        onClickListenerWhatsapp = v2 -> {
                                            intentMessageWhatsapp(msg);
                                        };
                                        onClickListenerEmail = v2 -> {
                                            intentMessageEmail(msg);
                                        };
                                        buttonTelegram.setOnClickListener(onClickListenerTelegram);
                                        buttonWhatsapp.setOnClickListener(onClickListenerWhatsapp);
                                        buttonEmail.setOnClickListener(onClickListenerEmail);
                                    } else {
                                        buttonTelegram.setVisibility(View.INVISIBLE);
                                        buttonWhatsapp.setVisibility(View.INVISIBLE);
                                        buttonEmail.setVisibility(View.INVISIBLE);
                                    }
                                } else {
                                    buttonTelegram.setVisibility(View.INVISIBLE);
                                    buttonWhatsapp.setVisibility(View.INVISIBLE);
                                    buttonEmail.setVisibility(View.INVISIBLE);
                                }
                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                textP.setText(null);
                                textS.setText(null);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }

                        };
                        break;
                    case "Triangle":
                        textP.setText(null);
                        textS.setText(null);

                        editTexts.forEach(editText -> {
                            editText.setText(null);
                        });

                        imageView.setImageResource(R.drawable.triangle);
                        imageViewP.setImageResource(R.drawable.trianglep);
                        imageViewS.setImageResource(R.drawable.triangles);

                        editTextNumber2.setVisibility(View.VISIBLE);
                        editTextNumber3.setVisibility(View.VISIBLE);
                        editTextNumber4.setVisibility(View.VISIBLE);
                        editTextNumber5.setVisibility(View.INVISIBLE);

                        editTextNumber.setHint("Длина A");
                        editTextNumber2.setHint("Длина B");
                        editTextNumber3.setHint("Длина C");
                        editTextNumber4.setHint("Высота");

                        watcher = new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                                if (editTextNumber.getText().toString().length() > 0 && editTextNumber2.getText().toString().length() > 0 && editTextNumber3.getText().toString().length() > 0) {
                                    double p = Double.parseDouble(editTextNumber.getText().toString()) + Double.parseDouble(editTextNumber2.getText().toString()) + Double.parseDouble(editTextNumber3.getText().toString());
                                    textP.setText(p + "");

                                    if (editTextNumber4.getText().toString().length() > 0) {
                                        buttonTelegram.setVisibility(View.VISIBLE);
                                        buttonWhatsapp.setVisibility(View.VISIBLE);
                                        double s = Double.parseDouble(editTextNumber.getText().toString()) * Double.parseDouble(editTextNumber4.getText().toString()) * 0.5;
                                        textS.setText(s + "");

                                        msg = "Площадь треугольника (Длина A равна "+ editTextNumber.getText().toString() +", длина B равна " + editTextNumber2.getText().toString() + ", длина C равна " + editTextNumber3.getText().toString() + " и высота равна " + editTextNumber4.getText().toString() + " ) равна " + textS.getText().toString() + " и периметр равен " + textP.getText().toString();
                                        onClickListenerTelegram = v2 -> {
                                            intentMessageTelegram(msg);
                                        };
                                        onClickListenerWhatsapp = v2 -> {
                                            intentMessageWhatsapp(msg);
                                        };
                                        onClickListenerEmail = v2 -> {
                                            intentMessageEmail(msg);
                                        };
                                        buttonTelegram.setOnClickListener(onClickListenerTelegram);
                                        buttonWhatsapp.setOnClickListener(onClickListenerWhatsapp);
                                        buttonEmail.setOnClickListener(onClickListenerEmail);
                                    } else {
                                        buttonTelegram.setVisibility(View.INVISIBLE);
                                        buttonWhatsapp.setVisibility(View.INVISIBLE);
                                        buttonEmail.setVisibility(View.INVISIBLE);
                                    }
                                }else {
                                    buttonTelegram.setVisibility(View.INVISIBLE);
                                    buttonWhatsapp.setVisibility(View.INVISIBLE);
                                    buttonEmail.setVisibility(View.INVISIBLE);
                                }
                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                textP.setText(null);
                                textS.setText(null);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }

                        };

                        break;
                    case "Trapezoid":
                        textP.setText(null);
                        textS.setText(null);

                        editTexts.forEach(editText -> {
                            editText.setText(null);
                        });


                        imageView.setImageResource(R.drawable.trapezoid);
                        imageViewP.setImageResource(R.drawable.trapezoidp);
                        imageViewS.setImageResource(R.drawable.trapezoids);

                        editTextNumber2.setVisibility(View.VISIBLE);
                        editTextNumber3.setVisibility(View.VISIBLE);
                        editTextNumber4.setVisibility(View.VISIBLE);
                        editTextNumber5.setVisibility(View.VISIBLE);

                        editTextNumber.setHint("Длина A");
                        editTextNumber2.setHint("Длина B");
                        editTextNumber3.setHint("Длина C");
                        editTextNumber4.setHint("Длина D");
                        editTextNumber5.setHint("Высота");

                        watcher = new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                                if (editTextNumber.getText().toString().length() > 0 && editTextNumber2.getText().toString().length() > 0 && editTextNumber3.getText().toString().length() > 0 && editTextNumber4.getText().toString().length() > 0 ) {
                                    double p = Double.parseDouble(editTextNumber.getText().toString()) + Double.parseDouble(editTextNumber2.getText().toString()) + Double.parseDouble(editTextNumber3.getText().toString()) + Double.parseDouble(editTextNumber4.getText().toString());
                                    textP.setText(p + "");

                                    if (editTextNumber5.getText().toString().length() > 0) {
                                        buttonTelegram.setVisibility(View.VISIBLE);
                                        buttonWhatsapp.setVisibility(View.VISIBLE);
                                        buttonEmail.setVisibility(View.VISIBLE);
                                        double s = (Double.parseDouble(editTextNumber.getText().toString()) + Double.parseDouble(editTextNumber2.getText().toString())) * 0.5 * (Double.parseDouble(editTextNumber5.getText().toString()));
                                        textS.setText(s + "");
                                        msg = "Площадь трапеции (Длина A равна "+ editTextNumber.getText().toString() +", длина B равна " + editTextNumber2.getText().toString() + ", длина C равна " + editTextNumber3.getText().toString() +", длина D равна " + editTextNumber4.getText().toString() + " и высота равна " + editTextNumber5.getText().toString() + " ) равна " + textS.getText().toString() + " и периметр равен " + textP.getText().toString();
                                        onClickListenerTelegram = v2 -> {
                                            intentMessageTelegram(msg);
                                        };
                                        onClickListenerWhatsapp = v2 -> {
                                            intentMessageWhatsapp(msg);
                                        };
                                        onClickListenerEmail = v2 -> {
                                            intentMessageEmail(msg);
                                        };
                                        buttonTelegram.setOnClickListener(onClickListenerTelegram);
                                        buttonWhatsapp.setOnClickListener(onClickListenerWhatsapp);
                                        buttonEmail.setOnClickListener(onClickListenerEmail);
                                    } else {
                                        buttonTelegram.setVisibility(View.INVISIBLE);
                                        buttonWhatsapp.setVisibility(View.INVISIBLE);
                                        buttonEmail.setVisibility(View.INVISIBLE);
                                    }
                                } else {
                                    buttonTelegram.setVisibility(View.INVISIBLE);
                                    buttonWhatsapp.setVisibility(View.INVISIBLE);
                                    buttonEmail.setVisibility(View.INVISIBLE);
                                }
                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                textP.setText(null);
                                textS.setText(null);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }

                        };
                        break;
                    case "Circle":
                        textP.setText(null);
                        textS.setText(null);

                        editTexts.forEach(editText -> {
                            editText.setText(null);
                        });

                        imageView.setImageResource(R.drawable.circle);
                        imageViewP.setImageResource(R.drawable.circlep);
                        imageViewS.setImageResource(R.drawable.circles);

                        editTextNumber2.setVisibility(View.INVISIBLE);
                        editTextNumber3.setVisibility(View.INVISIBLE);
                        editTextNumber4.setVisibility(View.INVISIBLE);
                        editTextNumber5.setVisibility(View.INVISIBLE);

                        editTextNumber.setHint("Радиус");

                        watcher = new TextWatcher() {
                            @Override
                            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                                if( editTextNumber.getText().toString().length() > 0)
                                    {
                                    buttonTelegram.setVisibility(View.VISIBLE);
                                    buttonWhatsapp.setVisibility(View.VISIBLE);
                                    buttonEmail.setVisibility(View.VISIBLE);
                                    double p = Double.parseDouble(editTextNumber.getText().toString()) * 2 * 3.14;
                                    textP.setText(p + "");
                                    double s = Math.pow(Integer.parseInt(editTextNumber.getText().toString()), 2) * 3.14;
                                    textS.setText(s + "");
                                    msg = "Площадь круга (Радиус равен "+ editTextNumber.getText().toString() +  " ) равна " + textS.getText().toString() + " и периметр равен " + textP.getText().toString();
                                    onClickListenerTelegram = v2 -> {
                                        intentMessageTelegram(msg);
                                    };
                                    onClickListenerWhatsapp = v2 -> {
                                        intentMessageWhatsapp(msg);
                                    };
                                    onClickListenerEmail = v2 -> {
                                        intentMessageEmail(msg);
                                    };
                                    buttonTelegram.setOnClickListener(onClickListenerTelegram);
                                    buttonWhatsapp.setOnClickListener(onClickListenerWhatsapp);
                                    buttonEmail.setOnClickListener(onClickListenerEmail);
                                } else {
                                    buttonTelegram.setVisibility(View.INVISIBLE);
                                    buttonWhatsapp.setVisibility(View.INVISIBLE);
                                    buttonEmail.setVisibility(View.INVISIBLE);
                                }
                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                textP.setText(null);
                                textS.setText(null);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }

                        };
                        break;
                    default:
                        break;
                }
                TextWatcher finalWatcher = watcher;
                editTexts.forEach(editText -> {
                    editText.addTextChangedListener(finalWatcher);
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

    }

    void intentMessageTelegram(String msg)
    {
        final String appName = "org.telegram.messenger";

        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        myIntent.setPackage(appName);
        myIntent.putExtra(Intent.EXTRA_TEXT, msg);//
        this.startActivity(Intent.createChooser(myIntent, "Share with"));

    }

    void intentMessageWhatsapp(String msg)
    {
        final String appName = "com.whatsapp";

            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            myIntent.setPackage(appName);
            myIntent.putExtra(Intent.EXTRA_TEXT, msg);//
            this.startActivity(Intent.createChooser(myIntent, "Share with"));

    }

    void intentMessageEmail(String msg)
    {
        Intent myIntent = new Intent(Intent.ACTION_SEND);

        myIntent.putExtra(Intent.EXTRA_TEXT, msg);
        myIntent.setType("message/rfc822");

        startActivity(Intent.createChooser(myIntent, "Choose an Email client :"));

    }




}