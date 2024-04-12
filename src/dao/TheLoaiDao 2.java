package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.TheLoaiSach;
import entity.TheLoaiVanPhongPham;

public class TheLoaiDao {
	private Connection con;
<<<<<<< HEAD
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public TheLoaiDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<TheLoaiSach> getListTheLoaiSach() throws Exception {
		ArrayList<TheLoaiSach> list = new ArrayList<>();
		query = "SELECT maTheLoai, tenTheLoai\r\n" + "FROM     TheLoaiSach";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			TheLoaiSach s = new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai"));
			list.add(s);
=======
	private PreparedStatement ps;
	private ResultSet rs;

	public TheLoaiDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public List<TheLoaiSach> getListTheLoaiSach() throws SQLException {
		List<TheLoaiSach> list = new ArrayList<>();
		ps = con.prepareStatement("SELECT maTheLoai, tenTheLoai FROM TheLoaiSach");
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai")));
>>>>>>> 18a2abe (Update DAO & Service)
		}
		return list;
	}

<<<<<<< HEAD
	public ArrayList<TheLoaiVanPhongPham> getListTheLoaiVanPhongPham() throws Exception {
		ArrayList<TheLoaiVanPhongPham> list = new ArrayList<>();
		query = "SELECT maLoaiVanPhongPham, tenTheLoai\r\n" + "FROM     LoaiVanPhongPham";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			TheLoaiVanPhongPham s = new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"),
					rs.getString("tenTheLoai"));
			list.add(s);
=======
	public List<TheLoaiVanPhongPham> getListTheLoaiVanPhongPham() throws SQLException {
		List<TheLoaiVanPhongPham> list = new ArrayList<>();
		ps = con.prepareStatement("SELECT maLoaiVanPhongPham, tenTheLoai FROM LoaiVanPhongPham");
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"), rs.getString("tenTheLoai")));
>>>>>>> 18a2abe (Update DAO & Service)
		}
		return list;
	}

<<<<<<< HEAD
	public boolean themTheLoaiSach(TheLoaiSach t) throws Exception {
		query = "INSERT [dbo].[TheLoaiSach] ([maTheLoai], [tenTheLoai]) VALUES (?, N'" + t.getTenLoai() + "')";
		ps = con.prepareStatement(query);
		ps.setString(1, t.getMaLoai());
		rsCheck = ps.executeUpdate();
		if (rsCheck != 0)
			return true;
		return false;
	}

	public boolean themTheLoaiVanPhongPham(TheLoaiVanPhongPham t) throws Exception {
		query = "INSERT [dbo].[LoaiVanPhongPham] ([maLoaiVanPhongPham], [tenTheLoai]) VALUES ( ? , N'" + t.getTenLoai()
				+ "')";
		ps = con.prepareStatement(query);
		ps.setString(1, t.getMaLoai());
		rsCheck = ps.executeUpdate();
		if (rsCheck != 0)
			return true;
		return false;
	}

	public TheLoaiSach timTheLoaiSach(String TheLoai) throws SQLException {
		query = "select * from TheLoaiSach where tenTheLoai = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, TheLoai);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai"));
		}
		return null;
	}

	public TheLoaiVanPhongPham timTheLoaiVanPhongPham(String TheLoai) throws SQLException {
		query = "select * from LoaiVanPhongPham where tenTheLoai = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, TheLoai);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"), rs.getString("tenTheLoai"));
		}
		return null;
	}

	public List<TheLoaiSach> getSachTheoTheLoai(String maTL) {
		List<TheLoaiSach> dsTLS = new ArrayList<>();
		try {
			String query = "Select * from TheLoaiSach where maTheLoai=?";
			ps = con.prepareStatement(query);
			ps.setString(1, maTL);
			rs = ps.executeQuery();
			while (rs.next()) {
				String maTheLoai = rs.getString("maTheLoai");
				String tenTheLoai = rs.getString("tenTheLoai");
				TheLoaiSach tls = new TheLoaiSach(maTheLoai, tenTheLoai);
				dsTLS.add(tls);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsTLS;
	}
	public boolean kiemTraTonTaiTheLoaiVPP(String ten) throws SQLException {
		query = "select * from LoaiVanPhongPham where tenTheLoai = N'"+ten+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
	public boolean kiemTraTonTaiTheLoaiSach(String ten) throws SQLException {
		query = "select * from TheLoaiSach where tenTheLoai = N'"+ten+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}

}
=======
	public boolean themTheLoaiSach(TheLoaiSach t) throws SQLException {
		ps = con.prepareStatement("INSERT INTO TheLoaiSach (maTheLoai, tenTheLoai) VALUES (?, ?)");
		ps.setString(1, t.getMaLoai());
		ps.setString(2, t.getTenLoai());
		return ps.executeUpdate() > 0;
	}

	public boolean themTheLoaiVanPhongPham(TheLoaiVanPhongPham t) throws SQLException {
		ps = con.prepareStatement("INSERT INTO LoaiVanPhongPham (maLoaiVanPhongPham, tenTheLoai) VALUES (?, ?)");
		ps.setString(1, t.getMaLoai());
		ps.setString(2, t.getTenLoai());
		return ps.executeUpdate() > 0;
	}

	public TheLoaiSach timTheLoaiSach(String TheLoai) throws SQLException {
		ps = con.prepareStatement("SELECT * FROM TheLoaiSach WHERE tenTheLoai = ?");
		ps.setString(1, TheLoai);
		rs = ps.executeQuery();
		return rs.next() ? new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai")) : null;
	}

	public TheLoaiVanPhongPham timTheLoaiVanPhongPham(String TheLoai) throws SQLException {
		ps = con.prepareStatement("SELECT * FROM LoaiVanPhongPham WHERE tenTheLoai = ?");
		ps.setString(1, TheLoai);
		rs = ps.executeQuery();
		return rs.next() ? new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"), rs.getString("tenTheLoai")) : null;
	}

	public List<TheLoaiSach> getSachTheoTheLoai(String maTL) throws SQLException {
		List<TheLoaiSach> dsTLS = new ArrayList<>();
		ps = con.prepareStatement("SELECT * FROM TheLoaiSach WHERE maTheLoai = ?");
		ps.setString(1, maTL);
		rs = ps.executeQuery();
		while (rs.next()) {
			dsTLS.add(new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai")));
		}
		return dsTLS;
	}

	public boolean kiemTraTonTaiTheLoaiVPP(String ten) throws SQLException {
		ps = con.prepareStatement("SELECT * FROM LoaiVanPhongPham WHERE tenTheLoai = ?");
		ps.setString(1, ten);
		rs = ps.executeQuery();
		return rs.next();
	}

	public boolean kiemTraTonTaiTheLoaiSach(String ten) throws SQLException {
		ps = con.prepareStatement("SELECT * FROM TheLoaiSach WHERE tenTheLoai = ?");
		ps.setString(1, ten);
		rs = ps.executeQuery();
		return rs.next();
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
