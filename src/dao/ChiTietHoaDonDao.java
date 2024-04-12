package dao;

import db.DBConnection;
import entity.ChiTietHoaDon;
import entity.HoaDon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private HoaDonDao hoaDonDao = new HoaDonDao();
	private SanPhamDao sanPhamDao = new SanPhamDao();

	public ChiTietHoaDonDao() {
		con = DBConnection.getInstance().getConnection();
	}

	private List<ChiTietHoaDon> getCTHoaDon(ResultSet rs) throws SQLException {
		List<ChiTietHoaDon> dscthd = new ArrayList<>();
		while (rs.next()) {
			dscthd.add(new ChiTietHoaDon(hoaDonDao.timHoaDonTheoMa(rs.getString(1)),
					sanPhamDao.timSanPhamTheoMa(rs.getString(2)), rs.getInt(3), rs.getLong(4)));
		}
		return dscthd;
	}

	public List<ChiTietHoaDon> getCTHoaDonTheoMaHoaDon(String maHD) throws SQLException {
		ps = con.prepareStatement("Select * from ChiTietHoaDon where maHoaDon =?");
		ps.setString(1, maHD);
		rs = ps.executeQuery();
		return getCTHoaDon(rs);
	}

	public List<ChiTietHoaDon> getCTHDTheoHoaDon(HoaDon hoaDon) throws SQLException {
		ps = con.prepareStatement("Select * from ChiTietHoaDon where maHoaDon = ?");
		ps.setString(1, hoaDon.getMaHoaDon());
		rs = ps.executeQuery();
		return getCTHoaDon(rs);
	}

	public List<ChiTietHoaDon> getAllCTHD() throws SQLException {
		ps = con.prepareStatement("Select * from ChiTietHoaDon");
		rs = ps.executeQuery();
		return getCTHoaDon(rs);
	}

	public int addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) throws SQLException {
		ps = con.prepareStatement("insert into ChiTietHoaDon values (?, ?, ?, ?)");
		ps.setString(1, chiTietHoaDon.getHoaDon().getMaHoaDon());
		ps.setString(2, chiTietHoaDon.getSanPham().getMaSanPham());
		ps.setInt(3, chiTietHoaDon.getSoLuong());
		ps.setLong(4, chiTietHoaDon.getDonGia());
		return ps.executeUpdate();
	}

	public long getTien(String maHD) throws SQLException {
		long tong = 0;
		ps = con.prepareStatement("Select * from ChiTietHoaDon where maHoaDon =?");
		ps.setString(1, maHD);
		rs = ps.executeQuery();
		while (rs.next()) {
			tong += rs.getInt(3) * rs.getLong(4);
		}
		return tong + tong / 100;
	}
}