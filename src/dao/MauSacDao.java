package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import entity.MauSac;

public class MauSacDao {
	private Connection con;
<<<<<<< HEAD
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public MauSacDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<MauSac> getListMauSac() throws Exception {
		ArrayList<MauSac> list = new ArrayList<>();
		query = "SELECT maMauSac, tenMau\r\n" + "FROM     MauSac";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			MauSac mauSac = new MauSac(rs.getString("maMauSac"), rs.getString("tenMau"));
			list.add(mauSac);
=======
	private PreparedStatement ps;
	private ResultSet rs;

	public MauSacDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public ArrayList<MauSac> getListMauSac() throws SQLException {
		ArrayList<MauSac> list = new ArrayList<>();
		ps = con.prepareStatement("SELECT maMauSac, tenMau FROM MauSac");
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new MauSac(rs.getString("maMauSac"), rs.getString("tenMau")));
>>>>>>> 18a2abe (Update DAO & Service)
		}
		return list;
	}

<<<<<<< HEAD
	public boolean themMauSac(MauSac mauSac) throws Exception {
		query = "INSERT [dbo].[MauSac] ([maMauSac], [tenMau]) VALUES ( ? ,N'" + mauSac.getTenMau() + "')";
		ps = con.prepareStatement(query);
		ps.setString(1, mauSac.getMaMau());
		rsCheck = ps.executeUpdate();
		if (rsCheck != 0)
			return true;
		return false;
	}

	public MauSac timMauSac(String Mau) throws SQLException {
		query = "select * from MauSac where tenMau = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, Mau);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new MauSac(rs.getString("maMauSac"), rs.getString("tenMau"));
		}
		return null;
	}
	public boolean kiemTraTonTaiMauSac(String ten) throws SQLException {
		query = "select * from MauSac where tenMau = N'"+ten+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
}
=======
	public boolean themMauSac(MauSac mauSac) throws SQLException {
		ps = con.prepareStatement("INSERT [dbo].[MauSac] ([maMauSac], [tenMau]) VALUES (?, ?)");
		ps.setString(1, mauSac.getMaMau());
		ps.setString(2, mauSac.getTenMau());
		return ps.executeUpdate() > 0;
	}

	public MauSac timMauSac(String Mau) throws SQLException {
		ps = con.prepareStatement("select * from MauSac where tenMau = ?");
		ps.setString(1, Mau);
		rs = ps.executeQuery();
		return rs.next() ? new MauSac(rs.getString("maMauSac"), rs.getString("tenMau")) : null;
	}

	public boolean kiemTraTonTaiMauSac(String ten) throws SQLException {
		ps = con.prepareStatement("select * from MauSac where tenMau = ?");
		ps.setString(1, ten);
		rs = ps.executeQuery();
		return rs.next();
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
