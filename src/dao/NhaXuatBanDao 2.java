package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import entity.NhaXuatBan;

public class NhaXuatBanDao {
	private Connection con;
<<<<<<< HEAD
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public NhaXuatBanDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<NhaXuatBan> getListNhaXuatBan() throws Exception {
		ArrayList<NhaXuatBan> list = new ArrayList<>();
		query = "SELECT maNXB, tenNXB\r\n" + "FROM     NhaXuatBan";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB"));
			list.add(nhaXuatBan);
=======

	public NhaXuatBanDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public ArrayList<NhaXuatBan> getListNhaXuatBan() throws SQLException {
		ArrayList<NhaXuatBan> list = new ArrayList<>();
		PreparedStatement ps = con.prepareStatement("SELECT maNXB, tenNXB FROM NhaXuatBan");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB")));
>>>>>>> 18a2abe (Update DAO & Service)
		}
		return list;
	}

<<<<<<< HEAD
	public boolean themNhaXuatBan(NhaXuatBan t) throws Exception {
		query = "INSERT [dbo].[NhaXuatBan] ([maNXB], [tenNXB]) VALUES ( ? , N'" + t.getTenNXB() + "')";
		ps = con.prepareStatement(query);
		ps.setString(1, t.getMaNXB());
		rsCheck = ps.executeUpdate();
		if (rsCheck != 0)
			return true;
		return false;
	}

	public NhaXuatBan timNhaXuatBan(String NXB) throws SQLException {
		query = "select * from NhaXuatBan where tenNXB = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, NXB);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB"));
		}
		return null;
	}
	public boolean kiemTraTonTaiNXB(String ten) throws SQLException {
		query = "select * from NhaXuatBan where tenNXB = N'"+ten+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
}
=======
	public boolean themNhaXuatBan(NhaXuatBan t) throws SQLException {
		PreparedStatement ps = con.prepareStatement("INSERT [dbo].[NhaXuatBan] ([maNXB], [tenNXB]) VALUES (?, ?)");
		ps.setString(1, t.getMaNXB());
		ps.setString(2, t.getTenNXB());
		return ps.executeUpdate() > 0;
	}

	public NhaXuatBan timNhaXuatBan(String NXB) throws SQLException {
		PreparedStatement ps = con.prepareStatement("select * from NhaXuatBan where tenNXB = ?");
		ps.setString(1, NXB);
		ResultSet rs = ps.executeQuery();
		return rs.next() ? new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB")) : null;
	}

	public boolean kiemTraTonTaiNXB(String ten) throws SQLException {
		PreparedStatement ps = con.prepareStatement("select * from NhaXuatBan where tenNXB = ?");
		ps.setString(1, ten);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
