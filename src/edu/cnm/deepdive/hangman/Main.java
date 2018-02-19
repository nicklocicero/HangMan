package edu.cnm.deepdive.hangman;

import java.util.Scanner;

public class Main {

  public static void main(String[] args){
    Game game = new Game();
    Scanner s = new Scanner(System.in);
    while (true){
      game.newWord();
      while(true){
        if (game.isOver()){
          System.out.println(game.status());
          break;
        }else {
          System.out.println(game.status());
          String str = s.nextLine().toLowerCase();
          if (str.length() == 0){
            System.out.println("Sorry, couldn't read that");
          }else if (Character.isLetter(str.charAt(0))) {
            char letter = str.charAt(0);
            game.guess(letter);
          }else{
            System.out.println("Not a letter");
          }
        }
      }
    }
  }

}
