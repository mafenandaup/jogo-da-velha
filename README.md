# Jogo da Velha

Uma aplicação gamificada simples para a disciplina de Dispositivos Móveis.

##  Tecnologias Utilizadas

O projeto é desenvolvido primariamente em **Java**, indicando que a aplicação é destinada à plataforma **Android** (dada a estrutura de arquivos como `gradle` e o contexto da disciplina de Dispositivos Móveis).

*   **Linguagem:** Java
*   **Plataforma:** Android
*   **Sistema de Build:** Gradle

##  Instruções de Uso

Siga os passos abaixo para configurar e rodar o projeto localmente:

### 1. Clonar o Repositório

Abra seu terminal ou prompt de comando e execute:

```bash
git clone https://github.com/mafenandaup/jogo-da-velha.git
cd jogo-da-velha
cd jogovelhaapp
```
* IMPORTANTE: tem que ser no folder jogovelhaapp, porque é aí que está o app executável

### 2. Abrir no Android Studio

1.  Abra o **Android Studio**.
2.  Selecione **"Open an existing Android Studio project"** (Abrir um projeto Android Studio existente).
3.  Navegue até a pasta `jogo-da-velha` que você acabou de clonar e selecione-a.
4.  Aguarde o Android Studio sincronizar o projeto e baixar as dependências do Gradle.

### 3. Executar a Aplicação

1.  Conecte um dispositivo Android ao seu computador via USB ou inicie um **Emulador Android** através do AVD Manager do Android Studio.
2.  Na barra de ferramentas do Android Studio, selecione o dispositivo ou emulador desejado.
3.  Clique no botão **"Run"** (o ícone de triângulo verde) para construir e instalar a aplicação no dispositivo/emulador.

### 4. Validações de teste
 * Inserir os campos de input vazios (um ou todos)
 * Tentar clicar em uma célula que não esteja vazia (window alert)
