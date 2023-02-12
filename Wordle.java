import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class Wordle {
    public static void main(String[] args){
        char guesses[][] = new char[2][5];
        Scanner input = new Scanner(System.in);

            //import wordlist
        ArrayList<String> words = new ArrayList<String>();
        try (Scanner file = new Scanner ( new File ("wordlist.txt"))){
            while(file.hasNext()){
                words.add(file.nextLine());
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found!");
        }
        // make another list with only 5 letter words
        ArrayList<String> fiveLetter = new ArrayList<String>();
        for ( int i = 0; i < words.size(); i++){
            if (words.get(i).length() == 5) fiveLetter.add(words.get(i)); //words.remove(i);
        }
                    // remove '
        for ( int i = 0; i < fiveLetter.size(); i++){
            if ( fiveLetter.get(i).contains("'")) {
                fiveLetter.remove(i);
                i--;
            }
        }
        String randWord = fiveLetter.get((int)(Math.random() * fiveLetter.size()));
        String user = "";

        // assign randword into array
        for ( int i = 0; i < 5; i++){
            guesses[0][i] = randWord.charAt(i);
        }
            //wordle logic
        do{
        // array to check if letter repeats, inside loop to empty array for next word
        boolean[] ifLetters = new boolean[26];

            System.out.print("Enter a guess: ");
            user = input.nextLine();

            //check if user guess is a word in the list
            while(!fiveLetter.contains(user)){
                System.out.println("Invalid entry!");
                user = input.nextLine();
            }
            // initialize user guess into array
            for ( int i = 0; i < 5; i++){
                guesses[1][i] = user.charAt(i);
            }

                for ( int r = 1; r < 2; r++){
                    for (int c = 0; c < 5; c++){
                    int arrLoc = guesses[r][c] - 'a';
                        // upper if its the correct letter in the correct spot
                    if ( guesses[r][c] ==guesses[r-1][c]){
                        // make true since letter is now in word
                        ifLetters[arrLoc] = true;
                        guesses[r][c] = Character.toUpperCase(guesses[r][c]);
                    }
                        // if letter in guess exists in word leave it alone else make -
                    else if (randWord.contains(Character.toString(guesses[r][c]))){
                        // if letter exists in array make -
                        if (ifLetters[arrLoc] == true) guesses[r][c] = '-';
                        // else change to true
                        else ifLetters[arrLoc] = true;
                    }
                    // if letter does not exist in word then make -
                    else guesses[r][c] = '-';
        }
    }
            for ( int i = 0; i < 5; i++) System.out.print(guesses[1][i]);
            System.out.println();
        }
        while(!user.equals(randWord));
        input.close();
    }
}