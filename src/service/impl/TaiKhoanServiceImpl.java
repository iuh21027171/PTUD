package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.NhanVienDao;
import dao.TaiKhoanDao;
import entity.TaiKhoan;
import service.TaiKhoanService;

public class TaiKhoanServiceImpl implements TaiKhoanService {
	TaiKhoanDao taiKhoanDao = new TaiKhoanDao();

	public TaiKhoanServiceImpl() {
		taiKhoanDao = new TaiKhoanDao();
	}

	@Override
	public int insertAccount(TaiKhoan taiKhoan) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return taiKhoanDao.insertAccount(taiKhoan);
	}
=======
        try {
            return taiKhoanDao.insertAccount(taiKhoan);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> 18a2abe (Update DAO & Service)

	@Override
	public ArrayList<TaiKhoan> getList() {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return taiKhoanDao.getList();
	}
=======
        try {
            return taiKhoanDao.getList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> 18a2abe (Update DAO & Service)

	@Override
	public int xoaTaiKhoan(String maNhanVien) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return taiKhoanDao.xoaTaiKhoan(maNhanVien);
	}
=======
        try {
            return taiKhoanDao.xoaTaiKhoan(maNhanVien);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> 18a2abe (Update DAO & Service)

	@Override
	public TaiKhoan getTaiKhoanTheoMaNV(String maNV) throws SQLException {
		// TODO Auto-generated method stub
		return taiKhoanDao.getTaiKhoanTheoMaNV(maNV);
	}

	@Override
	public int doiMatKhau(String passMoi, String maNV) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return taiKhoanDao.doiMatKhau(passMoi, maNV);
	}
=======
        try {
            return taiKhoanDao.doiMatKhau(passMoi, maNV);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> 18a2abe (Update DAO & Service)

}
