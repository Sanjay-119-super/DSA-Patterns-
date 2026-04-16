package Two_Pointer_Pattern;

//DAY-2
public class Solution {
    public boolean isPalindrome(String s){
        // Use two Pointer i =0, j=n-1
        int i=0,
                j= s.length()-1;

        /*
        *  "A man, a plan, a canal: Panama"
        *   i                            j
        * remove non-alphanumeric chars
        *
        *  "amanaplanacanalpanama"
        *            ij
        *
        *
        * */
        while (i<j){
            char left = s.charAt(i),
                    right = s.charAt(j);
            // remove non-alphanumeric characters
            if (!Character.isLetterOrDigit(left)){
                i=i+1;
                continue;
            }
            if (!Character.isLetterOrDigit(right)){
                j--;
                continue;
            }
            //  uppercase -> lowercase
            if (Character.toLowerCase(left) != Character.toLowerCase(right)){
                return false;
            }
            i++;
            j--;

        }
        return true;

    }
    public void reverseString(char[] s){
        int i=0,
                j=s.length-1;
        /*DRY - RUN
        * {'o','l','l','e','h'}
        *           ij
        *
        * */
        while (i<j){
            char temp = s[i];
            s[i]=s[j];
            s[j]=temp;
            i++;
            j--;
        }
    }
    public int[] sortedSquares(int[] nums){
        int i=0,
                j=nums.length-1;
        int k = nums.length-1;

        int[] res = new int[nums.length];
        /*
        * [-4,-1,0,3,10]
        *      i j
        *  k
        *  0 1 9  16  100
        *
        * */
        while (i<j){
            if (Math.abs(nums[i]) > Math.abs(nums[j])){
                res[k]=nums[i]*nums[i];
                i++;

            }else {
                res[k]=nums[j]*nums[j];
                j--;

            }
            k--;

        }
        return res;
    }
    public boolean validPalindrome(String s){
        int i =0,
                j=s.length()-1;
        /*
        * "aba"
        *   j
        *   i no delete
        *
        * "abca"
        *   ij -> mismatch need to delete delete c = aba
        * "aba"
        *   j
        *   i -> valid palindrome
        *
        * */
        while (i<=j){
            if (s.charAt(i) == s.charAt(j)){
                i++;
                j--;
            }else {
                return isPalindrome(s,i+1,j) || isPalindrome(s,i,j-1);
            }
        }
        return true;
    }
    public static boolean isPalindrome(String s , int i , int j){
       while (i<=j){
           if (s.charAt(i) != s.charAt(j)){
               return false;
           }
           i++;
           j--;
       }

        return true;
    }
    public boolean validWordAbbreviation(String word, String abbr){
        int i=0,
                j=0;

        /*
        * word = "implementation"
        *                       i
        * abbr = "i12n"
        *            j
        *
        * index =0 match i++, j++
        * index =1 have number 12 create number
        * word: i m p l e m e n t a t i o n
                0 1 2 3 4 5 6 7 8 9 10 11 12 13

          Step 1: i=0, match 'i' -> i=1, j=1
          Step 2: abbr[j]='1', parse '12' -> curr=12, j=3, i=1+12=13
         (Skip karo 12 characters: m p l e m e n t a t i o)
         Step 3: abbr[j]='n', word[13]='n' match -> i=14, j=4
         *
         *
* while (j < abbr.length() && Character.isDigit(abbr.charAt(j)))
Yeh loop tab tak chalta hai jab tak digit milti rahe.
Example "12":
Pehle '1' dikha → curr = 0*10 + (1) = 1
Agla character '2' bhi digit hai → curr = 1*10 + (2) = 12
Agla character 'n' digit nahi hai → loop ruk gaya, curr = 12 final.
Isi ko kehte hain parse integer from string.
        * */
        while (i<word.length() && j<abbr.length()){
            char w_c = word.charAt(i);
            char a_c = abbr.charAt(j);

            if (Character.isDigit(a_c)){
                //case.1 0
                if (a_c == '0'){
                    return false;
                }
                //case.3 handle number
                int curr=0;
                while (j<abbr.length() && Character.isDigit(abbr.charAt(j))){
                    curr = curr*10+(abbr.charAt(j)-'0');
                    j++;
                }
                //add skip number in word
                i+=curr;

            }else {
                //case.2 mismatch
                if (a_c != w_c){
                    return false;
                }
                i++;
                j++;
            }
        }
        //return same length of both string after checking
       return i==word.length() && j==abbr.length();
    }
}

class CheckSolution{
    public static void main(String[] args) {
        Solution solution = new Solution();
        /*String s = "A man, a plan, a canal: Panama";
        System.out.println(solution.isPalindrome(s));*/

        /*char[] s = {'h','e','l','l','o'};
        solution.reverseString(s);
        for (char c : s){
            System.out.print(c +" ");
        }*/
        /*int[] nums={-4,-1,0,3,10};
        int[] ints = solution.sortedSquares(nums);
        for (int n : ints){
            System.out.print(n + " ");
        }*/

       /* String s = "abahf";
        System.out.println(solution.validPalindrome(s));*/

        String word = "implementationjg";
        String abbr = "i12n";
        System.out.println(solution.validWordAbbreviation(word,abbr));


    }
}