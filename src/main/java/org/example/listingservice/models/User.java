package org.example.listingservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@Builder
public class User extends BaseEntity implements UserDetails {
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Building> buildings = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name="phone", unique= true)
    private String phone;

    @Column(name="avatar")
    private String avatar;

    @ManyToOne()
    @JoinColumn(name="role_id")
    private Role role;

    @Column(name="used_posts")
    private Long usedPost;

    @Column(name="remain_posts")
    private Long remainPost;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getCode().toUpperCase()));
        return authorityList;
    }

    @Override
    public String getUsername() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString(){
        return "";
    }


    public String toHTMLBody() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>User Profile</title>\n" +
                "    <style>\n" +
                "        body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }\n" +
                "        .container { max-width: 800px; margin: auto; background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); }\n" +
                "        h1 { text-align: center; color: #333; }\n" +
                "        table { width: 100%; border-collapse: collapse; margin-top: 20px; }\n" +
                "        table, th, td { border: 1px solid #ddd; }\n" +
                "        th, td { padding: 10px; text-align: left; }\n" +
                "        th { background-color: #f2f2f2; }\n" +
                "        .avatar { text-align: center; margin-top: 20px; }\n" +
                "        .avatar img { border-radius: 50%; max-width: 150px; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <h1>Thông tin người dùng</h1>\n" +

                "        <table>\n" +
                "            <tr><th>Họ và tên</th><td>" + this.getFullName() + "</td></tr>\n" +
                "            <tr><th>Email</th><td>" + this.getEmail() + "</td></tr>\n" +
                "            <tr><th>Số điện thoại</th><td>" + this.getPhone() + "</td></tr>\n" +
                "            <tr><th>Trạng thái tài khoản</th><td>" + (this.getStatus() == 1 ? "Hoạt động" : "Không hoạt động") + "</td></tr>\n" +
                "            <tr><th>Bài viết đã sử dụng</th><td>" + this.getUsedPost() + "</td></tr>\n" +
                "            <tr><th>Bài viết còn lại</th><td>" + this.getRemainPost() + "</td></tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }


}
