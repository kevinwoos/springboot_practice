package com.fastcampus.ch21;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@Controller
public class YoilTeller {
    @RequestMapping("/getYoilOrig") // http://localhost:8080/getYoil?year=2021&month=10&day=1
    //    public static void main(String[] args) {
    public void getYoilOrig(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 입력
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        int yyyy = Integer.parseInt(year);
        int mm = Integer.parseInt(month);
        int dd = Integer.parseInt(day);

        // 2. 처리
        Calendar cal = Calendar.getInstance();
        cal.clear();  // 모든 필드(날짜, 시간 등)을 초기화
        cal.set(yyyy, mm - 1, dd);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        char yoil = "일월화수목금토".charAt(dayOfWeek-1); // dayofWeek는 일요일:1, 월요일:2, ... 

        // 3. 출력
        response.setContentType("text/html");    // 응답의 형식을 html로 지정
        response.setCharacterEncoding("utf-8");  // 응답의 인코딩을 utf-8로 지정
        PrintWriter out = response.getWriter();  // 브라우저로의 출력 스트림(out)을 얻는다.
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println(year + "년 " + month + "월 " + day + "일은 ");
        out.println(yoil + "요일입니다.");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
    
    @GetMapping("/yoil") // http://localhost:8080/getYoil?year=2021&month=10&day=1
    //    public static void main(String[] args) {
    public void main(@ModelAttribute MyDate myDate, Model model) throws IOException {
        
        char yoil = getYoil(myDate);
        
        System.out.println("aaaaaaaaaaaaaa = " + myDate.getYear());
//      model.addAttribute("myDate", myDate);        
//        model.addAttribute("year", myDate.getYear());
//        model.addAttribute("month", myDate.getMonth());
//        model.addAttribute("day", myDate.getDay());
//        model.addAttribute("yoil", yoil);

    }

    @ModelAttribute("yoil")
	private char getYoil(MyDate myDate) {
		// 2. 처리
        Calendar cal = Calendar.getInstance();
        cal.clear();  // 모든 필드(날짜, 시간 등)을 초기화
        cal.set(myDate.getYear(), myDate.getMonth() - 1, myDate.getDay());

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        char yoil = "일월화수목금토".charAt(dayOfWeek-1); // dayofWeek는 일요일:1, 월요일:2, ... 
		return yoil;
	}
    
    @GetMapping("/yoils") // http://localhost:8080/getYoil?year=2021&month=10&day=1
    //    public static void main(String[] args) {
    public ModelAndView mains(int year, int month, int day) throws IOException {
        
    	ModelAndView mv = new ModelAndView();

    	if(!IsValid(year, month, day)) {
    		mv.setViewName("yoilError");
    		return mv;
    	}
        // 2. 처리
        Calendar cal = Calendar.getInstance();
        cal.clear();  // 모든 필드(날짜, 시간 등)을 초기화
        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        char yoil = "일월화수목금토".charAt(dayOfWeek-1); // dayofWeek는 일요일:1, 월요일:2, ... 
        
        mv.addObject("year", year);
        mv.addObject("month", month);
        mv.addObject("day", day);
        mv.addObject("yoil", yoil);
        
        System.out.println("TTTTTTTTTTTTTTTTTTTTTTTT");
//        mv.setViewName("yoil");

        return mv;
    }

	private boolean IsValid(int year, int month, int day) {
		// TODO Auto-generated method stub
		return true;
	}}
