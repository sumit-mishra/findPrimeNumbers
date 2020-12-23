import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PrimeNumberFinder {

  public static void main(String[] args) {
    PrimeNumberFinder finder = new PrimeNumberFinder();
    finder.printPrimeNumbers2(20);
  }

  // First attempt
  public void printPrimeNumbers(final int limit) {
  
    List<Integer> allNumbers = new CopyOnWriteArrayList<>();
    if (limit < 2) {
      System.out.println("N/A");
      return;
    }

    for (int i = 2; i <= limit; i++) {
      allNumbers.add(i);
    }

    for (int number = 2; number <= limit; number++) {
      int multipleOfNumber = 0;
      for (int index = 2; multipleOfNumber <= limit; index++) {
        multipleOfNumber = index * number;
        if (allNumbers.contains(multipleOfNumber)) {
          allNumbers.remove(Integer.valueOf(multipleOfNumber));
        }
      }
    }
    System.out.println(allNumbers);
  }

  // Second attempt (but this visits the updated indexes again)
  public void printPrimeNumbers1(final int limit) {
    byte[] allNumbers = new byte[limit];
    for (int number = 2; number <= limit; number++) {
      int multipleOfNumber = 0;
      for (int index = 1; multipleOfNumber <= limit; index++) {
        multipleOfNumber = (index + 1) * number; // basically I am multiplying the number with itself (square) and
        // then index can incremented by the sum of number with itself.
        if (multipleOfNumber <= limit) // this is a check to avoid the array index out of bound
          allNumbers[multipleOfNumber - 1] = 1;
      }
    }
    for (int index = 0; index < allNumbers.length; index++) {
      byte value = allNumbers[index];
      if (index > 0 && value != 1) {
        System.out.println(index + 1);
      }
    }
  }

  // Third attempt - enhancement of the sencond
  public void printPrimeNumbers2(final int limit) {
    byte[] allNumbers = new byte[limit + 1]; // +1 is just to avoid the array index out of bound

    for (int number = 2; number * number <= limit; number++) {
      if (allNumbers[number] == 0) { // if the value at this index is 0, then it's multiple would be non-prime so updating them to 1
        for (int index = number * number; index <= limit; index = index + number)
          allNumbers[index] = 1;
      }
    }
    for (int number = 2; number <= limit; number++) {
      if (allNumbers[number] == 0)
        System.out.println(number);
    }
  }

}
