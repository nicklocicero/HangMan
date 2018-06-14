package edu.cnm.deepdive.hangman;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Game {

  private static final int TOTAL_GUESSES = 10;

  private static List<String> word_list;

  private String current_word;
  private StringBuilder sb_display;

  private Set<Character> guessed_letters;

  static {
    loadWords();
  }

  private int guesses_left;

  public void newWord(){
    Random rnd = new Random();
    int i = rnd.nextInt(word_list.size());
    current_word = word_list.get(i);
    sb_display = new StringBuilder();
    for (int j = 0; j < current_word.length(); j++) {
      sb_display.append('_');
    }
    guesses_left = TOTAL_GUESSES;
    guessed_letters = new HashSet<>();
  }

  private static void loadWords(){
    try {
      word_list = Files.readAllLines(new File("words.txt").toPath());
    } catch (IOException e) {
      throw new RuntimeException("Error reading words file",e);
    }
  }

  public String status() {
    if (guesses_left < 1){
      return current_word + "\t LOST!";
    }else if (sb_display.indexOf("_") == -1){
      return current_word + "\t WON!";
    }
    StringBuilder guesses_status = new StringBuilder(
	"\tGuesses left: " + Integer.toString(guesses_left));
    guesses_status.append("|");
    return sb_display.toString() + guesses_status.toString();
  }

  public void guess(char letter) {
    if (guessed_letters.contains(letter)) {
      return;
    }
    guessed_letters.add(letter);
    if (current_word.indexOf(letter) != -1){
      for (int i = 0; i < sb_display.length(); i++) {
        if (letter == current_word.charAt(i)){
          sb_display.setCharAt(i,letter);
        }
      }
    }else{
      guesses_left--;
    }
  }

  public boolean isOver() {
    return guesses_left < 1 || sb_display.indexOf("_") == -1;
  }
}
