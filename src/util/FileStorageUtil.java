package util;

import java.io.*;
import java.util.List;

public class FileStorageUtil {

    public static <T> void guardar(String caminho, List<T> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(caminho))) {
            out.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Erro ao guardar dados em " + caminho + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> carregar(String caminho) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(caminho))) {
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nenhum ficheiro encontrado ou erro ao carregar " + caminho + ". Inicializando lista vazia.");
            return new java.util.ArrayList<>();
        }
    }
}
