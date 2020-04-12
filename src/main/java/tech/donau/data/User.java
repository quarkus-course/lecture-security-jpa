package tech.donau.data;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
@UserDefinition
public class User extends PanacheEntity {
    @Username
    public String username;
    @Password
    public String password;
    @Roles
    public String roles;

    public User() {
    }

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public static User add(String username, String password, String roles) {
        final User user = new User(username, BcryptUtil.bcryptHash(password), roles);
        user.persist();
        return user;
    }

}
