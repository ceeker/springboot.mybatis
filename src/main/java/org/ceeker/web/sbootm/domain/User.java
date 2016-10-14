package org.ceeker.web.sbootm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

@Alias("User")
@Entity
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2459687804504582479L;

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    @Size(min = 6, max = 20, message = "在{min}-{max}个字符之间！")
    private String username;
    @Column(nullable = false)
    @Size(min = 6, max = 20, message = "在{min}-{max}个字符之间！")
    private String password;

    public User() {
        super();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
    }

}
