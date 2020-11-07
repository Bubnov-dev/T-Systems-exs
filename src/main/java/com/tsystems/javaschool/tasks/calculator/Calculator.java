package com.tsystems.javaschool.tasks.calculator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.min;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        try {
            statement.replaceAll(" ", "");
            return step3(step2(step1(step0(statement))));
        } catch (Exception e) {
            return null;
        }
    }

    private String step0 (String statement){ //brackets
        try {
            int start_index = statement.indexOf('(');
            int end_index;
            String part2;
            String res;
            try {
                part2 = getPart(statement, start_index);
            }
            catch (BracketException e) {
                return null;
            }
            if (part2!=null) {
                end_index = start_index+part2.length();
                String part1 = statement.substring(0, start_index);
                String part3 = (statement.substring(end_index + 2));
                res = part1 + evaluate(part2) + step0(part3);
            } else res = statement;
            return res;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    private String step1 (String statement) throws Exception { // '*' and '/'
        if (statement==null) return null;
        List<String> statementArr =mySplit(statement, new char []{'*', '/', '+', '-'});//not working like i wanted: Arrays.asList(statement.split("(?=\\*|\\+|-|\\/)"));
        if (statementArr==null || statementArr.toArray().length == 0) return null; //todo good check for uncompleted nums;
        int index = getFirstIndex(statementArr.indexOf("*"), statementArr.indexOf("/"));
        String res;
        if (index!=-1) {
            String part1 = String.join("", statementArr.subList(0, index - 1));
            String part2 = step2(String.join("", statementArr.subList(index-1, index + 2)));
            String part3 = step1(String.join("", statementArr.subList(index + 2, statementArr.toArray().length)));
            if (part2 == null) return  null;
            res = String.join("", part1,part2,part3);
        }
        else res = statement;
        return res;
    }
    private String step2 (String statement) throws Exception { //simple calc
        if (statement==null || statement == "") return null;
        String res = "";
        List<String> statementArr = mySplit(statement, new char [] {'+', '-', '*', '/'});
        if (statementArr.toArray().length >2) { //todo: normal check
            Iterator<String> iter = statementArr.listIterator();
            try {
                Float first = Float.parseFloat(iter.next());
                String operation = (iter.next());
                Float second = Float.parseFloat(iter.next());
                switch (operation) {
                    case "+":
                        res += (Float.toString(first + second));
                        break;
                    case "-":
                        res += (Float.toString(first - second));
                        break;
                    case "*":
                        res += (Float.toString(first * second));
                        break;
                    case "/":
                        if (second == 0.0) return null;
                        res += (Float.toString(first / second));
                        break;
                    default:
                        throw new Exception("no such operation: " + operation);
                }
                if (iter.hasNext())
                    res = step2(res += String.join("", (statementArr.subList(3, statementArr.toArray().length))));
                return res;
            }
            catch (NumberFormatException e){
                return null;
            }
        }
        else return statement;
    }
    private String step3 (String statement){ //simplifying
        if (statement==null) return null;
        statement = String.format("%.4f", Float.parseFloat(statement)).replaceAll(",", ".");
        int index = statement.indexOf('.');
        if (index == -1) return statement;
        else{
            index=statement.length()-1;
            while (statement.charAt(index)=='0'){
                index--;
            }
            if (statement.charAt(index) =='.'){
                index--;//todo костыль - хотя не, норм
            }
        }
        return statement.substring(0, index+1);
    }
    private int getFirstIndex(int a, int b){
        if (a==-1) return b;
        else if(b==-1) return a;
        else return min(a,b);
    }
    private List<String> mySplit(String statement, char [] del){
        List <String> res = new ArrayList<>();
        int tmp=0;
        for(int i=1; i<statement.length(); i++){
                if (isDel(del, statement.charAt(i))){
                    res.add(statement.substring(tmp, i));
                    res.add(String.valueOf(statement.charAt(i)));
                    i++;
                    if (statement.charAt(i-1) == statement.charAt(i)) return null;
                    tmp=i;
                }

        }
        res.add(statement.substring(tmp));
        return res;
    }
    private boolean isDel (char [] del, char ch){
        for (char sign: del) if (ch==sign) return true;
        return false;
    }
    private String getPart(String statement, int start) throws BracketException {
        StringBuilder res = new StringBuilder();
        int balance=1;
        int index = start;
        if (start!=-1){
            while (balance!=0) {
                index++;
                if (index==statement.length()) throw new BracketException();
                char tmp = statement.charAt(index);
                if (tmp == '(') balance++;
                else if (tmp == ')') balance--;
                res.append(tmp);
            }
            res.deleteCharAt(index-start-1);
            return res.toString();
        }
        else return null;
    }
}