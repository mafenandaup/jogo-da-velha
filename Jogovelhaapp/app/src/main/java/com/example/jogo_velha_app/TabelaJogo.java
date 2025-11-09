package com.example.jogo_velha_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TabelaJogo extends View {

    private final int boardColor;
    private final int Xcolor;
    private final int Ocolor;
    private final int WinLinecolor;
    private int tamanhoCelulas;
    private final LogicaJogo jogo;
    private final Paint telha = new Paint();

    // ✅ Construtor exigido pelo Android (necessário para inflar via XML)
    public TabelaJogo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // Inicialize sua lógica (caso precise)
        this.jogo = new LogicaJogo(); // ou null, se for setada depois

        TypedArray arr = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TabelaJogo,
                0, 0
        );

        try {
            boardColor = arr.getColor(R.styleable.TabelaJogo_boardColor, 0);
            Xcolor = arr.getColor(R.styleable.TabelaJogo_Xcolor, 0);
            Ocolor = arr.getColor(R.styleable.TabelaJogo_Ocolor, 0);
            WinLinecolor = arr.getColor(R.styleable.TabelaJogo_WinLinecolor, 0);
        } finally {
            arr.recycle();
        }
    }

    // Seus outros construtores opcionais (se usados no código)
    public TabelaJogo(Context context, int boardColor, int xcolor, int ocolor, int winLinecolor, LogicaJogo jogo) {
        super(context);
        this.boardColor = boardColor;
        Xcolor = xcolor;
        Ocolor = ocolor;
        WinLinecolor = winLinecolor;
        this.jogo = jogo;
    }

    public TabelaJogo(Context context, AttributeSet attrs, int boardColor, int xcolor, int ocolor, int winLinecolor, LogicaJogo jogo) {
        super(context, attrs);
        this.boardColor = boardColor;
        Xcolor = xcolor;
        Ocolor = ocolor;
        WinLinecolor = winLinecolor;
        this.jogo = jogo;
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

        // as linhas e colunas são numeradas de 0 a 2, então, nos métodos desenharX e desenharO, se você quisesse deixar
        // no canto direito, teria que ser linha 0 e coluna 2, no meio, ambas linha e coluna são 1, vice versa..
        // isso ocorre porque cada linha e coluna são definidas como parte de um array - vide o TypedArray acima -
        // cada célula é definida a partir de um valor i (linha) e j (coluna), ambos de 0 a 2.

        desenharMarcadores(canvas);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (jogo == null) return false;
        float coordX = event.getX(); // armazenam as coordenadas de ONDE O usuário clicou na tabela
        float coordY = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
            int linha = (int) Math.floor(coordY / tamanhoCelulas);
            int coluna = (int) Math.floor(coordX / tamanhoCelulas);

            // garantir dentro dos limites 0..2
            if (linha < 0) linha = 0;
            if (linha > 2) linha = 2;
            if (coluna < 0) coluna = 0;
            if (coluna > 2) coluna = 2;


            if (jogo.atualizarTabela(linha, coluna)){
                // somente quando houve alteração, redesenha a view
                invalidate();
            }

            return true;
        }
        return false;
    }

    private void desenharTelha(Canvas canvas, Paint telha){
        for (int coluna = 1; coluna < 3; coluna++){ //começa com uma linha (horizontal) desenhada, quando chegar na segunda já para de desenhar pois aí terão sido feitas 3 células
            canvas.drawLine(tamanhoCelulas * coluna, 0, tamanhoCelulas * coluna, getHeight(), telha);
        }
        for (int linha = 1; linha < 3; linha++){ // mesma coisa pra essa aqui de baixo (vertical)
            canvas.drawLine(0, tamanhoCelulas * linha, getWidth(), tamanhoCelulas * linha, telha);
        }
    }


    private void desenharMarcadores(Canvas canvas){
        if (jogo == null) return;
        int[][] tabela = jogo.getTabelaJogo();
        for (int i = 0; i < 3; i++){  // i representa as linhas, j representa as colunas, como mencionado no arquivo da tabela
            for (int j = 0; j < 3; j++){
                int valor = tabela[i][j];
                if (valor == 1) {
                    desenharX(canvas, i, j);
                } else if (valor == 2) {
                    desenharO(canvas, i, j);
                }
            }
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
        float top = linha * tamanhoCelulas + padding;
        float right = (coluna + 1) * tamanhoCelulas - padding;
        float bottom = (linha + 1) * tamanhoCelulas - padding;

        canvas.drawOval(left, top, right, bottom, playerO);
    }


}