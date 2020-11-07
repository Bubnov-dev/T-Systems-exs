import com.tsystems.javaschool.tasks.calculator.Calculator;
import com.tsystems.javaschool.tasks.pyramid.PyramidBuilder;
import com.tsystems.javaschool.tasks.subsequence.Subsequence;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class myTest {
    public static void main(String[] args) {
        //String input = "5+41..1-6";
        String input = "10/(2-7+3)*4";
        Calculator calculator = new Calculator();
        Subsequence subsequence = new Subsequence();
        //calculator.evaluate(input);
        //List x = Stream.of(3, 9, 1, 5, 7).collect(toList());
        //List y = Stream.of(1, 2, 3, 4, 5, 7, 9, 20).collect(toList());
       // System.out.println(subsequence.find(x, y));
 //       System.out.println(calculator.mySplit("2*-3+-4", new char [] {'+', '-', '*', '/'}));
        //System.out.println(calculator.evaluate("(1-4/2)*2"));
       //System.out.println("answer: "+calculator.evaluate("2+3*4"));
    }
}
