package org.ceeker.web.sbootm.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("User")
public class User implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 2459687804504582479L;
    
    private String userName;
    
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "User [userName=" + userName + ", passWord=" + passWord + "]";
    }
    

}
