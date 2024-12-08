package org.wxl.ygmall.domain;

import java.time.LocalDateTime;


public class Browselog {

    private int browseLogId;  // 对应 SQL 的 browse_log_id
    private int user_id;  

    private String username;  // 对应 SQL 的 username
    private String product_id; // 对应 SQL 的 product_id
    private String product_name; // 对应 SQL 的 product_name
    private LocalDateTime browse_time;  // 对应 SQL 的 browse_time

    // Getter 和 Setter 方法

    public int getBrowseLogId() {
        return browseLogId;
    }

    public void setBrowseLogId(int browseLogId) {
        this.browseLogId = browseLogId;
    }
    
    public int getuser_id() {
        return user_id;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getproduct_id() {
        return product_id;
    }

    public void setproduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getproduct_name() {
        return product_name;
    }

    public void setproduct_name(String product_name) {
        this.product_name = product_name;
    }

    public LocalDateTime getbrowse_time() {
        return browse_time;
    }

    public void setbrowse_time(LocalDateTime browse_time) {
        this.browse_time = browse_time;
    }

    @Override
    public String toString() {
        return "Browselog{" +
                "browseLogId=" + browseLogId +
                ", username='" + username + '\'' +
                ", product_id='" + product_id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", browse_time=" + browse_time +
                '}';
    }
}
