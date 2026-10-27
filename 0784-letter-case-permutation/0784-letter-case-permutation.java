class Solution {
    public List<String> letterCasePermutation(String s) {
        ArrayList<String> res=new ArrayList<>();
        myfn(s.toCharArray(),0,res);
        return res;
    }
       void myfn(char[] arr,int index,List<String> res){
        if(index==arr.length){
        res.add(new String(arr));
        return;
        }
        if(Character.isDigit(arr[index])){
            myfn(arr,index+1,res);
            return;
        }
        arr[index]=Character.toLowerCase(arr[index]);
        myfn(arr,index+1,res);

        arr[index] = Character.toUpperCase(arr[index]);
        myfn(arr,index+1,res);
       } 
}