/************************************************************
 * Queens.java
 * Guita Vahdatinia
 * gvahdati
 * pa5
 * Determine the number of solutions for a Queens board of 
 * n by n. 
 ************************************************************/
import java.util.Scanner;
import java.lang.Integer;
import java.util.Arrays;
import java.util.ArrayList; 
class Queens{
    public static void main (String[] args){
	Scanner sc = new Scanner(System.in);
	int  pivot, successor;
	int num_sol = 0;
	int fact = 1;
	int side = 0;
	boolean flag = false; 
	if(args.length == 0 || args.length>2){
	    System.out.println("Usage: Queens[-v] number");
	    System.out.println("option: -v verbose output, print all solutions");
	    System.exit(0);
	}
	else if(args.length==1){
	    try{
		side = Integer.parseInt(args[0]);
	    }
	    catch (NumberFormatException nfe){
		System.out.println("Usage: Queens[-v] number");
		System.out.println("option: -v verbose output, print all solutions");
		System.exit(0);	
	    }
	}
	else{
	    flag = "-v".equals(args[0]); 
	    try{
	    side = Integer.parseInt(args[1]);
	    }
	    catch(NumberFormatException nfe){
		System.out.println("Usage: Queens[-v] number");
		System.out.println("option: -v verbose output, print all solutions");
		System.exit(0);
	    }
	}
	int [] A = new int [side+1];
	for(int i =0; i<side+1; i++){
	    A[i] = i;
	}
	for(int i =1; i<=side;i++){
	    fact*=i;
	}
	for(int i = 1; i<fact;i++){
	    nextPermutation(A);
	    if(isSolution(A)){
		if(flag){
		    printer(A);
		}
		num_sol++;
	    }
	}
	System.out.println(side+ "-Queens has "+num_sol+ " solutions");
    }		      
    static void reverse(int[] A, int i, int j) {
	while(i<j){
	    swap(A, i, j);
	    j--;
	    i++;
	}
    }
    static void printer (int []A){
	System.out.print("(");
	for(int i = 1; i<A.length-1; i++){
	    System.out.print(A[i]+", ");
	}
	System.out.print(A[A.length-1]);
	System.out.print(")\n");
    }
    static void swap(int[] A, int i, int j){
	int temp = A[i];
	A[i] = A[j];
	A[j] = temp;
    }
    static void nextPermutation(int[] A){
	int successor = 0;
	int pivot = 0; 
	for (int i = A.length-2; i>0; i--){
	    if(A[i]<A[i+1]){
		pivot = i;
		break; 
	    }
	}
	if(pivot == 0){
	    reverse(A,1, A.length-1);
	    return;
	}
	for(int i = A.length-1; i>pivot; i--){
	    if(A[i]>A[pivot]){
		successor = i; 
		break; 
	    }
	}
	swap(A, successor, pivot);
	reverse(A, pivot+1,A.length-1); 
	return;
    }
    static boolean isSolution(int[] A){
	for(int i = 2; i<A.length; i++){
	    for(int j = 1; j<i; j++){
		if((A[i]-A[j]) ==(i-j)){
		    return false;
		}else if ((A[j] - A[i]) == (i-j)){
		    return false;
		}
	    }
	}
	return true; 
    }
}