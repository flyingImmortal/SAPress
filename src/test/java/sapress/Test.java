package sapress;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

public class Test {

    public static void main(String[] arg) {
        get();
        
    }
    private static void get(){
        int g=6,s=0,sum=0;
        do
        {
             g=g*3+s;
             s=g/10;
             g=g%10;
             sum++; 
        }while(g!=6||s!=0);
        System.out.println(sum);
    }
    private static void get2(){
        int i=6;
        BigInteger a= new BigInteger("1");
        while(true){
            DecimalFormat df = new DecimalFormat();
            String b=a.toString()+"8"+i;
            String c=i+""+String.valueOf(a)+"8";
            System.out.println(b+"与"+c);
            BigInteger d=new BigInteger(b);
            BigInteger e=d.multiply(new BigInteger("3"));
            System.out.println("*3后"+e);
            System.out.println("还相差"+e.subtract(new BigInteger(c)));
            if(c.equals(String.valueOf(e))){
                System.out.println("最后是"+b);
                break;
            }
            a=a.add(new BigInteger("1"));
            if(a.compareTo(new BigInteger("10000"))>0){
                break;
            }
        }
    }
}
