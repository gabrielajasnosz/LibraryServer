package com.demo.springboot.model;

import javax.persistence.*;
import java.io.Serializable;
/** Specifies the entity "Admin" for the JPA.
 */

@Entity(name = "admin")
@Table(name = "admin")
public class Admin implements Serializable {
    /** Represents the admin's id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id", nullable = false, updatable = false)

    private Long adminId;
    /** Represents the admin's login.
     */
    private String login;
    /** Represents the admin's password.
     */
    private String password;

    /** Gets the admin's id.
     * @return A Long representing the admin's id
     */
    public Long getAdminId() {
        return adminId;
    }

    /** Gets the admin's login
     * @return A string representing the admin's login
     */
    public String getLogin() {
        return login;
    }

    /** Gets the admin's password.
     * @return A string representing the admin's password
     */
    public String getPassword() {
        return password;
    }


}
