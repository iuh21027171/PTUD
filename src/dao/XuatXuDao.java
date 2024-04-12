package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.XuatXu;

public class XuatXuDao {
	private Connection con;
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
		}
		return list;
	}

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
		}
		return dsXX;
	}

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