# WebScraper - ANS PDF Downloader

Este projeto é um web scraper que acessa o site da ANS (Agência Nacional de Saúde Suplementar) para baixar os anexos em PDF relacionados ao rol de procedimentos. Ele também compacta os arquivos PDF baixados em um único arquivo ZIP.

## Funcionalidades

- Acessa a página da ANS com a lista de anexos.
- Baixa os PDFs de anexos específicos.
- Compacta os PDFs em um arquivo ZIP.

## Como usar

1. Clone este repositório:
    ```
    git clone https://github.com/seu-usuario/webscraper-ans-pdf.git
    ```

2. Navegue até o diretório do projeto:
    ```
    cd webscraper-ans-pdf
    ```

3. Compile e execute o projeto:
    ```
    mvn clean install
    mvn exec:java
    ```

Os arquivos serão baixados na pasta `downloads` e compactados em um arquivo `anexos.zip`.
