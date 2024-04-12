package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.TacGia;

public class TacGiaDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public TacGiaDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public List<TacGia> getListTacGia() throws SQLException {
		List<TacGia> list = new ArrayList<>();
		ps = con.prepareStatement("SELECT maTacGia, tenTacGia FROM TacGia");
		rs = ps.executeQuery();
		while (rs.next()) {
			list.add(new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia")));
		}
		return list;
	}

	public List<TacGia> getTacGia(String maTacGia) throws SQLException {
		List<TacGia> dsTG = new ArrayList<>();
		ps = con.prepareStatement("Select * from TacGia where maTacGia = ?");
		ps.setString(1, maTacGia);
		rs = ps.executeQuery();
		while (rs.next()) {
			dsTG.add(new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia")));
		}
		return dsTG;
	}

	public boolean themTacGia(TacGia t) throws SQLException {
		ps = con.prepareStatement("INSERT [dbo].[TacGia] ([maTacGia], [tenTacGia]) VALUES (?, ?)");
		ps.setString(1, t.getMaTacGia());
		ps.setString(2, t.getTenTacGia());
		return ps.executeUpdate() != 0;
	}

	public TacGia timTacGia(String TacGia) throws SQLException {
		ps = con.prepareStatement("select * from TacGia where tenTacGia = ?");
		ps.setString(1, TacGia);
		rs = ps.executeQuery();
		return rs.next() ? new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia")) : null;
	}

	public boolean kiemTraTonTaiTacGia(String ten) throws SQLException {
		ps = con.prepareStatement("select * from TacGia where tenTacGia = ?");
		ps.setString(1, ten);
		rs = ps.executeQuery();
		return rs.next();
	}
}