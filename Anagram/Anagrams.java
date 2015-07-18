import java.util.*;
/**
** @author Subahu Rayamajhi
** @date 04/14/2015
**/

/**
** It uses a dictionary as a parameter and the object finds all the anagram phrases
** that match a given word or phrase with getWords method, and it can be used to print 
** anagrams with max number of words in it 
**/
public class Anagrams{

	private Set<String> dictionary;

	public Anagrams(Set <String> dictionary){
		if(dictionary==null)
			throw new IllegalArgumentException();
		else
			this.dictionary = dictionary;
	}

	// returns a set containing all words from the dict that can be made using some or all of the letters in the given phrase
	public Set<String> getWords(String phrase){
		Set<String> lettersInPhrase = new TreeSet<String>(); 
		LetterInventory letters = new LetterInventory(phrase);
		Iterator<String> itr = dictionary.iterator();
		//System.out.println(letters);
		if(phrase==null){
			throw new IllegalArgumentException();
		}
		else{
			while(itr.hasNext()){
				String currentWord = itr.next();
				if(letters.contains(currentWord))
					lettersInPhrase.add(currentWord);
			}
			return lettersInPhrase;
		}

	}

	// find all the anagrams
	public void print(String phrase){
		if (phrase==null)
			throw new IllegalArgumentException();
		else
			print(phrase,0);
	}

	// If there are no more choices to make then stop and print chosen, else 
	// Makes a single choice from the set of choices and remove it from the choices,
	// The method then explores the remaining choices and backtrack
	private void anagrams(LetterInventory letters, Set<String> choice, LinkedList<String> chosen, int max){
		if(letters.isEmpty()){ 
			if (max==0)
				System.out.println(chosen);
			else if (chosen.size()<=max)
				System.out.println(chosen);
		}
		else{
			Iterator<String> itr = choice.iterator();
			while(itr.hasNext()){
				String currentChoice = itr.next();
				if(letters.contains(currentChoice)){
					LetterInventory remainingLetters = new LetterInventory(letters);
					remainingLetters.subtract(currentChoice);
					chosen.add(currentChoice);
					anagrams(remainingLetters,choice,chosen,max);
					int endOfList = chosen.size()-1;
					chosen.remove(endOfList);
				}
			}
		}

	}

	// A function that prints anagrams according to max given
	// If max is 0 it will print out all the anagrams
	public void print(String phrase, int max){
		if (phrase==null||max<0){
			throw new IllegalArgumentException();
		}
		else{
			LetterInventory letters = new LetterInventory(phrase);
			LinkedList<String> chosen = new LinkedList<String>();
			Set<String> choice = getWords(phrase);
			anagrams(letters,choice,chosen,max);
		}
	}

}