
import controllers.CustomerController;
import controllers.ShoesController;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> hs=new HashSet<>();
        hs.add(CustomerController.class);
        hs.add(ShoesController.class);
        hs.add(MultiPartFeature.class);
        return hs;
    }
}        //Qiyn joly!!! To do this thing you need to extend from Application
/*
@ApplicationPath("/")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("controllers");
        packages("filters");
    }
}*/
