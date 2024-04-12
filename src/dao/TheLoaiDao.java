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
		}
		return list;
	}

	public List<TheLoaiVanPhongPham> getListTheLoaiVanPhongPham() throws SQLException {
		List<TheLoaiVanPhongPham> list = new ArrayList<>();
		ps = con.prepareStatement("SELECT maLoaiVanPhongPham, tenTheLoai FROM LoaiVanPhongPham");
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"), rs.getString("tenTheLoai")));
		}
		return list;
	}

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