package sandbox;

import sandbox.tools.Secret;

public class Child extends Secret {

    void show() {
       // System.out.println(privateField);    // а)
      //  System.out.println(packageField);    // б)
        System.out.println(protectedField);  // в)
        System.out.println(publicField);     // г)

        Secret stranger = new Secret();
      //  System.out.println(stranger.privateField);    // а)
       // System.out.println(stranger.packageField);    // б)
       // System.out.println(stranger.protectedField);  // в)
        System.out.println(stranger.publicField);     // г) // д)

        Child brother = new Child();
      //  System.out.println(brother.privateField);    // а)
      //  System.out.println(brother.packageField);    // б)
        System.out.println(brother.protectedField);  // в)
        System.out.println(brother.publicField);  // е)
    }
    public static void main(String [] args){
        new Child().show();
    }
}