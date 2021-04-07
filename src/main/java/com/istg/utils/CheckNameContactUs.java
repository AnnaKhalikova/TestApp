package com.istg.utils;

import com.istg.domain.ContactUs;

public class CheckNameContactUs {
    private ContactUs contactUs;

    public CheckNameContactUs(ContactUs contactUs) {
        this.contactUs = contactUs;
    }

    public CheckNameContactUs() {
    }

    public ContactUs getContactUs() {
        return contactUs;
    }

    public void setContactUs(ContactUs contactUs) {
        this.contactUs = contactUs;
    }

    public boolean checkNameContactUs(){
        if(contactUs.getName_ct().length()>=4 && contactUs.getName_ct().length()<=33){
            return true;
        }else{
            return false;
        }
    }
}
