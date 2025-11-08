package com.example.jogo_velha_app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TabelaJogo extends View {

    private final int boardColor;
    private final int Xcolor;
    private final int Ocolor; // definição dos atributos feitos laaa no xml
    private final int WinLinecolor;
    private int tamanhoCelulas;
    private final Paint telha = new Paint();


    public TabelaJogo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray arr = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TabelaJogo, //referenciando o arquivo
                0, 0
        );

        try {
            boardColor = arr.getColor(R.styleable.TabelaJogo_boardColor, 0); //valores dos atributos de estilização
            Xcolor = arr.getColor(R.styleable.TabelaJogo_Xcolor, 0);
            Ocolor = arr.getColor(R.styleable.TabelaJogo_Ocolor, 0); // 0 por padrão pois queremos customizar
            WinLinecolor = arr.getColor(R.styleable.TabelaJogo_WinLinecolor, 0);
        } finally {
            arr.recycle();
        }
    }

    @Override
    protected void onMeasure(int altura, int largura) {
        int dimensoes = Math.min(MeasureSpec.getSize(largura),
                MeasureSpec.getSize(altura)); //definindo as dimensões da tabela e células individualmente
        setMeasuredDimension(dimensoes, dimensoes);
        tamanhoCelulas = dimensoes / 3;
    }

    @Override
    protected void onDraw(Canvas canvas){
        Paint telha = new Paint();
        telha.setStyle(Paint.Style.STROKE); // estilização da tabela pra quando ela for inserida na view
        telha.setStrokeWidth(10); //sem incluir diretamente os valores do xml porque, novamente, são customizáveis
        telha.setAntiAlias(true);
        telha.setColor(boardColor);

        desenharTelha(canvas, telha);
    }

    private void desenharTelha(Canvas canvas, Paint telha){
        for (int coluna = 1; coluna<3; coluna++){
       canvas.drawLine(tamanhoCelulas * coluna, 0, tamanhoCelulas * coluna, getHeight(), telha);

    }
        for (int linha = 1; linha<3; linha++){
            canvas.drawLine(0, tamanhoCelulas * linha, getWidth(), tamanhoCelulas * linha, telha);
        }

    }
}