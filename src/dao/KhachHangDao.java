package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.KhachHang;

public class KhachHangDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public KhachHangDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public int themKhachHang(KhachHang kh) throws SQLException {
		String insert = "Insert into KhachHang values (?, ?, ?, ?, ?)";
		ps = con.prepareStatement(insert);
		ps.setString(1, kh.getMaKhachHang());
		ps.setString(2, kh.getHoTenKhachHang());
		ps.setBoolean(3, kh.isGioiTinh());
		ps.setString(4, kh.getsDT());
		ps.setString(5, kh.getDiaChi());
		return ps.executeUpdate();
	}

	public List<KhachHang> getDSKhachHang() throws SQLException {
		List<KhachHang> dskh = new ArrayList<>();
		String query = "Select * from KhachHang";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			dskh.add(createKhachHangFromResultSet(rs));
		}
		return dskh;
	}

	public KhachHang timKhachHangTheoMa(String maKH) throws SQLException {
		String query = "Select * from KhachHang where maKhachHang=?";
		ps = con.prepareStatement(query);
		ps.setString(1, maKH);
		rs = ps.executeQuery();
		return rs.next() ? createKhachHangFromResultSet(rs) : null;
	}

	public List<KhachHang> timKhachHangTheoTen(String tenKH) throws SQLException {
		return executeQueryAndCollectResults("Select * from KhachHang where hotenKhachHang LIKE CONCAT('%', ?, '%')", tenKH);
	}

	public int capNhatKhachHang(KhachHang kh) throws SQLException {
		String sql = "UPDATE KhachHang SET hotenKhachHang = ?, gioiTinh = ?, sdt = ?, diaChi = ? WHERE maKhachHang = ?";
		ps = con.prepareStatement(sql);
		ps.setString(1, kh.getHoTenKhachHang());
		ps.setBoolean(2, kh.isGioiTinh());
		ps.setString(3, kh.getsDT());
		ps.setString(4, kh.getDiaChi());
		ps.setString(5, kh.getMaKhachHang());
		return ps.executeUpdate();
	}

	public List<KhachHang> timKhachHangTheoSDT(String sDT) throws SQLException {
		return executeQueryAndCollectResults("Select * from KhachHang where sdt LIKE CONCAT('%', ?, '%')", sDT);
	}

	public List<KhachHang> getListKhachHangByNameAndSDT(String tenKH, String sdt) throws SQLException {
		return executeQueryAndCollectResults("select * from KhachHang where hotenKhachHang LIKE CONCAT('%', ?, '%') or sdt LIKE CONCAT('%', ?, '%')", tenKH, sdt);
	}

	public KhachHang timKhachHangBangSDT(String sdt) throws SQLException {
		String query = "Select * from KhachHang where sdt=?";
		ps = con.prepareStatement(query);
		ps.setString(1, sdt);
		rs = ps.executeQuery();
		return rs.next() ? createKhachHangFromResultSet(rs) : null;
	}

	private KhachHang createKhachHangFromResultSet(ResultSet rs) throws SQLException {
		return new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"), rs.getString("sdt"),
				rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
	}

	private List<KhachHang> executeQueryAndCollectResults(String query, String... params) throws SQLException {
		List<KhachHang> khachhang = new ArrayList<>();
		ps = con.prepareStatement(query);
		for (int i = 0; i < params.length; i++) {
			ps.setString(i + 1, params[i]);
		}
		rs = ps.executeQuery();
		while (rs.next()) {
			khachhang.add(createKhachHangFromResultSet(rs));
		}
		return khachhang;
	}
}