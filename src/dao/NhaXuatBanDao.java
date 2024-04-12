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

	public NhaXuatBanDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public ArrayList<NhaXuatBan> getListNhaXuatBan() throws SQLException {
		ArrayList<NhaXuatBan> list = new ArrayList<>();
		PreparedStatement ps = con.prepareStatement("SELECT maNXB, tenNXB FROM NhaXuatBan");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB")));
		}
		return list;
	}

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