package com.meuprojeto;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WebScraper {

    private static final String DOWNLOAD_FOLDER = "downloads/";
    private static final String ZIP_FILE = "anexos.zip";

    public static void main(String[] args) {
        try {
            // Criar pasta para armazenar os PDFs baixados
            File dir = new File(DOWNLOAD_FOLDER);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Links específicos para download
            String[] pdfLinks = {
                "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf",
                "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos/Anexo_II_DUT_2021_RN_465.2021_RN628.2025_RN629.2025.pdf"
            };

            // Baixar os arquivos PDFs especificados
            for (String pdfUrl : pdfLinks) {
                String fileName = pdfUrl.substring(pdfUrl.lastIndexOf("/") + 1);
                downloadFile(pdfUrl, DOWNLOAD_FOLDER + fileName);
            }

            // Compactar os arquivos baixados em um ZIP
            zipFiles(DOWNLOAD_FOLDER, ZIP_FILE);
            System.out.println("Processo concluído. Arquivos compactados em: " + ZIP_FILE);

        } catch (IOException e) {
            System.err.println("Erro ao acessar a página: " + e.getMessage());
        }
    }

    // Método para baixar o arquivo
    public static void downloadFile(String fileURL, String savePath) throws IOException {
        System.out.println("Baixando: " + fileURL);

        URL url = new URL(fileURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(savePath)) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        System.out.println("Download concluído: " + savePath);
    }

    // Método para compactar os arquivos em um arquivo ZIP
    public static void zipFiles(String sourceFolder, String zipFilePath) throws IOException {
        File directory = new File(sourceFolder);
        File[] files = directory.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("Nenhum arquivo para compactar.");
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (File file : files) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }

                    zos.closeEntry();
                }
            }
        }
        System.out.println("Arquivos compactados em: " + zipFilePath);
    }
}
