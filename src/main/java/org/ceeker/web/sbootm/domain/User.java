package org.ceeker.web.sbootm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2459687804504582479L;

    @Id
    @GeneratedValue
    @XmlElement(name = "id")
    private int id;
    @Column(nullable = false)
    @Size(min = 6, max = 20, message = "在{min}-{max}个字符之间！")
    @XmlElement(name = "username")
    private String username;
    @Column(nullable = false)
    @Size(min = 6, max = 20, message = "在{min}-{max}个字符之间！")
    @XmlElement(name = "password")
    private String password;


}
