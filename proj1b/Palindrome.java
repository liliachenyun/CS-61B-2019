public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        String actual = reverseword(deque);
        return actual.equals(word);
    }

    private String reverseword(Deque<Character> D) {
        if (D.isEmpty()) {
            return "";
        } else {
            return (D.removeLast() + reverseword(D));
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        for (int i = 0; i < word.length() / 2; i++) {
            if (!cc.equalChars(word.charAt(i), deque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
