package bean;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@ManagedBean("authBean")
@SessionScoped
public class AuthBean implements Serializable {
    private String username;
    private String password;
}
