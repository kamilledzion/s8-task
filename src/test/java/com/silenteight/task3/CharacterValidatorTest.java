package com.silenteight.task3;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.InputMismatchException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CharacterValidatorTest {

  private CharacterValidator validator;

  public CharacterValidatorTest() {
    this.validator = new CharacterValidator();
  }

  @Parameters( {
      "(([34[34]])234)",
      "([([()])])",
  })
  @Test
  public void validateShouldReturnTrue(final String input) {
    String response = validator.validate(input);
    assertThat(response, is("TRUE"));
  }

  @Parameters( {
      "([6)",
      "([3)]",
      "(]",
  })
  @Test
  public void validateShouldReturnFalse(final String input) {
    String response = validator.validate(input);
    assertThat(response, is("FALSE"));
  }

  @Parameters( {
      "",
      "[ab]",
      "[ba",
      "abc",
  })
  @Test
  public void validateShouldThrowIllegalArgumentException(final String input) {
    try {
      validator.validate(input);
      fail();
    } catch (InputMismatchException e) {
      assertThat(e.getMessage(), is("Incorrect input!"));
    } catch (Exception e) {
      fail();
    }
  }
}