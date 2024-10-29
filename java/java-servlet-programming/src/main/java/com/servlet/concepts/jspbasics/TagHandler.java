package com.servlet.concepts.jspbasics;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;

public class TagHandler extends TagSupport{
    int number;
    String color;

    public void setColor(String color) {
        this.color = color;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int doStartTag() {
        try{
            JspWriter out = pageContext.getOut();
            out.println("This is the custom tag");

            out.println("Multiplication table for " + number);
            out.println("<div style='color : "+color+"'>");
            for(int i=0;i<number;i++){
                out.println("<br>");
                out.println(i * number);
            }
            out.println("</div>");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return SKIP_BODY;
    }
}
