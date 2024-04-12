package dao;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.SanPham;

public class ChiTietHoaDonDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;
=======
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
>>>>>>> 18a2abe (Update DAO & Service)
	private HoaDonDao hoaDonDao = new HoaDonDao();
	private SanPhamDao sanPhamDao = new SanPhamDao();

	public ChiTietHoaDonDao() {
<<<<<<< HEAD
		// TODO Auto-generated constructor stub
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public List<ChiTietHoaDon> getCTHoaDonTheoMaHoaDon(String maHD) {
		List<ChiTietHoaDon> dscthd = new ArrayList<>();
		// System.out.println(maNV);
		try {
			String query = "Select * from ChiTietHoaDon where maHoaDon =?";
			ps = con.prepareStatement(query);
			ps.setString(1, maHD);
			rs = ps.executeQuery();
			while (rs.next()) {
				ChiTietHoaDon cthd = new ChiTietHoaDon(hoaDonDao.getHoaDonTheoMa(rs.getString(1)).get(0),
						sanPhamDao.timSanPhamTheoMa(rs.getString(2)), rs.getInt(3), rs.getLong(4));
				dscthd.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
=======
		con = DBConnection.getInstance().getConnection();
	}

	private List<ChiTietHoaDon> getCTHoaDon(ResultSet rs) throws SQLException {
		List<ChiTietHoaDon> dscthd = new ArrayList<>();
		while (rs.next()) {
			dscthd.add(new ChiTietHoaDon(hoaDonDao.timHoaDonTheoMa(rs.getString(1)),
					sanPhamDao.timSanPhamTheoMa(rs.getString(2)), rs.getInt(3), rs.getLong(4)));
>>>>>>> 18a2abe (Update DAO & Service)
		}
		return dscthd;
	}

<<<<<<< HEAD
	public ArrayList<ChiTietHoaDon> getCTHDTheoHoaDon(HoaDon hoaDon) {
		ArrayList<ChiTietHoaDon> listChiTietHoaDon = new ArrayList<>();
		try {
			String sql = "Select * from ChiTietHoaDon where maHoaDon = ?";
			PreparedStatement stmt = con.prepareCall(sql);
			stmt.setString(1, hoaDon.getMaHoaDon());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SanPham sanPham = sanPhamDao.timSanPhamTheoMa(rs.getString(1));

				int soLuong = rs.getInt(3);
				long thanhTien = rs.getLong(4);

				ChiTietHoaDon CTHD = new ChiTietHoaDon(hoaDon, sanPham, soLuong, thanhTien);
				listChiTietHoaDon.add(CTHD);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return listChiTietHoaDon;
	}

	public List<ChiTietHoaDon> getAllCTHD() throws SQLException {
		List<ChiTietHoaDon> dscthd = new ArrayList<>();
		String query = "Select * from ChiTietHoaDon";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {

			ChiTietHoaDon cthd = new ChiTietHoaDon(hoaDonDao.getHoaDonTheoMa(rs.getString(1)).get(0),
					sanPhamDao.timSanPhamTheoMa(rs.getString(2)), rs.getInt(3), rs.getLong(4));
			dscthd.add(cthd);
		}

		return dscthd;
	}

	public int addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		try {
			String sql = "insert into ChiTietHoaDon values (?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareCall(sql);
			stmt.setString(1, chiTietHoaDon.getHoaDon().getMaHoaDon());
			stmt.setString(2, chiTietHoaDon.getSanPham().getMaSanPham());
			stmt.setInt(3, chiTietHoaDon.getSoLuong());
			stmt.setLong(4, chiTietHoaDon.getDonGia());

			return stmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	// Get tong tien
	 public long getTien(String maHD) {
		 List<ChiTietHoaDon> dscthd = new ArrayList<>();
		 long tong = 0;
			try {
				
			String query = "Select * from ChiTietHoaDon where maHoaDon =?";
			ps = con.prepareStatement(query);
			ps.setString(1, maHD);
			rs = ps.executeQuery();
			while (rs.next()) {
				tong += rs.getInt(3) * rs.getLong(4);
				ChiTietHoaDon cthd = new ChiTietHoaDon(hoaDonDao.getHoaDonTheoMa(rs.getString(1)).get(0), sanPhamDao.timSanPhamTheoMa(rs.getString(2)), rs.getInt(3), rs.getLong(4));
				dscthd.add(cthd);
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}
//			for (ChiTietHoaDon chiTietHoaDon : dscthd) {
//				tong += chiTietHoaDon.tinhThanhTien();
//			}
			long VAT = tong / 100;
			long TongTien = tong+VAT;
			System.out.println("Tong tien" + TongTien);
			return TongTien;
			
		}
}
=======
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
>>>>>>> 18a2abe (Update DAO & Service)
