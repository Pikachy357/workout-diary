package sandbox.tools;
public class Neighbor {
    public static void main(String [] args){
        Secret s = new Secret();
        //System.out.println(s.privateField);
        System.out.println(s.packageField);
        System.out.println(s.protectedField);
        System.out.println(s.publicField);
        Secret a = new Secret();
        Secret b = new Secret();

        a.protectedField = "изменено у a";

        System.out.println(a.protectedField);
        System.out.println(b.protectedField);

    }
}
