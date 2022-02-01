package pl.kurs.java;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        new FacebookEncoderApp().start();
  }


}
/*

napisz aplikacje ktora:
poprosi uzytkownika o siezke do plikow (folderu z dana konwersacja)
wyswiteli menu:
1. wyswietl ilosc wiadomsoci per osoba
2. wyswietl ilosc slow per osoba
3. najczesneij uzywane slowo per osoba
4. najczesneij uzywana emotka per osoba
5. sktywnosc (ilosc wiadomosci) per dzien tygodnia
6. aktywnosc per mkiesiac
7. aktywnosc per rok


 */