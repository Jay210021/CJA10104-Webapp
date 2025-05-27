package com.coupon.controller;

import java.io.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.coupon.model.*;

public class CouponServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("action = " + req.getParameter("action"));
		System.out.println("couponId = " + req.getParameter("couponId"));

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("couponId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入優惠券編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer couponId = null;
				try {
					couponId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("優惠券編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				CouponService couponSvc = new CouponService();
				CouponVO couponVO = couponSvc.getOneCoupon(couponId);
				if (couponVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("couponVO", couponVO); 
				String url = "/coupon/listOneCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCoupon.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
//				Integer couponId = Integer.valueOf(req.getParameter("couponId"));
				String couponIdStr = req.getParameter("couponId");
				Integer couponId = null;

				if (couponIdStr == null || couponIdStr.trim().isEmpty()) {
				    errorMsgs.add("未取得優惠券編號");
				} else {
				    try {
				        couponId = Integer.valueOf(couponIdStr.trim());
				    } catch (NumberFormatException e) {
				        errorMsgs.add("優惠券編號格式錯誤");
				    }
				}
				
			    if (!errorMsgs.isEmpty()) {
			        RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
			        failureView.forward(req, res);
			        return;  // 這裡一定要中斷，避免下面用到null的couponId
			    }
				
				/***************************2.開始查詢資料****************************************/
				CouponService couponSvc = new CouponService();
				CouponVO couponVO = couponSvc.getOneCoupon(couponId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("couponVO", couponVO);         
				String url = "/coupon/update_coupon_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_coupon_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_coupon_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
Integer couponId = Integer.valueOf(req.getParameter("couponId").trim());
				
String couponCode = req.getParameter("couponCode");
				String couponCodeReg = "^[a-zA-Z0-9]{2,10}$";
				if (couponCode == null || couponCode.trim().length() == 0) {
					errorMsgs.add("優惠券代碼: 請勿空白");
				} else if(!couponCode.trim().matches(couponCodeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("優惠券代碼: 只能是英文字母和數字, 且長度必需在10以內");
	            }
				
				Integer discountValue = null;
				try {
discountValue = Integer.valueOf(req.getParameter("discountValue").trim());
				} catch (NumberFormatException e) {
					discountValue = 0;
					errorMsgs.add("折扣金額請填數字.");
				}
				
				Integer minSpend = null;
				try {
minSpend = Integer.valueOf(req.getParameter("minSpend").trim());
				} catch (NumberFormatException e) {
					minSpend = 0;
					errorMsgs.add("最低消費金額請填數字.");
				}
				
				java.sql.Date startDate = null;
				try {
startDate = java.sql.Date.valueOf(req.getParameter("startDate").trim());
				} catch (IllegalArgumentException e) {
					startDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				

				java.sql.Date endDate = null;
				try {
endDate = java.sql.Date.valueOf(req.getParameter("endDate").trim());
				} catch (IllegalArgumentException e) {
					endDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}


				CouponVO couponVO = new CouponVO();
				couponVO.setCouponId(couponId);
				couponVO.setCouponCode(couponCode);
				couponVO.setDiscountValue(discountValue);
				couponVO.setMinSpend(minSpend);
				couponVO.setStartDate(startDate);
				couponVO.setEndDate(endDate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("couponVO", couponVO); // 含有輸入格式錯誤的couponVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon/update_coupon_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				CouponService couponSvc = new CouponService();
				couponVO = couponSvc.updateCoupon(couponId, couponCode, discountValue, minSpend, startDate, endDate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("couponVO", couponVO); // 資料庫update成功後,正確的的couponVO物件,存入req
				String url = "/coupon/listOneCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCoupon.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addCoupon.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String couponCode = req.getParameter("couponCode");
			String couponCodeReg = "^[a-zA-Z0-9]{2,10}$";
			if (couponCode == null || couponCode.trim().length() == 0) {
				errorMsgs.add("優惠券代碼: 請勿空白");
			} else if(!couponCode.trim().matches(couponCodeReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("優惠券代碼: 只能是英文字母和數字, 且長度必需在10以內");
            }
			
			Integer discountValue = null;
			try {
discountValue = Integer.valueOf(req.getParameter("discountValue").trim());
			} catch (NumberFormatException e) {
				discountValue = 0;
				errorMsgs.add("折扣金額請填數字.");
			}
			
			Integer minSpend = null;
			try {
minSpend = Integer.valueOf(req.getParameter("minSpend").trim());
			} catch (NumberFormatException e) {
				minSpend = 0;
				errorMsgs.add("最低消費金額請填數字.");
			}
			
			java.sql.Date startDate = null;
			try {
startDate = java.sql.Date.valueOf(req.getParameter("startDate").trim());
			} catch (IllegalArgumentException e) {
				startDate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			

			java.sql.Date endDate = null;
			try {
endDate = java.sql.Date.valueOf(req.getParameter("endDate").trim());
			} catch (IllegalArgumentException e) {
				endDate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			CouponVO couponVO = new CouponVO();
			couponVO.setCouponCode(couponCode);
			couponVO.setDiscountValue(discountValue);
			couponVO.setMinSpend(minSpend);
			couponVO.setStartDate(startDate);
			couponVO.setEndDate(endDate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("couponVO", couponVO); // 含有輸入格式錯誤的couponVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/coupon/addCoupon.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				CouponService couponSvc = new CouponService();
				couponVO = couponSvc.addCoupon(couponCode, discountValue, minSpend, startDate, endDate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/coupon/listAllCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllCoupon.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllCoupon.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer couponId = Integer.valueOf(req.getParameter("couponId"));
				
				/***************************2.開始刪除資料***************************************/
				CouponService couponSvc = new CouponService();
				couponSvc.deleteCoupon(couponId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/coupon/listAllCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
