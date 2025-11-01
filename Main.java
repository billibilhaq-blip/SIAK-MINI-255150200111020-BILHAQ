// src/Main.java
public class Main {
    public static void main(String[] args) {
        Dosen pakBudi = new Dosen("Dr. Budi", "112233");
        Mahasiswa ani = new Mahasiswa("Ani", "2024001");
        Mahasiswa tono = new Mahasiswa("Tono", "2024002");

        System.out.println("=== DEMO SIAK MINI (2 Sprint, tanpa Admin) ===");

        // Sprint 1: US-004 (ajukan), US-001 (tambah via proses), US-003 (lihat daftar), US-002 (lihat pembimbing)
        ani.ajukanBimbingan(pakBudi);
        tono.ajukanBimbingan(pakBudi);

        // Sprint 2: US-005 (proses), US-006 (status), US-007 (hapus)
        pakBudi.prosesPermintaan(ani, true);   // diterima
        pakBudi.prosesPermintaan(tono, false); // ditolak

        pakBudi.lihatDaftarBimbingan();

        ani.lihatDosenPembimbing();
        ani.lihatStatusPermintaan();

        tono.lihatDosenPembimbing();
        tono.lihatStatusPermintaan();

        pakBudi.hapusMahasiswaBimbingan(ani);
        pakBudi.lihatDaftarBimbingan();
    }
}
