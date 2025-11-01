import java.util.ArrayList;
import java.util.List;

// ==================== CLASS MAHASISWA ====================
class Mahasiswa {
    private String nama;
    private String nim;
    private Dosen pembimbing;
    private String statusPermintaan; // pending, diterima, ditolak

    public Mahasiswa(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
        this.statusPermintaan = "Belum mengajukan";
    }

    // US-004: Ajukan permintaan bimbingan
    public void ajukanBimbingan(Dosen dosen) {
        System.out.println("INFO: " + nama + " mengajukan bimbingan ke " + dosen.getNama());
        dosen.terimaPermintaan(this);
        this.statusPermintaan = "Menunggu konfirmasi";
    }

    // US-002: Lihat dosen pembimbing
    public void lihatDosenPembimbing() {
        if (pembimbing != null) {
            System.out.println("INFO: Dosen pembimbing " + nama + " adalah " + pembimbing.getNama());
        } else {
            System.out.println("INFO: " + nama + " belum memiliki dosen pembimbing.");
        }
    }

    // US-006: Lihat status permintaan
    public void lihatStatusPermintaan() {
        System.out.println("STATUS: " + nama + " -> " + statusPermintaan);
    }

    // Setter & Getter
    public void setPembimbing(Dosen pembimbing) {
        this.pembimbing = pembimbing;
    }

    public void setStatusPermintaan(String status) {
        this.statusPermintaan = status;
    }

    public String getNama() { return nama; }
    public String getNim() { return nim; }
}

// ==================== CLASS DOSEN ====================
class Dosen {
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

    // US-004: Terima permintaan (sementara masuk daftar)
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
    public List<Mahasiswa> getMahasiswaBimbingan() { return mahasiswaBimbingan; }
}

// ==================== CLASS ADMIN ====================
class Admin {
    private String nama;

    public Admin(String nama) {
        this.nama = nama;
    }

    // US-008: Lihat seluruh relasi bimbingan
    public void lihatRelasi(List<Dosen> dosenList) {
        System.out.println("=== Monitoring Relasi Bimbingan oleh Admin " + nama + " ===");
        for (Dosen d : dosenList) {
            d.lihatDaftarBimbingan();
        }
    }
}

// ==================== MAIN (DEMO) ====================
public class Main {
    public static void main(String[] args) {
        Dosen pakBudi = new Dosen("Dr. Budi", "112233");
        Mahasiswa ani = new Mahasiswa("Ani", "2024001");
        Mahasiswa tono = new Mahasiswa("Tono", "2024002");
        Admin admin = new Admin("SuperAdmin");

        System.out.println("=== DEMO SIAK MINI (Sprint 1-4) ===");

        // Sprint 2 & 3: Mahasiswa ajukan bimbingan
        ani.ajukanBimbingan(pakBudi);
        tono.ajukanBimbingan(pakBudi);

        // Sprint 3: Dosen proses permintaan
        pakBudi.prosesPermintaan(ani, true);   // diterima
        pakBudi.prosesPermintaan(tono, false); // ditolak

        // Sprint 2: Dosen lihat daftar bimbingan
        pakBudi.lihatDaftarBimbingan();

        // Sprint 1 & 3: Mahasiswa lihat pembimbing & status
        ani.lihatDosenPembimbing();
        ani.lihatStatusPermintaan();

        tono.lihatDosenPembimbing();
        tono.lihatStatusPermintaan();

        // Sprint 4: Hapus mahasiswa bimbingan
        pakBudi.hapusMahasiswaBimbingan(ani);

        // Sprint 4: Admin monitoring
        List<Dosen> dosenList = new ArrayList<>();
        dosenList.add(pakBudi);
        admin.lihatRelasi(dosenList);
    }
}
