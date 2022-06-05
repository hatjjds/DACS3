package com.example.dacs3.Model;

public class NewModel {
    String id_anime;
    String name_anime;
    String backgroud;
    String detail;
    String total_episodes;
    String ep_number;
    String src;

    public NewModel() {
        this.id_anime = id_anime;
        this.name_anime = name_anime;
        this.backgroud = backgroud;
        this.detail = detail;
        this.total_episodes=total_episodes;
        this.ep_number=ep_number;
        this.src=src;
    }

    public String getEp_number() {
        return ep_number;
    }

    public void setEp_number(String ep_number) {
        this.ep_number = ep_number;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getId_anime() {
        return id_anime;
    }

    public void setId_anime(String id_anime) {
        this.id_anime = id_anime;
    }

    public String getName_anime() {
        return name_anime;
    }

    public void setName_anime(String name_anime) {
        this.name_anime = name_anime;
    }

    public String getBackgroud() {
        return backgroud;
    }

    public void setBackgroud(String backgroud) {
        this.backgroud = backgroud;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTotal_episodes() {
        return total_episodes;
    }

    public void setTotal_episodes(String total_episodes) {
        this.total_episodes = total_episodes;
    }

}
