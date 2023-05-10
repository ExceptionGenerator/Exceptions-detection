package com.shiv.exception.be;

public class RacroTest {
    public static void main(String[] args) {
        System.out.println(checkPalindrome("Yo, banana boy!"));
    }

    /**
     * Need to check if sentence is palindrome or not
     * string 'Yo, banana boy!' consider as a palindrome
     * exclude comma(,) and explanatory(!)
     * i.e. 'Yo, banana boy!'
     * solution -> exclude the commas,spaces and symbols then it will be like this 'Yobananaboy'
     * solution -> reverse the entire string then it will be like 'yobananaboy'
     * @param string
     * @return
     */
    public static String checkPalindrome(String string){
        StringBuilder stringBuilder=new StringBuilder();
        for(char ch:string.toCharArray())
            if(Character.isAlphabetic(ch))
                stringBuilder.append(ch);
        return stringBuilder.toString().equalsIgnoreCase(stringBuilder.reverse().toString()) ? "Palindrome":"Not Palindrome";
    }
}
