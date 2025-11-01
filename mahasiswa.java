// src/Mahasiswa.java
public class Mahasiswa {
    private String nama;
    private String nim;
    private Dosen pembimbing;
    private String statusPermintaan; // Belum mengajukan, Menunggu konfirmasi, Diterima, Ditolak, Dihapus

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

    public void setPembimbing(Dosen pembimbing) { this.pembimbing = pembimbing; }
    public void setStatusPermintaan(String status) { this.statusPermintaan = status; }
    public String getNama() { return nama; }
    public String getNim() { return nim; }
}
