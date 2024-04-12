package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.TheLoaiSach;
import entity.TheLoaiVanPhongPham;

public interface TheLoaiService {
<<<<<<< HEAD
	public ArrayList<TheLoaiSach> getListTheLoaiSach() throws Exception;

	public ArrayList<TheLoaiVanPhongPham> getListTheLoaiVanPhongPham() throws Exception;
=======
	public List<TheLoaiSach> getListTheLoaiSach() throws Exception;

	public List<TheLoaiVanPhongPham> getListTheLoaiVanPhongPham() throws Exception;
>>>>>>> 18a2abe (Update DAO & Service)

	public boolean themTheLoaiSach(TheLoaiSach t) throws Exception;

	public boolean themTheLoaiVanPhongPham(TheLoaiVanPhongPham t) throws Exception;

	public TheLoaiSach timTheLoaiSach(String TheLoai) throws SQLException;

	public TheLoaiVanPhongPham timTheLoaiVanPhongPham(String TheLoai) throws SQLException;

	public List<TheLoaiSach> getSachTheoTheLoai(String maTL);
}
