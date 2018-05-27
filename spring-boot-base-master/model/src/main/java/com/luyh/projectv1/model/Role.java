package com.luyh.projectv1.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ROLES", catalog = "")
@NamedQueries({
    @NamedQuery(name = "Role.findAll"     , query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findByIdRole", query = "SELECT r FROM Role r WHERE r.idRole = :idRole"),
    @NamedQuery(name = "Role.findByRole"  , query = "SELECT r FROM Role r WHERE r.role = :role")})
public class Role implements Serializable {
   
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROLE")
    @SequenceGenerator(name = "sec_roles", sequenceName = "SQ_ROLES", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sec_roles")
    private BigDecimal idRole;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ROLE")
    private String role;
    
    @JoinTable(name = "USERS_ROLES", joinColumns = {
        @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID_ROLE")}, inverseJoinColumns = {
        @JoinColumn(name = "USER_ID", referencedColumnName = "ID_USER")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> userList;

    public Role() {
    }

    public Role(BigDecimal idRole) {
        this.idRole = idRole;
    }

    public Role(BigDecimal idRole, String role) {
        this.idRole = idRole;
        this.role = role;
    }

    public BigDecimal getIdRole() {
        return idRole;
    }

    public void setIdRole(BigDecimal idRole) {
        this.idRole = idRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRole != null ? idRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Role[ idRole=" + idRole + " ]";
    }
    
}