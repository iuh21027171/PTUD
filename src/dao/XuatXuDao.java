package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
<<<<<<< HEAD

=======
>>>>>>> 18a2abe (Update DAO & Service)
import entity.XuatXu;

public class XuatXuDao {
	private Connection con;
<<<<<<< HEAD
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public XuatXuDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<XuatXu> getListXuatXu() throws Exception {
		ArrayList<XuatXu> list = new ArrayList<>();
		query = "SELECT maXuatXu, tenXuatXu\r\n" + "FROM     XuatXu";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			XuatXu xuatXu = new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu"));
			list.add(xuatXu);
=======
	private PreparedStatement ps;
	private ResultSet rs;

	public XuatXuDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public List<XuatXu> getListXuatXu() throws SQLException {
		List<XuatXu> list = new ArrayList<>();
		String query = "SELECT maXuatXu, tenXuatXu FROM XuatXu";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu")));
>>>>>>> 18a2abe (Update DAO & Service)
		}
		return list;
	}

<<<<<<< HEAD
	public boolean themXuatXu(XuatXu x) throws Exception {
		query = "INSERT [dbo].[XuatXu] ([maXuatXu], [tenXuatXu]) VALUES ( ? , N'" + x.getTenXuatXu() + "')";
		ps = con.prepareStatement(query);
		ps.setString(1, x.getMaXuatXu());
		rsCheck = ps.executeUpdate();
		if (rsCheck != 0)
			return true;
		return false;
	}

	public List<XuatXu> getXuatXu(String maXuatXu) {
		List<XuatXu> dsXX = new ArrayList<XuatXu>();
		try {
			String query = "Select * from XuatXu where maXuatXu = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, maXuatXu);
			rs = ps.executeQuery();
			while (rs.next()) {
				String maxx = rs.getString("maXuatXu");
				String tenxx = rs.getString("tenXuatXu");
				XuatXu xx = new XuatXu(maxx, tenxx);
				dsXX.add(xx);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
=======
	public boolean themXuatXu(XuatXu x) throws SQLException {
		String query = "INSERT INTO XuatXu (maXuatXu, tenXuatXu) VALUES (?, ?)";
		ps = con.prepareStatement(query);
		ps.setString(1, x.getMaXuatXu());
		ps.setString(2, x.getTenXuatXu());
		return ps.executeUpdate() > 0;
	}

	public List<XuatXu> getXuatXu(String maXuatXu) throws SQLException {
		List<XuatXu> dsXX = new ArrayList<>();
		String query = "SELECT * FROM XuatXu WHERE maXuatXu = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, maXuatXu);
		rs = ps.executeQuery();
		while (rs.next()) {
			dsXX.add(new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu")));
>>>>>>> 18a2abe (Update DAO & Service)
		}
		return dsXX;
	}

<<<<<<< HEAD
	public boolean xoaXuatXu(String maXuatXu) {

		return false;
	}

	public XuatXu timXuatXu(String XuatXu) throws SQLException {
		query = "select * from XuatXu where tenXuatXu = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, XuatXu);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu"));
		}
		return null;
	}
	public boolean kiemTraTonTaiXuatXu(String ten) throws SQLException {
		query = "select * from XuatXu where tenXuatXu = N'"+ten+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
}
=======
	public XuatXu timXuatXu(String XuatXu) throws SQLException {
		String query = "SELECT * FROM XuatXu WHERE tenXuatXu = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, XuatXu);
		rs = ps.executeQuery();
		return rs.next() ? new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu")) : null;
	}

	public boolean kiemTraTonTaiXuatXu(String ten) throws SQLException {
		String query = "SELECT * FROM XuatXu WHERE tenXuatXu = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, ten);
		rs = ps.executeQuery();
		return rs.next();
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
