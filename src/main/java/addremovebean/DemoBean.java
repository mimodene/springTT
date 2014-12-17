package addremovebean;

import org.springframework.stereotype.Component;

/**
 * Created by MMO on 17.12.2014.
 */
@Component("demo")
public class DemoBean {


    private String name = "Tom";

    public void saySomething(){
        System.out.println("Demo bean is here: "+ toString());
        System.out.println("My Name is:  "+ name);
    }

    public void setName(String theName){
        name = theName;
    }
}
