package dev.com.infraestructure.adapter.out.storage;

import dev.com.application.ports.out.ImagenOutPort;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@ApplicationScoped
public class ImagenStorageAdapter implements ImagenOutPort {

    private static final Path DIRECTORIO =
            Paths.get("uploads", "cursos");

    @Override
    public String guardar(FileUpload imagen) throws IOException {

        Files.createDirectories(DIRECTORIO);
        String nombreOriginal = imagen.fileName();
        String extension = obtenerExtencion(nombreOriginal);
        String nombreUnico = UUID.randomUUID() + extension;
        Path destino = DIRECTORIO.resolve(nombreUnico);
        Path origen = imagen.uploadedFile();

        Files.copy(
                origen,
                destino,
                StandardCopyOption.REPLACE_EXISTING
        );

        return nombreUnico;
    }
    private String obtenerExtencion(String nombreArchivo) {
        int indice = nombreArchivo.lastIndexOf('.');
        if (indice == -1){
            return "";
        }
        return nombreArchivo.substring(indice);
    }
}
