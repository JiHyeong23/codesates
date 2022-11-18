import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Arrays;

public class main {
  public static void main(String[] args) throws JsonProcessingException {
    stringifyJSON test = new stringifyJSON();
    System.out.println(test.stringify(new Object[]{1,true,"2",new int[]{1,2}}));
  }
}
