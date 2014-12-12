package config;

import org.springframework.stereotype.Component;

/**
 * Created by MMO on 12.12.2014.
 */

@Component
public class Innerautowired {

    public void sayThis(){
        System.out.println(" this is the inner voice speaking: "+ toString());
    }
}
