package com.example.Chibi.model;

import org.springframework.http.MediaType;

public enum ExtratoFileFormat {
    PDF("pdf"),
    TXT("txt");

    public final String formatName;

    ExtratoFileFormat(String formatName) {
        this.formatName = formatName.toLowerCase();
    }

    public MediaType getMediaType() {
        if (this == ExtratoFileFormat.PDF) {
            return MediaType.APPLICATION_PDF;
        }
        return MediaType.TEXT_PLAIN;
    }

    public String getFileName(String filename) {
        return filename + "." + formatName;
    }
}
