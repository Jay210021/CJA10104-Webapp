package com.coupon.model;

import java.util.*;
import java.sql.*;

public class CouponJDBCDAO implements CouponDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cja101_g6?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "a3787806";

	private static final String INSERT_STMT = 
		"INSERT INTO coupon (coupon_Code,discount_Value,min_Spend,start_Date,end_Date) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT coupon_Id,coupon_Code,discount_Value,min_Spend,start_Date,end_Date FROM coupon order by coupon_Id";
	private static final String GET_ONE_STMT = 
		"SELECT coupon_Id,coupon_Code,discount_Value,min_Spend,start_Date,end_Date FROM coupon where coupon_Id = ?";
	private static final String DELETE = 
		"DELETE FROM coupon where coupon_Id = ?";
	private static final String UPDATE = 
		"UPDATE coupon set coupon_Code=?, diacount_Value=?, min_Spend=?, start_Date=?, end_Date=? where coupon_Id = ?";

	@Override
	public void insert(CouponVO couponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, couponVO.getCouponCode());
			pstmt.setInt(2, couponVO.getDiscountValue());
			pstmt.setInt(3, couponVO.getMinSpend());
			pstmt.setDate(4, couponVO.getStartDate());
			pstmt.setDate(5, couponVO.getEndDate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(CouponVO couponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, couponVO.getCouponCode());
			pstmt.setInt(2, couponVO.getDiscountValue());
			pstmt.setInt(3, couponVO.getMinSpend());
			pstmt.setDate(4, couponVO.getStartDate());
			pstmt.setDate(5, couponVO.getEndDate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer couponId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, couponId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public CouponVO findByPrimaryKey(Integer couponId) {

		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, couponId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCouponId(rs.getInt("coupon_Id"));
				couponVO.setCouponCode(rs.getString("coupon_Code"));
				couponVO.setDiscountValue(rs.getInt("discount_Value"));
				couponVO.setMinSpend(rs.getInt("min_Spend"));
				couponVO.setStartDate(rs.getDate("start_Date"));
				couponVO.setEndDate(rs.getDate("end_Date"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return couponVO;
	}

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCouponId(rs.getInt("coupon_Id"));
				couponVO.setCouponCode(rs.getString("coupon_Code"));
				couponVO.setDiscountValue(rs.getInt("discount_Value"));
				couponVO.setMinSpend(rs.getInt("min_Spend"));
				couponVO.setStartDate(rs.getDate("start_Date"));
				couponVO.setEndDate(rs.getDate("end_Date"));
				list.add(couponVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		CouponJDBCDAO dao = new CouponJDBCDAO();

		

		// 查詢
		List<CouponVO> list = dao.getAll();
		for (CouponVO aCoupon : list) {
			System.out.print(aCoupon.getCouponId() + ",");
			System.out.print(aCoupon.getCouponCode() + ",");
			System.out.print(aCoupon.getDiscountValue() + ",");
			System.out.print(aCoupon.getMinSpend() + ",");
			System.out.print(aCoupon.getStartDate() + ",");
			System.out.print(aCoupon.getEndDate() + ",");
			System.out.println();
		}
	}
}