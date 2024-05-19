package spring.learn.spring.model;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "app_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "user_name" }),
        @UniqueConstraint(columnNames = { "EMAIL" })
})
@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;
    @Basic(optional = false)
    @Column(name = "USER_NAME", nullable = false, length = 255)
    private String username;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL", length = 255)
    private String userEmail;
    private String comment;
    @Column(name = "status")
    private short status;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "last_update_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateOn;
    @Column(name = "PhoneNumber")
    private Integer phoneNumber;
    @OneToMany(mappedBy = "userRoleId.user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<UserRole> usersRolesList;
    @Transient
    private List<String> roleList;
    @Transient
    private List<String> permissionList;

    @Column(name = "date_naissance")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNaissance;
    @Column(name = "Lieu_de_naissance")
    private String lieuDeNaissance;
    @Column(name = "Profile_picture")
    private String photo;


    @Lob
    @Column(name = "list_emails")
    private String emailList;

    
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void setEmailList(String emailList) {
        this.emailList = emailList;
    }

    public User() {
    }

    public User(Integer userId) {
        this.id = userId;
    }

    public User(String password, String username, String userEmail, Date createdOn) {
        this.password = password;
        this.username = username;
        this.userEmail = userEmail;
        this.createdOn = createdOn;
    }

    public User(String password, String username, String userEmail, String firstName, String lastName, Date createdOn) {
        this.password = password;
        this.username = username;
        this.userEmail = userEmail;
        this.createdOn = createdOn;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String password, String username, String userEmail, String firstName, String lastName,
            Integer id, String emailList) {
        this.password = password;
        this.username = username;
        this.userEmail = userEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.emailList = emailList;
    }

    

    public User(Integer id, String password, String username, String firstName, String lastName, String userEmail,
            String comment, short status, Date createdOn, Date lastUpdateOn, Integer phoneNumber,Date dateNaissance,
            String lieuDeNaissance, String photo, String emailList) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.comment = comment;
        this.status = status;
        this.createdOn = createdOn;
        this.lastUpdateOn = lastUpdateOn;
        this.phoneNumber = phoneNumber;
        this.dateNaissance = dateNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.photo = photo;
        this.emailList = emailList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastUpdateOn() {
        return lastUpdateOn;
    }

    public void setLastUpdateOn(Date lastUpdateOn) {
        this.lastUpdateOn = lastUpdateOn;
    }

    @XmlTransient
    public List<UserRole> getUsersRolesList() {
        return usersRolesList;
    }

    public void setUsersRolesList(List<UserRole> usersRolesList) {
        this.usersRolesList = usersRolesList;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    @JsonIgnore
    public List<String> getUserRoleList() {
        if (roleList == null)
            roleList = new ArrayList<>();
        usersRolesList.forEach(userRole -> {
            if (!roleList.contains(userRole.getRole().getRoleName())) {
                roleList.add(userRole.getRole().getRoleName());
            }
        });
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    @JsonIgnore
    public List<String> getUserPermissionList() {
        if (permissionList == null)
            permissionList = new ArrayList<>();
        usersRolesList.forEach(userRole -> {
            userRole.getRole().getRolePermissionList().forEach(rolePermission -> {
                if (!permissionList.contains(rolePermission.getPermission().getPermissionName())) {
                    permissionList.add(rolePermission.getPermission().getPermissionName());
                }
            });
        });
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    public List<String> getEmailList() {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(emailList, listType);
    }

    public void setEmailList(List<String> emails) {
        Gson gson = new Gson();
        String json = gson.toJson(emails);
        emailList = json;
    }
}
