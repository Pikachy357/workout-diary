package sandbox;
import sandbox.tools.Secret;
public class Stranger {
    public static void main (String [] args){
        Secret s = new Secret();
       // System.out.println(s.privateField);
       // System.out.println(s.packageField);
       // System.out.println(s.protectedField);
        System.out.println(s.publicField);
    }
}
