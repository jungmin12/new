package org.zerock.dao;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractDAO<E, K> implements BoardDAO<E, K> {

	protected String convert(String sql) {

		return sql.replaceAll("#\\{[a-z]*}", "?");

	}

	protected List<String> parse(String sql) {

		List<String> list = new ArrayList<>();

		Pattern p = Pattern.compile("#\\{[a-z]*}");

		Matcher matcher = p.matcher(sql);

		while (matcher.find()) {

			String str = matcher.group();

			list.add(str.substring(2).substring(0, str.length() - 3));

		}
		return list;
	}

	public Map<String, Method> getMethodParse(Object obj) throws Exception {

		Map<String, Method> map = new HashMap<>();

		Class clz = obj.getClass();

		BeanInfo info = Introspector.getBeanInfo(clz);

		PropertyDescriptor[] props = info.getPropertyDescriptors();

		for (PropertyDescriptor propertyDescriptor : props) {
			map.put(propertyDescriptor.getName(), propertyDescriptor.getReadMethod());
		}
		return map;
	}

	public Map<String, Method> setMethodParse(Object obj) throws Exception {

		Map<String, Method> map = new HashMap<>();

		Class clz = obj.getClass();

		BeanInfo info = Introspector.getBeanInfo(clz);

		PropertyDescriptor[] props = info.getPropertyDescriptors();

		for (PropertyDescriptor propertyDescriptor : props) {
			map.put(propertyDescriptor.getName(), propertyDescriptor.getWriteMethod());
		}
		return map;
	}

	private E extractData(ResultSet rs, Class clz) throws Exception {

		if (rs.next() == false) {
			return null;
		}

		Object obj = clz.newInstance();

		Map<String, Method> setMap = setMethodParse(obj);

		ResultSetMetaData meta = rs.getMetaData();

		int count = meta.getColumnCount();

		for (int i = 1; i <= count; i++) {

			String colName = meta.getColumnName(i).toLowerCase();

			Method setMethod = setMap.get(colName);

			Class type = setMethod.getParameterTypes()[0];

			if (setMethod == null) {
				continue;
			}

			setMethod.invoke(obj, rs.getObject(i));

		}

		return (E) obj;
	}

	protected E selectOneObject(String sql, Class clz, Object... params) throws Exception {

		Object result = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			pstmt = con.prepareStatement(convert(sql));

			int idx = 1;

			for (Object object : params) {

				Class type = object.getClass();

				if (type == int.class || type == Integer.class) {

					pstmt.setInt(idx, (int) object);
				} else if (type == String.class) {

					pstmt.setString(idx, (String) object);
				} else if (type == double.class || type == Double.class) {

					pstmt.setDouble(idx, (double) object);
				}
				idx++;
			}
			rs = pstmt.executeQuery();

			result = extractData(rs, clz);

		} catch (Exception e) {

			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
				}
			}
		}
		return (E) result;
	}

	protected int executeUpdate(String sql, Object obj) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();

			pstmt = con.prepareStatement(convert(sql));

			Map<String, Method> getMap = getMethodParse(obj);
			List<String> paramList = parse(sql);

			int idx = 1;
			for (String string : paramList) {

				Method getMethod = getMap.get(string);

				Class type = getMethod.getReturnType();

				if (type == String.class) {

					pstmt.setString(idx, (String) getMethod.invoke(obj, null));

				} else if (type == int.class || type == Integer.class) {
					pstmt.setInt(idx, (int) getMethod.invoke(obj, null));
				} else if (type == double.class || type == Double.class) {
					pstmt.setDouble(idx, (double) getMethod.invoke(obj, null));
				} else if (type == java.sql.Date.class) {
					pstmt.setDate(idx, (java.sql.Date) getMethod.invoke(obj, null));
				} else if (type == java.util.Date.class) {
					java.util.Date value = (java.util.Date) getMethod.invoke(obj, null);
					pstmt.setDate(idx, new java.sql.Date(value.getTime()));
				}

				idx++;
			}

			return pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
				}
			}
		}

	}

	private Connection getConnection() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.0.19:3306/bit88?useSSL=false", "makemoney",
				"makemoney");

		return con;
	}

}
