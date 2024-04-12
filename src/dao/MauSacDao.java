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
		}
		return list;
	}

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