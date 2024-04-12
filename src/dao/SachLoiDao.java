package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.Sach;
import entity.SachLoi;

public class SachLoiDao {
	private Connection con;

	public SachLoiDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public int themSachLoi(SachLoi sl) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("Insert into SachLoi values (?,?,?)");
		stmt.setString(1, sl.getSach().getMaSanPham());
		stmt.setString(2, sl.getLoiSanPham());
		stmt.setInt(3, sl.getSoLuong());
		return stmt.executeUpdate();
	}

	public int capNhatSoLuong(SachLoi sl) throws SQLException {
		PreparedStatement ps = con.prepareStatement("update SachLoi set soLuongLoi = ? where maSanPham =? and loiSanPham =?");
		ps.setInt(1, sl.getSoLuong());
		ps.setString(2, sl.getSach().getMaSanPham());
		ps.setString(3, sl.getLoiSanPham());
		return ps.executeUpdate();
	}

	public List<SachLoi> getAllSachLoi() throws SQLException {
		List<SachLoi> dssl = new ArrayList<>();
		PreparedStatement ps = con.prepareStatement("Select * from SachLoi");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			dssl.add(new SachLoi(new Sach(rs.getString("maSanPham")), rs.getString("loiSanPham"), rs.getInt("soLuongLoi")));
		}
		return dssl;
	}

	public void xoaSachLoi(String maSach, String loi) throws SQLException {
		PreparedStatement ps = con.prepareStatement("delete from SachLoi where maSanPham = ? and loiSanPham = ?");
		ps.setString(1, maSach);
		ps.setString(2, loi);
		ps.executeUpdate();
	}
}