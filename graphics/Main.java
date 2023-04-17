package ru.egor.graphics;

import ru.egor.graphics.image.Converter;
import ru.egor.graphics.image.TextGraphicsConverter;
import ru.egor.graphics.server.GServer;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем


//         Или то же, но с выводом на экран:
//        converter.setMaxHeight(300);
//        converter.setMaxWidth(300);
//        converter.setMaxRatio(4);
//        String url = "https://wallbox.ru/resize/640x480/wallpapers/main/201252/3d0c7ca9450ad70.jpg";
//        String imgTxt = converter.convert(url);
//        System.out.println(imgTxt);

    }
}
