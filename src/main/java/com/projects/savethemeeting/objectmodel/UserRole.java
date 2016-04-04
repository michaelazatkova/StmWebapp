package com.projects.savethemeeting.objectmodel;

import javax.persistence.*;

@Entity
@Table(name = "user_role", schema = "public", catalog = "")
@SequenceGenerator(sequenceName = "public.user_role_id_user_role_seq", name = "userRoleSeq")
public class UserRole {
    @Id
    @Column(name = "id_user_role")
    @GeneratedValue(generator = "userRoleSeq")
    private Integer idUserRole;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

    public Integer getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(Integer userRoleId) {
        this.idUserRole = userRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
