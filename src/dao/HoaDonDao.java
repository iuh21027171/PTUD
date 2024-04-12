package dao;

import db.DBConnection;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private NhanVienDao nhanVienDao = new NhanVienDao();
	private KhachHangDao khachHangDao = new KhachHangDao();

	public HoaDonDao() {
		con = DBConnection.getInstance().getConnection();
	}

	public int themHoaDon(HoaDon hd) throws SQLException {
		String sql = "Insert into HoaDon values (?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, hd.getMaHoaDon());
		stmt.setString(2, hd.getNhanVien().getMaNhanVien());
		stmt.setString(3, hd.getKhachHang().getMaKhachHang());
		int day = hd.getNgayLapHoaDon().getDayOfMonth();
		int month = hd.getNgayLapHoaDon().getMonthValue();
		int year = hd.getNgayLapHoaDon().getYear();
		stmt.setString(4, year + "-" + month + "-" + day);
		stmt.setString(5, hd.getGhiChu());
		stmt.setDouble(6, hd.getTienKhachDua());
		stmt.setBoolean(7, hd.isTinhTrang());
		stmt.executeUpdate();
		return 1;
	}

	private HoaDon createHoaDon(ResultSet rs) throws SQLException {
		return new HoaDon(rs.getString("maHoaDon"),
				nhanVienDao.timNhanVienTheoMa(rs.getString("maNhanVien")),
				khachHangDao.timKhachHangTheoMa(rs.getString("maKhachHang")),
				rs.getDate("ngayLapHoaDon").toLocalDate(),
				rs.getString("ghiChu"), rs.getFloat("tienKhachDua"),
				rs.getBoolean("tinhTrang"));
	}

	public int updateHoaDon(String query, String param) throws SQLException {
		ps = con.prepareStatement(query);
		ps.setString(1, param);
		return ps.executeUpdate();
	}

	public List<HoaDon> getHoaDonList(String query, String param) throws SQLException {
		List<HoaDon> dshd = new ArrayList<>();
		ps = con.prepareStatement(query);
		if (param != null) {
			ps.setString(1, param);
		}
		rs = ps.executeQuery();
		while (rs.next()) {
			dshd.add(createHoaDon(rs));
		}
		return dshd;
	}

	public int setNullChoMaNhanVienTrongHoaDon(String maNV) throws SQLException {
		return updateHoaDon("update HoaDon set maNhanVien =NULL where maNhanVien=?", maNV);
	}

	public List<HoaDon> getDSHoaDon() throws SQLException {
		return getHoaDonList("Select * from HoaDon", null);
	}

	public int doiThongTinHoaDonSauKhiXoa(String tenNV) throws SQLException {
		return updateHoaDon("update HoaDon set maNhanVien =NULL where maNhanVien=NULL", tenNV);
	}

	public List<HoaDon> getHoaDonTheoMa(String maHD) throws SQLException {
		return getHoaDonList("Select * from HoaDon where maHoaDon =?", maHD);
	}

	public List<HoaDon> getHoaDonThuong() throws SQLException {
		return getHoaDonList("select * from HoaDon", null);
	}

	public HoaDon timHoaDonTheoMa(String maHoaDon) throws SQLException {
		List<HoaDon> list = getHoaDonList("Select * from HoaDon where maHoaDon=?", maHoaDon);
		return list.isEmpty() ? null : list.get(0);
	}

	public List<HoaDon> getHoaDonTheoTen(String tenNV) throws SQLException {
		return getHoaDonList("select * from NhanVien inner join HoaDon on NhanVien.maNhanVien = HoaDon.maNhanVien where NhanVien.hotenNhanVien like N'%" + tenNV + "%'", null);
	}

	public List<HoaDon> timHoaDonTheoSDT(String sdt) throws SQLException {
		return getHoaDonList("select * from HoaDon inner join KhachHang on HoaDon.maKhachHang = KhachHang.maKhachHang where khachhang.sdt = ?", sdt);
	}

	public List<HoaDon> timHoaDonTheoTenKH(String ten) throws SQLException {
		return getHoaDonList("select * from HoaDon inner join NhanVien on HoaDon.maNhanVien = NhanVien.maNhanVien inner join KhachHang on HoaDon.maKhachHang = KhachHang.maKhachHang where KhachHang.hotenKhachHang like N'%"+ ten +"%'", null);
	}
}