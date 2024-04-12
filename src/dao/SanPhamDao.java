package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.ChatLieu;
import entity.MauSac;
import entity.NhaCungCap;
import entity.NhaXuatBan;
import entity.Sach;
import entity.SachLoi;
import entity.SanPham;
import entity.TacGia;
import entity.TheLoaiSach;
import entity.TheLoaiVanPhongPham;
import entity.VanPhongPham;
import entity.XuatXu;

public class SanPhamDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;
	private ChatLieuDao chatLieuDao;
	private XuatXuDao xuatXuDao;
	private TheLoaiDao theloaiDao;
	private TacGiaDao tacgiaDao;

	public SanPhamDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public SanPham timSanPhamTheoMa(String maSP) throws SQLException {
		PreparedStatement ps = con.prepareStatement("Select * from SanPham where maSanPham=?");
		ps.setString(1, maSP);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new SanPham(
					rs.getString("maSanPham"),
					rs.getString("loaiSanPham"),
					rs.getInt("soLuongTon"),
					rs.getDouble("trongLuong"),
					new NhaCungCap(rs.getString("maNCC")),
					rs.getLong("giaNhap"),
					rs.getString("ghiChu"),
					rs.getString("soLuongTon"),
					rs.getString("hinhAnh")
			);
		}
		return null;
	}

	public Sach getSachTheoMaSP(String maSP) throws SQLException {
		PreparedStatement ps = con.prepareStatement("Select * from SanPham where maSanPham=?");
		ps.setString(1, maSP);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new Sach(
					rs.getString("maSanPham"),
					rs.getString("loaiSanPham"),
					rs.getInt("soLuongTon"),
					rs.getDouble("trongLuong"),
					new NhaCungCap(rs.getString("maNCC")),
					rs.getLong("giaNhap"),
					rs.getString("ghiChu"),
					rs.getString("soLuongTon"),
					rs.getString("hinhAnh"),
					rs.getString("tenSach"),
					new TacGia(rs.getString("maTacGia")),
					new NhaXuatBan(rs.getString("maNXB")),
					rs.getInt("namXB"),
					rs.getInt("soTrang"),
					new TheLoaiSach(rs.getString("maTheLoai"))
			);
		}
		return null;
	}

	public String getLoaiSanPhamTheoMa(String maSanPham) throws SQLException {
		PreparedStatement ps = con.prepareStatement("Select loaiSanPham from SanPham where maSanPham = ?");
		ps.setString(1, maSanPham);
		ResultSet rs = ps.executeQuery();
		return rs.next() ? rs.getString("loaiSanPham") : null;
	}

	public SanPham getSanPhamTheoMa(String masp) throws SQLException {
		PreparedStatement ps = con.prepareStatement("Select * from SanPham where maSanPham=?");
		ps.setString(1, masp);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new SanPham(rs.getString("maSanPham"));
		}
		return null;
	}

	public VanPhongPham getVPPTheoMaSP(String maSP) throws SQLException {
		PreparedStatement ps = con.prepareStatement("Select * from SanPham where maSanPham=?");
		ps.setString(1, maSP);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new VanPhongPham(
					rs.getString("maSanPham"),
					rs.getString("loaiSanPham"),
					rs.getInt("soLuongTon"),
					rs.getDouble("trongLuong"),
					new NhaCungCap(rs.getString("maNCC")),
					rs.getLong("giaNhap"),
					rs.getString("ghiChu"),
					rs.getString("soLuongTon"),
					rs.getString("hinhAnh"),
					rs.getString("tenVanPhongPham"),
					new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham")),
					new MauSac(rs.getString("maMauSac")),
					new ChatLieu(rs.getString("maChatLieu")),
					new XuatXu(rs.getString("maXuatXu"))
			);
		}
		return null;
	}

	public ArrayList<Sach> getListSach(String maSach, String tenSP, String maTheLoai, Long giaTu, Long giaDen,
									   String maTacGia, String maNXB, String maNCC, boolean hetHang) throws Exception {

		ArrayList<Sach> listSach = new ArrayList<>();
		String query = "SELECT SanPham.maSanPham, SanPham.soLuongTon,SanPham.loaiSanPham, NhaCungCap.maNCC, NhaCungCap.tenNCC, SanPham.giaNhap, SanPham.ghiChu, SanPham.trongLuong, SanPham.donViSanPham, SanPham.hinhAnh, SanPham.tenSach, TacGia.maTacGia, TacGia.tenTacGia, NhaXuatBan.maNXB, NhaXuatBan.tenNXB, SanPham.namXB, SanPham.soTrang, TheLoaiSach.maTheLoai, TheLoaiSach.tenTheLoai FROM SanPham INNER JOIN NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC INNER JOIN NhaXuatBan ON SanPham.maNXB = NhaXuatBan.maNXB INNER JOIN TacGia ON SanPham.maTacGia = TacGia.maTacGia INNER JOIN TheLoaiSach ON SanPham.maTheLoai = TheLoaiSach.maTheLoai where maSanPham like ? and tenSach like ? and SanPham.maTheLoai like ? and SanPham.giaNhap > ? and SanPham.giaNhap < ? and SanPham.maTacGia like ? and SanPham.maNXB like ? and SanPham.maNCC like ?";
		if (hetHang) {
			query = query + " and soLuongTon = 0";
		}
		ps = con.prepareStatement(query);
		ps.setString(1, "%" + maSach + "%");
		ps.setString(2, "%" + tenSP + "%");
		ps.setString(3, "%" + maTheLoai + "%");
		ps.setLong(4, giaTu);
		ps.setLong(5, giaDen);
		ps.setString(6, "%" + maTacGia + "%");
		ps.setString(7, "%" + maNXB + "%");
		ps.setString(8, "%" + maNCC + "%");
		rs = ps.executeQuery();
		while (rs.next()) {
			Sach s = new Sach(rs.getString("maSanPham"), rs.getString("loaiSanPham"), rs.getInt("soLuongTon"),
					rs.getDouble("trongLuong"), new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")),
					rs.getLong("giaNhap"), rs.getString("ghiChu"), rs.getString("donViSanPham"),
					rs.getString("hinhAnh"), rs.getString("tenSach"),
					new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia")),
					new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB")), rs.getInt("namXB"),
					rs.getInt("soTrang"), new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai")));
			listSach.add(s);
		}
		return listSach;
	}

	public ArrayList<VanPhongPham> getListVanPhongPham(String maVPP, String tenVPP, String theLoaiVPP, Long giaTu,
			Long giaDen, String maChatLieu, String maXuatXu, String maNCC, boolean hetHang) throws Exception {
		ArrayList<VanPhongPham> list = new ArrayList<>();
		query = "SELECT SanPham.maSanPham, SanPham.loaiSanPham, SanPham.soLuongTon, SanPham.trongLuong, "
				+ "	NhaCungCap.maNCC, NhaCungCap.tenNCC, SanPham.giaNhap, SanPham.ghiChu, SanPham.donViSanPham, SanPham.hinhAnh, "
				+ "	SanPham.tenVanPhongPham, LoaiVanPhongPham.maLoaiVanPhongPham, "
				+ " LoaiVanPhongPham.tenTheLoai, MauSac.maMauSac, MauSac.tenMau, ChatLieu.maChatLieu, ChatLieu.tenChatLieu, XuatXu.maXuatXu, XuatXu.tenXuatXu "
				+ "	FROM SanPham INNER JOIN"
				+ "	LoaiVanPhongPham ON SanPham.maLoaiVanPhongPham = LoaiVanPhongPham.maLoaiVanPhongPham INNER JOIN "
				+ "	ChatLieu ON SanPham.maChatLieu = ChatLieu.maChatLieu INNER JOIN "
				+ " MauSac ON SanPham.maMauSac = MauSac.maMauSac INNER JOIN "
				+ "	 XuatXu ON SanPham.maXuatXu = XuatXu.maXuatXu INNER JOIN "
				+ "	NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC\r\n" + "	where maSanPham like '%" + maVPP
				+ "%' and tenVanPhongPham like N'%" + tenVPP + "%' and SanPham.maLoaiVanPhongPham like '%" + theLoaiVPP
				+ "%' \r\n" + "	and SanPham.giaNhap > ? and SanPham.giaNhap < ? and SanPham.maChatLieu like '%"
				+ maChatLieu + "%' \r\n" + "	and SanPham.maXuatXu like '%" + maXuatXu
				+ "%' and SanPham.maNCC like '%" + maNCC + "%' ";
		if (hetHang) {
			query = query + " and soLuongTon = 0";
		}
		ps = con.prepareStatement(query);
		ps.setLong(1, giaTu);
		ps.setLong(2, giaDen);
		rs = ps.executeQuery();
		while (rs.next()) {
			VanPhongPham vanPhongPham = new VanPhongPham(rs.getString("maSanPham"), rs.getString("loaiSanPham"),
					rs.getInt("soLuongTon"), rs.getDouble("trongLuong"),
					new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")), rs.getLong("giaNhap"),
					rs.getString("ghiChu"), rs.getString("donViSanPham"), rs.getString("hinhAnh"),
					rs.getString("tenVanPhongPham"),
					new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"), rs.getString("tenTheLoai")),
					new MauSac(rs.getString("maMauSac"), rs.getString("tenMau")),
					new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu")),
					new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu")));
			list.add(vanPhongPham);
		}
		return list;
	}

	public boolean themSanPham(SanPham sanPham) throws Exception {
		if (sanPham instanceof Sach) {
			Sach s = (Sach) sanPham;
			String query = "INSERT [dbo].[SanPham] VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, null, null, null, null, null)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, s.getMaSanPham());
			ps.setString(2, s.getLoaiSanPham());
			ps.setInt(3, s.getSoLuongTon());
			ps.setDouble(4, s.getTrongLuong());
			ps.setString(5, s.getNhaCungCap().getMaNCC());
			ps.setLong(6, s.getGiaNhap());
			ps.setString(7, s.getGhiChu());
			ps.setString(8, s.getDonViSanPham());
			ps.setString(9, s.getHinhAnh());
			ps.setString(10, s.getTenSach());
			ps.setString(11, s.getTacGia().getMaTacGia());
			ps.setString(12, s.getNhaXuatBan().getMaNXB());
			ps.setInt(13, s.getNamXuatBan());
			ps.setInt(14, s.getSoTrang());
			ps.setString(15, s.getTheLoaiSach().getMaLoai());
			return ps.executeUpdate() > 0;
		} else {
			VanPhongPham v = (VanPhongPham) sanPham;
			String query = "INSERT [dbo].[SanPham] VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, null, null, null, null, null, null, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, v.getMaSanPham());
			ps.setString(2, v.getLoaiSanPham());
			ps.setInt(3, v.getSoLuongTon());
			ps.setDouble(4, v.getTrongLuong());
			ps.setString(5, v.getNhaCungCap().getMaNCC());
			ps.setLong(6, v.getGiaNhap());
			ps.setString(7, v.getGhiChu());
			ps.setString(8, v.getDonViSanPham());
			ps.setString(9, v.getHinhAnh());
			ps.setString(10, v.getTenVanPhongPham());
			ps.setString(11, v.getLoaiVanPhongPham().getMaLoai());
			ps.setString(12, v.getMauSac().getMaMau());
			ps.setString(13, v.getChatLieu().getMaChatLieu());
			ps.setString(14, v.getXuatXu().getMaXuatXu());
			return ps.executeUpdate() > 0;
		}
	}

	public boolean capNhatSanPham(String maSP, SanPham temp) throws Exception {
		if (temp instanceof Sach) {
			Sach s = (Sach) temp;
			String query = "update SanPham set maTheLoai = ?, soLuongTon = ? , trongLuong = ? ," +
					" maNCC = ? , giaNhap = ? , ghiChu = ? , donViSanPham = ? , hinhAnh = ? ," +
					"tenSach = ? , maTacGia = ? ,maNXB = ? , namXB = ? , soTrang = ? where maSanPham like ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(14, s.getMaSanPham());
			ps.setInt(2, s.getSoLuongTon());
			ps.setDouble(3, s.getTrongLuong());
			ps.setString(4, s.getNhaCungCap().getMaNCC());
			ps.setLong(5, s.getGiaNhap());
			ps.setString(6, s.getGhiChu());
			ps.setString(7, s.getDonViSanPham());
			ps.setString(8, s.getHinhAnh());
			ps.setString(9, s.getTenSach());
			ps.setString(10, s.getTacGia().getMaTacGia());
			ps.setString(11, s.getNhaXuatBan().getMaNXB());
			ps.setInt(12, s.getNamXuatBan());
			ps.setInt(13, s.getSoTrang());
			ps.setString(1, s.getTheLoaiSach().getMaLoai());
			return ps.executeUpdate() > 0;
		} else {
			VanPhongPham v = (VanPhongPham) temp;
			String query = "update SanPham set maLoaiVanPhongPham = ?, soLuongTon = ? , trongLuong = ? ," +
					" maNCC = ? , giaNhap = ? , ghiChu = ? , donViSanPham = ? , hinhAnh = ? ," +
					"tenVanPhongPham = ? , maMauSac = ? ,maChatLieu = ? , maXuatXu = ? where maSanPham like ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(13, v.getMaSanPham());
			ps.setInt(2, v.getSoLuongTon());
			ps.setDouble(3, v.getTrongLuong());
			ps.setString(4, v.getNhaCungCap().getMaNCC());
			ps.setLong(5, v.getGiaNhap());
			ps.setString(6, v.getGhiChu());
			ps.setString(7, v.getDonViSanPham());
			ps.setString(8, v.getHinhAnh());
			ps.setString(9, v.getTenVanPhongPham());
			ps.setString(1, v.getLoaiVanPhongPham().getMaLoai());
			ps.setString(10, v.getMauSac().getMaMau());
			ps.setString(11, v.getChatLieu().getMaChatLieu());
			ps.setString(12, v.getXuatXu().getMaXuatXu());
			return ps.executeUpdate() > 0;
		}
	}

	public boolean xoaSanPham(String maSP) throws SQLException {
		String query = "DELETE FROM SanPham WHERE maSanPham = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, maSP);
		int affectedRows = ps.executeUpdate();
		return affectedRows > 0;
	}

	public String getMaSPMax() throws SQLException {
		String query = "SELECT MAX(maSanPham) AS maSP FROM SanPham";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getString("maSP");
		}
		return null;
	}

	public Sach timSanPhamTheoMaSach(String maSach) throws Exception {
		String query = "SELECT SanPham.maSanPham, SanPham.soLuongTon, SanPham.loaiSanPham, NhaCungCap.maNCC, NhaCungCap.tenNCC, SanPham.giaNhap, SanPham.ghiChu, SanPham.trongLuong, SanPham.donViSanPham, SanPham.hinhAnh, SanPham.tenSach, TacGia.maTacGia, TacGia.tenTacGia, NhaXuatBan.maNXB, NhaXuatBan.tenNXB, SanPham.namXB, SanPham.soTrang, TheLoaiSach.maTheLoai, TheLoaiSach.tenTheLoai FROM SanPham INNER JOIN NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC INNER JOIN NhaXuatBan ON SanPham.maNXB = NhaXuatBan.maNXB INNER JOIN TacGia ON SanPham.maTacGia = TacGia.maTacGia INNER JOIN TheLoaiSach ON SanPham.maTheLoai = TheLoaiSach.maTheLoai where SanPham.maSanPham like ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, maSach);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new Sach(rs.getString("maSanPham"), rs.getString("loaiSanPham"), rs.getInt("soLuongTon"),
					rs.getDouble("trongLuong"), new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")),
					rs.getLong("giaNhap"), rs.getString("ghiChu"), rs.getString("donViSanPham"),
					rs.getString("hinhAnh"), rs.getString("tenSach"),
					new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia")),
					new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB")), rs.getInt("namXB"),
					rs.getInt("soTrang"), new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai")));
		}
		return null;
	}

	public VanPhongPham timSanPhamTheoMaVPP(String maVPP) throws Exception {
		String query = "SELECT SanPham.maSanPham, SanPham.loaiSanPham, SanPham.soLuongTon, SanPham.trongLuong, " +
				"NhaCungCap.maNCC, NhaCungCap.tenNCC, SanPham.giaNhap, SanPham.ghiChu, SanPham.donViSanPham, SanPham.hinhAnh, " +
				"SanPham.tenVanPhongPham, LoaiVanPhongPham.maLoaiVanPhongPham, LoaiVanPhongPham.tenTheLoai, MauSac.maMauSac, MauSac.tenMau, ChatLieu.maChatLieu, ChatLieu.tenChatLieu, XuatXu.maXuatXu, XuatXu.tenXuatXu " +
				"FROM SanPham INNER JOIN LoaiVanPhongPham ON SanPham.maLoaiVanPhongPham = LoaiVanPhongPham.maLoaiVanPhongPham INNER JOIN " +
				"ChatLieu ON SanPham.maChatLieu = ChatLieu.maChatLieu INNER JOIN MauSac ON SanPham.maMauSac = MauSac.maMauSac INNER JOIN " +
				"XuatXu ON SanPham.maXuatXu = XuatXu.maXuatXu INNER JOIN NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC " +
				"where SanPham.maSanPham like ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, maVPP);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return new VanPhongPham(rs.getString("maSanPham"), rs.getString("loaiSanPham"),
					rs.getInt("soLuongTon"), rs.getDouble("trongLuong"),
					new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")), rs.getLong("giaNhap"),
					rs.getString("ghiChu"), rs.getString("donViSanPham"), rs.getString("hinhAnh"),
					rs.getString("tenVanPhongPham"),
					new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"), rs.getString("tenTheLoai")),
					new MauSac(rs.getString("maMauSac"), rs.getString("tenMau")),
					new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu")),
					new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu")));
		}
		return null;
	}

	public List<Sach> getAllSach() throws Exception {
		List<Sach> dsS = new ArrayList<>();
		String query = "SELECT * FROM SanPham WHERE loaiSanPham = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, "Sách");
		rs = ps.executeQuery();
		while (rs.next()) {
			Sach s = new Sach(
					rs.getString("maSanPham"),
					rs.getString("loaiSanPham"),
					rs.getInt("soLuongTon"),
					rs.getDouble("trongLuong"),
					new NhaCungCap(rs.getString("maNCC")),
					rs.getLong("giaNhap"),
					rs.getString("ghiChu"),
					rs.getString("soLuongTon"),
					rs.getString("hinhAnh"),
					rs.getString("tenSach"),
					rs.getString("maTacGia") != null ? new TacGia(rs.getString("maTacGia")) : null,
					new NhaXuatBan(rs.getString("maNXB")),
					rs.getInt("namXB"),
					rs.getInt("soTrang"),
					new TheLoaiSach(rs.getString("maTheLoai"))
			);
			dsS.add(s);
		}
		return dsS;
	}

	public List<VanPhongPham> getAllVPP() {
		List<VanPhongPham> dsVPP = new ArrayList<>();
		try {
			String query = "Select * from SanPham where loaiSanPham=?";
			ps = con.prepareStatement(query);
			ps.setString(1, "Văn phòng phẩm");
			rs = ps.executeQuery();
			while (rs.next()) {
				VanPhongPham vpp = new VanPhongPham(
						rs.getString("maSanPham"),
						rs.getString("loaiSanPham"),
						rs.getInt("soLuongTon"),
						rs.getDouble("trongLuong"),
						new NhaCungCap(rs.getString("maNCC")),
						rs.getLong("giaNhap"),
						rs.getString("ghiChu"),
						rs.getString("soLuongTon"),
						rs.getString("hinhAnh"),
						rs.getString("tenVanPhongPham"),
						new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham")),
						new MauSac(rs.getString("maMauSac")),
						new ChatLieu(rs.getString("maChatLieu")),
						new XuatXu(rs.getString("maXuatXu"))
				);
				dsVPP.add(vpp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsVPP;
	}

	public Sach getSachTheoTen(String ten) {
		Sach s = null;
		try {
			String query = "Select * from SanPham where tenSach=?";
			ps = con.prepareStatement(query);
			ps.setString(1, ten);
			rs = ps.executeQuery();
			if (rs.next()) {
				s = new Sach(
						rs.getString("maSanPham"),
						rs.getString("loaiSanPham"),
						rs.getInt("soLuongTon"),
						rs.getDouble("trongLuong"),
						new NhaCungCap(rs.getString("maNCC")), // Create a new NhaCungCap object
						rs.getLong("giaNhap"),
						rs.getString("ghiChu"),
						rs.getString("soLuongTon"),
						rs.getString("hinhAnh"),
						rs.getString("tenSach"),
						new TacGia(rs.getString("maTacGia")), // Create a new TacGia object
						new NhaXuatBan(rs.getString("maNXB")), // Create a new NhaXuatBan object
						rs.getInt("namXB"),
						rs.getInt("soTrang"),
						new TheLoaiSach(rs.getString("maTheLoai")) // Create a new TheLoaiSach object
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	public VanPhongPham getVPPTheoTen(String ten) {
		VanPhongPham vpp = null;
		try {
			String query = "Select * from SanPham where tenVanPhongPham=?";
			ps = con.prepareStatement(query);
			ps.setString(1, ten);
			rs = ps.executeQuery();
			if (rs.next()) {
				vpp = new VanPhongPham(
						rs.getString("maSanPham"),
						rs.getString("loaiSanPham"),
						rs.getInt("soLuongTon"),
						rs.getDouble("trongLuong"),
						new NhaCungCap(rs.getString("maNCC")), // Create a new NhaCungCap object
						rs.getLong("giaNhap"),
						rs.getString("ghiChu"),
						rs.getString("soLuongTon"),
						rs.getString("hinhAnh"),
						rs.getString("tenVanPhongPham"),
						new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham")), // Create a new TheLoaiVanPhongPham object
						new MauSac(rs.getString("maMauSac")), // Create a new MauSac object
						new ChatLieu(rs.getString("maChatLieu")), // Create a new ChatLieu object
						new XuatXu(rs.getString("maXuatXu")) // Create a new XuatXu object
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vpp;
	}

	public int capNhatSoLuongSanPham(SanPham sanPham) throws SQLException {
		String sql = "UPDATE SanPham SET soLuongTon = ? WHERE maSanPham = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, sanPham.getSoLuongTon());
		stmt.setString(2, sanPham.getMaSanPham());
		return stmt.executeUpdate();
	}

	public boolean kiemTraTonTaiSanPham(String tenSP) {
		String query = "SELECT * FROM SanPham WHERE tenSach = ? OR tenVanPhongPham = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, tenSP);
			ps.setString(2, tenSP);
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public SanPham timSanPhamTheoMa1(String maSP) throws SQLException {
		String query = "Select * from SanPham where maSanPham=?";
		ps = con.prepareStatement(query);
		ps.setString(1, maSP);
		rs = ps.executeQuery();

		if (rs.next()) {
			return new SanPham(
					rs.getString("maSanPham"),
					rs.getString("loaiSanPham"),
					rs.getInt("soLuongTon"),
					rs.getDouble("trongLuong"),
					new NhaCungCap(rs.getString("maNCC")),
					rs.getLong("giaNhap"),
					rs.getString("ghiChu"),
					rs.getString("donViSanPham"),
					rs.getString("hinhAnh")
			);
		}
		return null;
	}
	

}
