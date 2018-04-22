package com.silenteight.task3;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

public final class CharacterValidator {

  private static final String RESPONSE_CORRECT = "TRUE";
  private static final String RESPONSE_INCORRECT = "FALSE";
  private static final String INPUT_REGEX = "[0-9()\\[\\]]+";
  private static final char[] OPEN_CHARACTER = {'[', '('};
  private static final char[] CLOSE_CHARACTER = {']', ')'};

  public String validate(final String input) {
    valid(input);

    Deque<Character> deque = new ArrayDeque<>();
    for (char element : input.toCharArray()) {
      if (getPositionInOpenCharacter(element) != -1) {
        deque.addLast(element);
      } else {
        int closePosition = getPositionInCloseCharacter(element);
        if (closePosition != -1 &&
            OPEN_CHARACTER[closePosition] == deque.peekLast()) {
          deque.pollLast();
        }
      }
    }

    String response = deque.isEmpty() ?
        RESPONSE_CORRECT :
        RESPONSE_INCORRECT;
    System.out.println(response);

    return response;
  }

  private void valid(String input) {
    if (!Pattern.compile(INPUT_REGEX)
        .matcher(input)
        .matches()) {

      throw new InputMismatchException("Incorrect input!");
    }
  }

  private int getPosition(char element, char[] collection) {
    for (int i = 0; i < collection.length; i++) {
      if (collection[i] == element) {
        return i;
      }
    }
    return -1;
  }

  private int getPositionInOpenCharacter(char element) {
    return getPosition(element, OPEN_CHARACTER);
  }

  private int getPositionInCloseCharacter(char element) {
    return getPosition(element, CLOSE_CHARACTER);
  }
}