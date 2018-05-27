package com.luyh.projectv1.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERS", catalog = "")
@NamedQueries({
    @NamedQuery(name = "User.findAll"                   , query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdUser"              , query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "User.findByCedula"              , query = "SELECT u FROM User u WHERE u.cedula = :cedula"),
    @NamedQuery(name = "User.findByNombre"              , query = "SELECT u FROM User u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "User.findByApellido"            , query = "SELECT u FROM User u WHERE u.apellido = :apellido"),
    @NamedQuery(name = "User.findByDireccion"           , query = "SELECT u FROM User u WHERE u.direccion = :direccion"),
    @NamedQuery(name = "User.findByFechaNacimiento"     , query = "SELECT u FROM User u WHERE u.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "User.findByFechaIngreso"        , query = "SELECT u FROM User u WHERE u.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "User.findByFechaRetiro"         , query = "SELECT u FROM User u WHERE u.fechaRetiro = :fechaRetiro"),
    @NamedQuery(name = "User.findByTelefono"            , query = "SELECT u FROM User u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "User.findByEmail"               , query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByLogin"               , query = "SELECT u FROM User u WHERE u.login = :login"),
    @NamedQuery(name = "User.findByPassword"            , query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEnable"              , query = "SELECT u FROM User u WHERE u.enable = :enable"),
    @NamedQuery(name = "User.findByAccountNonExpired"   , query = "SELECT u FROM User u WHERE u.accountNonExpired = :accountNonExpired"),
    @NamedQuery(name = "User.findByCredentialNonExpired", query = "SELECT u FROM User u WHERE u.credentialNonExpired = :credentialNonExpired"),
    @NamedQuery(name = "User.findByAccountNonLocked"    , query = "SELECT u FROM User u WHERE u.accountNonLocked = :accountNonLocked")})
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USER")
    @SequenceGenerator(name = "sec_user", sequenceName = "SQ_USERS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sec_user")
    private BigDecimal idUser;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CEDULA")
    private BigInteger cedula;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "APELLIDO")
    private String apellido;
    
    @Size(max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    
    @Column(name = "FECHA_RETIRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRetiro;
    
    @Size(max = 30)
    @Column(name = "TELEFONO")
    private String telefono;
    
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "LOGIN")
    private String login;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PASSWORD")
    private String password;
    
    @Size(max = 10)
    @Column(name = "ENABLE")
    private String enable;
    
    @Size(max = 10)
    @Column(name = "ACCOUNT_NON_EXPIRED")
    private String accountNonExpired;
    
    @Size(max = 10)
    @Column(name = "CREDENTIAL_NON_EXPIRED")
    private String credentialNonExpired;
    
    @Size(max = 10)
    @Column(name = "ACCOUNT_NON_LOCKED")
    private String accountNonLocked;
    
    @ManyToMany(mappedBy = "userList", fetch = FetchType.LAZY)
    private List<Role> roleList;
    
    public User() {
    }

    public User(BigDecimal idUser) {
        this.idUser = idUser;
    }

    public User(BigDecimal idUser, BigInteger cedula, String nombre, String apellido, String email, String login, String password) {
        this.idUser = idUser;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public BigDecimal getIdUser() {
        return idUser;
    }

    public void setIdUser(BigDecimal idUser) {
        this.idUser = idUser;
    }

    public BigInteger getCedula() {
        return cedula;
    }

    public void setCedula(BigInteger cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(String accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public String getCredentialNonExpired() {
        return credentialNonExpired;
    }

    public void setCredentialNonExpired(String credentialNonExpired) {
        this.credentialNonExpired = credentialNonExpired;
    }

    public String getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(String accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.User[ idUser=" + idUser + " ]";
    }
    
}