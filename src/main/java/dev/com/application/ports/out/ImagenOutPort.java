package dev.com.application.ports.out;

import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.IOException;

public interface ImagenOutPort {

    String guardar(FileUpload imagen) throws IOException;
}
