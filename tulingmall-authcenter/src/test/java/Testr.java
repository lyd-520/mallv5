import com.tuling.TulingmallAuthcenterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TulingmallAuthcenterApplication.class)

public class Testr {

// @Autowired
// private PasswordEncoder passwordEncoder;


    public static void main(String[] args){
        String  secret = new BCryptPasswordEncoder().encode("toLogin");
        System.out.println(">>>>>>>>>>>>>>>>"+secret);
 }

//    public static void main(String[] args) {
//        Boolean  secret = new BCryptPasswordEncoder().matches("nacos","$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu");
//        System.out.println(">>>>>>>>>>>>>>>>"+secret);
//    }

}




