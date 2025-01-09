package by.it.group351001.viktor.lesson15;

import java.io.*;
import java.nio.charset.MalformedInputException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class SourceScannerB {

    public static void main(String[] args) {
        // Получаем путь к каталогу src
        String src = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        List<FileData> fileDataList = new ArrayList<>();

        try {
            // Проходим по всем файлам в каталоге src и его подкаталогах
            Files.walk(Paths.get(src))
                    .filter(Files::isRegularFile) // Фильтруем только файлы
                    .filter(path -> path.toString().endsWith(".java")) // Фильтруем только файлы с расширением .java
                    .forEach(path -> {
                        try {
                            // Читаем все строки файла
                            List<String> lines = Files.readAllLines(path);
                            // Проверяем, содержит ли файл @Test или org.junit.Test
                            if (lines.stream().noneMatch(line -> line.contains("@Test") || line.contains("org.junit.Test"))) {
                                // Объединяем строки в один текст
                                String content = String.join("\n", lines);
                                // Удаляем строку package и все импорты
                                content = removePackageAndImports(content);
                                // Удаляем все комментарии
                                content = removeComments(content);
                                // Удаляем все символы с кодом <33 в начале и конце текстов
                                content = removeSpecialChars(content);
                                // Удаляем пустые строки
                                content = removeEmptyLines(content);
                                // Добавляем данные файла в список
                                fileDataList.add(new FileData(path, content.getBytes().length));
                            }
                        } catch (MalformedInputException e) {
                            // Игнорируем ошибки MalformedInputException
                            System.err.println("Ошибка чтения файла: " + path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            // Сортируем файлы по размеру, а если размер одинаковый, то лексикографически по пути
            fileDataList.sort(Comparator.comparingInt(FileData::getSize)
                    .thenComparing(fileData -> fileData.getPath().toString()));

            // Выводим размер и относительный путь к каждому файлу
            fileDataList.forEach(fileData ->
                    System.out.println(fileData.getSize() + " " + Paths.get(src).relativize(fileData.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления строки package и всех импортов
    private static String removePackageAndImports(String content) {
        return Arrays.stream(content.split("\n"))
                .filter(line -> !line.startsWith("package") && !line.startsWith("import"))
                .collect(Collectors.joining("\n"));
    }

    // Метод для удаления всех комментариев
    private static String removeComments(String content) {
        return content.replaceAll("(?s)/\\*.*?\\*/|//.*", "");
    }

    // Метод для удаления всех символов с кодом <33 в начале и конце текстов
    private static String removeSpecialChars(String content) {
        return content.replaceAll("^[\\x00-\\x20]+|[\\x00-\\x20]+$", "");
    }

    // Метод для удаления пустых строк
    private static String removeEmptyLines(String content) {
        return Arrays.stream(content.split("\n"))
                .filter(line -> !line.trim().isEmpty())
                .collect(Collectors.joining("\n"));
    }

    // Класс для хранения данных о файле
    private static class FileData {
        private final Path path;
        private final int size;

        public FileData(Path path, int size) {
            this.path = path;
            this.size = size;
        }

        public Path getPath() {
            return path;
        }

        public int getSize() {
            return size;
        }
    }
}
