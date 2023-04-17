package ru.egor.graphics.image;
import java.util.*;

public class Schema implements TextColorSchema{
    List<Character> charList = List.of('▇', '●', '◉', '◍', '◎', '○', '☉', '◌', '-');
    @Override
    public char convert(int color) {
        if (color <= 28) {
            return charList.get(0);
        } else if (color <= 56) {
            return charList.get(1);
        } else if (color <= 84) {
            return charList.get(2);
        } else if (color <= 112) {
            return charList.get(3);
        } else if (color <= 140) {
            return charList.get(4);
        } else if (color <= 168) {
            return charList.get(5);
        } else if (color <= 196) {
            return charList.get(6);
        } else if (color <= 224) {
            return charList.get(7);
        } else {
            return charList.get(8);
        }
    }


}
