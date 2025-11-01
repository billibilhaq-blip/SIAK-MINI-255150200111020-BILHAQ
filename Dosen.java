// src/Dosen.java
import java.util.ArrayList;
import java.util.List;

public class Dosen {
    private String nama;
    private String nidn;
    private List<Mahasiswa> mahasiswaBimbingan;
    private List<Mahasiswa> daftarPermintaan;

    public Dosen(String nama, String nidn) {
        this.nama = nama;
        this.nidn = nidn;
        this.mahasiswaBimbingan = new ArrayList<>();
        this.daftarPermintaan = new ArrayList<>();
    }

    // US-001: Tambah mahasiswa bimbingan
    public void tambahMahasiswaBimbingan(Mahasiswa mhs) {
        mahasiswaBimbingan.add(mhs);
        mhs.setPembimbing(this);
        mhs.setStatusPermintaan("Diterima");
        System.out.println("INFO: " + mhs.getNama() + " berhasil menjadi bimbingan " + nama);
    }

    // US-003: Lihat daftar bimbingan
    public void lihatDaftarBimbingan() {
        System.out.println("--- Daftar Bimbingan Dosen: " + nama + " ---");
        for (Mahasiswa mhs : mahasiswaBimbingan) {
            System.out.println("- " + mhs.getNama() + " (" + mhs.getNim() + ")");
        }
    }

    // US-004: Terima permintaan (masuk antrian)
    public void terimaPermintaan(Mahasiswa mhs) {
        daftarPermintaan.add(mhs);
    }

    // US-005: Proses permintaan (terima/tolak)
    public void prosesPermintaan(Mahasiswa mhs, boolean diterima) {
        if (daftarPermintaan.contains(mhs)) {
            if (diterima) {
                tambahMahasiswaBimbingan(mhs);
            } else {
                mhs.setStatusPermintaan("Ditolak");
                System.out.println("INFO: Permintaan " + mhs.getNama() + " ditolak oleh " + nama);
            }
            daftarPermintaan.remove(mhs);
        }
    }

    // US-007: Hapus mahasiswa bimbingan
    public void hapusMahasiswaBimbingan(Mahasiswa mhs) {
        if (mahasiswaBimbingan.remove(mhs)) {
            mhs.setPembimbing(null);
            mhs.setStatusPermintaan("Dihapus");
            System.out.println("INFO: " + mhs.getNama() + " dihapus dari bimbingan " + nama);
        }
    }

    public String getNama() { return nama; }
}
