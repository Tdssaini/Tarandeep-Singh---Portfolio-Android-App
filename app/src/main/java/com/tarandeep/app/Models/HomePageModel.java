package com.tarandeep.app.Models;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Tarandeep Singh on 8/23/2017.
 */

public class HomePageModel extends BaseModel {

    private ArrayList<Map<Object,Object>> summary;
    private Map<String,String> education;
    private String personal_info;
    private Map<String,String> contact_info;
    private String resume_link;
    private String certification_url;
    private String image_url;
    private String qr_code;
    private String experience;
    private String current_designation;

    public String getCurrent_designation() {
        return current_designation;
    }

    public void setCurrent_designation(String current_designation) {
        this.current_designation = current_designation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public ArrayList<Map<Object,Object>> getSummary() {
        return summary;
    }

    public Map<String, String> getContact_info() {
        return contact_info;
    }

    public Map<String, String> getEducation() {
        return education;
    }

    public String getCertification_url() {
        return certification_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getPersonal_info() {
        return personal_info;
    }

    public String getQr_code() {
        return qr_code;
    }

    public String getResume_link() {
        return resume_link;
    }

    public void setCertification_url(String certification_url) {
        this.certification_url = certification_url;
    }

   public void setContact_info(Map<String, String> contact_info) {
        this.contact_info = contact_info;
    }

    public void setEducation(Map<String, String> education) {
        this.education = education;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setPersonal_info(String personal_info) {
        this.personal_info = personal_info;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public void setResume_link(String resume_link) {
        this.resume_link = resume_link;
    }

   public void setSummary(ArrayList<Map<Object,Object>> summary) {
        this.summary = summary;
    }
}
