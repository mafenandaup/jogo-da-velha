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

        desenharX(canvas, 2, 2); // as linhas e colunas são numeradas de 0 a 2, então se você quisesse deixar
        // no canto direito, teria que ser linha 0 e coluna 2, no meio, ambas linha e coluna são 1, vice versa..
        // isso ocorre porque cada linha e coluna são definidas como parte de um array - vide o TypedArray acima -
        // cada célula é definida a partir de um valor i (linha) e j (coluna), ambos de 0 a 2.

        desenharO(canvas ,0, 2);
    }

    private void desenharTelha(Canvas canvas, Paint telha){
        for (int coluna = 1; coluna<3; coluna++){ //começa com uma linha (horizontal) desenhada, quando chegar na segunda já para de desenhar pois aí terão sido feitas 3 células
       canvas.drawLine(tamanhoCelulas * coluna, 0, tamanhoCelulas * coluna, getHeight(), telha);

    }
        for (int linha = 1; linha<3; linha++){ // mesma coisa pra essa aqui de baixo (vertical)
            canvas.drawLine(0, tamanhoCelulas * linha, getWidth(), tamanhoCelulas * linha, telha);
        }

    }

    private void desenharX(Canvas canvas, int linha, int coluna){
        Paint playerX = new Paint();
        playerX.setColor(Xcolor);
        playerX.setStrokeWidth(14);

        float padding = tamanhoCelulas * 0.2f;

        float startXLeft = (coluna+1)*tamanhoCelulas - padding;
        float startYLeft = linha * tamanhoCelulas + padding;
        float endXLeft = coluna * tamanhoCelulas + padding; // estabelecendo as coordenadas em variáveis (mais facil de editar dps)
        float endYLeft = (linha + 1) * tamanhoCelulas - padding;

        float startXRight = coluna * tamanhoCelulas + padding;
        float startYRight = linha * tamanhoCelulas + padding;
        float endXRight = (coluna + 1) * tamanhoCelulas - padding;
        float endYRight = (linha + 1) * tamanhoCelulas - padding;

        canvas.drawLine(startXLeft, startYLeft,endXLeft, endYLeft, playerX);
        canvas.drawLine(startXRight, startYRight, endXRight,endYRight, playerX);

    }

    private void desenharO(Canvas canvas, int linha, int coluna){
        Paint playerO = new Paint();
        playerO.setStyle(Paint.Style.STROKE);
        playerO.setColor(Ocolor);
        playerO.setStrokeWidth(14);

        float padding = tamanhoCelulas * 0.2f;
        float left = coluna * tamanhoCelulas + padding;
        float right = linha * tamanhoCelulas + padding;
        float top = (coluna + 1) * tamanhoCelulas - padding;
        float bottom = (linha + 1) * tamanhoCelulas - padding;

        canvas.drawOval(left, right, top, bottom, playerO);
    }


}