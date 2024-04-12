package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.HoaDon;
import entity.HoaDonDoiTra;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDonDoiTraDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private NhanVienDao nhanVienDao = new NhanVienDao();
	private KhachHangDao khachHangDao = new KhachHangDao();
	private HoaDonDao hoaDonDao = new HoaDonDao();

	public HoaDonDoiTraDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public int themHoaDonDoiTra(HoaDonDoiTra hddt) throws SQLException {
		String insert = "Insert into HoaDonDoiTra values (?, ?, ?, ?, ?, ?,?,?)";
		ps = con.prepareStatement(insert);
		ps.setString(1, hddt.getMaHoaDonDoiTra());
		ps.setString(2, hddt.getNhanVien().getMaNhanVien());
		ps.setString(3, hddt.getKhachHang().getMaKhachHang());
		ps.setDate(4, java.sql.Date.valueOf(hddt.getNgayLapHoaDon()));
		ps.setString(5, hddt.getGhiChu());
		ps.setDouble(6, hddt.getTienKhachDua());
		ps.setString(7, hddt.getHoaDon().getMaHoaDon());
		ps.setDouble(8, hddt.getTienPhaiTru());
		return ps.executeUpdate();
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoMa(String maHDDT) throws SQLException {
		List<HoaDonDoiTra> dshddt = new ArrayList<>();
		String query = "Select * from HoaDonDoiTra where maHoaDonDoiTra =?";
		ps = con.prepareStatement(query);
		ps.setString(1, maHDDT);
		rs = ps.executeQuery();
		while (rs.next()) {
			HoaDonDoiTra hddt = new HoaDonDoiTra(rs.getString(1), nhanVienDao.timNhanVienTheoMa(rs.getString(2)),
					khachHangDao.timKhachHangTheoMa(rs.getString(3)), rs.getDate(4).toLocalDate(), rs.getString(5),
					rs.getDouble(6), hoaDonDao.timHoaDonTheoMa(rs.getString(7)), rs.getDouble(8));
			dshddt.add(hddt);
		}
		return dshddt;
	}

	public List<HoaDonDoiTra> getDSHoaDonDoiTra() throws SQLException {
		return getHoaDonDoiTraTheoMa(null);
	}

	public int editTienKhachTra(HoaDonDoiTra hddt) throws SQLException {
		return updateHoaDonDoiTra(hddt, "update HoaDonDoiTra set tienKhachDua =? where maHoaDonDoiTra=?", hddt.getTienKhachDua());
	}

	public int editTienPhaiTru(HoaDonDoiTra hddt) throws SQLException {
		return updateHoaDonDoiTra(hddt, "update HoaDonDoiTra set tienPhaiTru =? where maHoaDonDoiTra=?", hddt.getTienPhaiTru());
	}

	private int updateHoaDonDoiTra(HoaDonDoiTra hddt, String query, double value) throws SQLException {
		ps = con.prepareStatement(query);
		ps.setDouble(1, value);
		ps.setString(2, hddt.getMaHoaDonDoiTra());
		return ps.executeUpdate();
	}

	public List<HoaDonDoiTra> getMaHoaDonDoiTraByMaHDCu(String maHDCu) throws SQLException {
		return getHoaDonDoiTraTheoMa(maHDCu);
	}

	public List<HoaDonDoiTra> getToanBoDSHoaDonDoiTra() throws SQLException {
		return getHoaDonDoiTraTheoMa(null);
	}

	public HoaDonDoiTra timHoaDonDoiTraTheoMa(String maHoaDon) throws SQLException {
		List<HoaDonDoiTra> list = getHoaDonDoiTraTheoMa(maHoaDon);
		return list.isEmpty() ? null : list.get(0);
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTen(String tenNV) throws SQLException {
		return getHoaDonDoiTraTheoMa(tenNV);
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoSDT(String sdt) throws SQLException {
		return getHoaDonDoiTraTheoMa(sdt);
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTenKH(String tenKH) throws SQLException {
		return getHoaDonDoiTraTheoMa(tenKH);
	}
}