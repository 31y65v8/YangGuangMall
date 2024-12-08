package org.wxl.ygmall.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// domain包存放实体类
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private LocalDateTime registdate;  // 修改为 LocalDateTime

    // Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getRegistdate() {
        return registdate;
    }

    // 格式化并返回注册时间
    public String getFormattedRegistdate() {
        if (registdate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return registdate.format(formatter);  // 使用 LocalDateTime 的 format 方法
        }
        return "未知";  // 如果 registdate 为 null，则返回 "未知"
    }

    public void setRegistdate(LocalDateTime registdate) {
        this.registdate = registdate;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password 
                + ", email=" + email + ", role=" + role + ", registdate=" + registdate + "]";
    }
}
